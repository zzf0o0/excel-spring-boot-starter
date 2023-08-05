package com.zzf.excel.core.support;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.zzf.excel.core.exception.ExcelException;
import com.zzf.excel.core.annotation.StrategyModel;
import com.zzf.excel.core.domain.strategy.IExcelStrategyProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: excel策略配置支撑
 * @author zzf
 */
public class ExcelStrategySupport implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(ExcelStrategySupport.class);

    private ApplicationContext applicationContext;

    /**
     * 导入策略配置组
     */
    private static final Map<String, IExcelStrategyProcess> IMPORT_EXCEL_STRATEGY_MAP = new ConcurrentHashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 容器启动完成获取所有标注策略注解策略类
     *
     * @param event event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            logger.info("spring container start Done load excel strategy Ing......");
            // 获取容器中标注 StrategyModel 策略注解的类
            Map<String, Object> strategyModeMap = applicationContext.getBeansWithAnnotation(StrategyModel.class);
            for (Map.Entry<String, Object> entry : strategyModeMap.entrySet()) {
                StrategyModel strategyMode = AnnotationUtil.getAnnotation(entry.getValue().getClass(), StrategyModel.class);
                if (entry.getValue() instanceof IExcelStrategyProcess) {
                    // 注册策略类
                    IMPORT_EXCEL_STRATEGY_MAP.put(strategyMode.strategyName(), (IExcelStrategyProcess) entry.getValue());
                }
            }
            logger.info("excel starter load strategy Done：{}", JSON.toJSONString(IMPORT_EXCEL_STRATEGY_MAP));
        } catch (Exception e) {
            logger.error("spring container start error：{}", e.getMessage(), e);
            throw e;
        }
    }


    /**
     * 获取策略实例化
     *
     * @param importStrategy 导入策略
     * @return ImportExcel {@link IExcelStrategyProcess}
     */
    protected IExcelStrategyProcess getImportStrategy(String importStrategy) {
        if (StrUtil.isEmpty(importStrategy)) {
            throw new ExcelException("导入策略名称为空，导入失败");
        }
        if (!IMPORT_EXCEL_STRATEGY_MAP.containsKey(importStrategy)) {
            logger.error("导入策略不存在：{}", importStrategy);
            throw new ExcelException("导入策略不存在：" + importStrategy);
        }
        IExcelStrategyProcess strategyProcess = IMPORT_EXCEL_STRATEGY_MAP.get(importStrategy);
        if (ObjectUtil.isEmpty(strategyProcess)) {
            logger.error("策略未实例化：{}", importStrategy);
            throw new ExcelException("策略未实例化，请检查：" + importStrategy);
        }
        return strategyProcess;
    }
}
