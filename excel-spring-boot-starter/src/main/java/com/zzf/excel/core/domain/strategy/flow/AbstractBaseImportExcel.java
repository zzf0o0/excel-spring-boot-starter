package com.zzf.excel.core.domain.strategy.flow;

import com.zzf.excel.core.domain.strategy.IExcelStrategyProcess;
import com.zzf.excel.core.support.DataImportInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author: zzf
 * @description: excel导入实现策略基类，公共方法提取实现
 */
public abstract class AbstractBaseImportExcel implements IExcelStrategyProcess {

    private final Logger logger = LoggerFactory.getLogger(AbstractBaseImportExcel.class);

    @Override
    public void doCheck(List<?> qualifiedExcelDataList, List<DataImportInfo> importLogList) {
        if(null == qualifiedExcelDataList || qualifiedExcelDataList.size() == 0){
            logger.warn("校验数据为空");

            return;
        }
        for (Object obj : qualifiedExcelDataList) {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                // TODO 针对每个字段完成自定义校验规则，并创建导入信息
            }
        }

    }

}
