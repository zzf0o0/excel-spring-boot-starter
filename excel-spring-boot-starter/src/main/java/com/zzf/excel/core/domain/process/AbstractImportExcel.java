package com.zzf.excel.core.domain.process;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.StrUtil;
import com.zzf.excel.core.exception.ExcelException;
import com.zzf.excel.core.domain.factory.IExcelServiceFactory;
import com.zzf.excel.core.domain.strategy.IExcelStrategyProcess;
import com.zzf.excel.core.enums.ExcelImportTypeEnum;
import com.zzf.excel.core.support.DataImportInfo;
import com.zzf.excel.core.support.ExcelStrategySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zzf
 * @description: 定义标准excel导入流程，策略实现业务解析
 */
public abstract class AbstractImportExcel extends ExcelStrategySupport implements IExcelServiceFactory {

    private final Logger logger = LoggerFactory.getLogger(AbstractImportExcel.class);
    private IExcelStrategyProcess strategyProcess;

    @Override
    public String doImportExcel(MultipartFile file, String strategyName, String importType) throws Exception {
        logger.info("导入策略：{}，导入模式：{}", strategyName, importType);
        List<DataImportInfo> importLogList = new ArrayList<>();
        TimeInterval timer = new TimeInterval();

        // 1.校验文件格式
        checkFile(file);
        logger.info("step 1：校验文件格式 Timer {} S", timer.intervalSecond("1"));

        // 2.获取导入策略
        this.strategyProcess = getImportStrategy(strategyName);
        logger.info("step 2：获取导入策略 Timer {} S", timer.intervalSecond("2"));

        // 3.读取解析校验excel数据
        List<?> qualifiedExcelDataList = doReadFileAndCheck(file, importType, importLogList);
        logger.info("step 3：读取解析excel数据 Timer {} S", timer.intervalSecond("3"));

        // 4.逻辑业务执行策略导入
        implementExcelImport(qualifiedExcelDataList, importType, importLogList);
        logger.info("step 4：逻辑业务执行策略导入 Timer {} S", timer.intervalSecond("4"));

        // 5.全量导入 or 增量导入 业务处理
        allImportOrIncrementImport(qualifiedExcelDataList, importType, importLogList);
        logger.info("step 5：全量导入 or 增量导入 业务处理 Timer {} S", timer.intervalSecond("6"));

        // 根据日志构建返回结果msg
        return importLogInsertBuildResult(importType, strategyProcess, importLogList, strategyName);
    }

    /**
     * 构建导入日志信息msg
     *
     * @param importType      导入模式
     * @param strategyProcess 导入策略
     * @param importLogList   日志集合
     * @param strategyName    策略名称（表名）
     * @return res
     */
    protected abstract String importLogInsertBuildResult(String importType, IExcelStrategyProcess strategyProcess, List<DataImportInfo> importLogList, String strategyName);

    /**
     * 5.全量导入 or 增量导入 业务处理
     * <p>
     * 全量导入（内部存在外部不存在，内部删除）
     * </p>
     *
     * @param qualifiedExcelDataList excel数据
     * @param importType             导入模式
     * @param importLogList          日志集合
     */
    private void allImportOrIncrementImport(List<?> qualifiedExcelDataList, String importType, List<DataImportInfo> importLogList) {
        if (importType.equals(ExcelImportTypeEnum.INCREMENTAL_IMPORT.getCode())) {
            logger.info("非增量导入不处理删除数据");
            return;
        }
        if (null == qualifiedExcelDataList || qualifiedExcelDataList.size() == 0) {
            logger.warn("合法数据为空，增量导入终止");
            return;
        }
        strategyProcess.doAllImportOrIncrementImport(qualifiedExcelDataList, importLogList);
    }

    /**
     * 逻辑业务执行策略导入
     *
     * <p>
     * 增量导入
     * 1.外部存在，内部不存在，新增
     * 2.外部存在，内部也存在，修改
     * <p>
     * 全量导入
     * 1.外部存在，内部不存在，做新增
     * 2.外部存在，内部也存在，做修改
     * 3.外部不存在，内部存在，内部做删除
     *
     * @param qualifiedExcelDataList excel数据
     * @param importType             导入类型
     * @param importLogList          日志集
     */
    private void implementExcelImport(List<?> qualifiedExcelDataList, String importType, List<DataImportInfo> importLogList) {
        if (null == qualifiedExcelDataList || qualifiedExcelDataList.size() == 0) {
            logger.warn("合法数据为空，逻辑业务执行策略导入终止");
            return;
        }
        strategyProcess.doImplementExcelImport(qualifiedExcelDataList, importLogList, importType);
    }

    /**
     * 读取excel文件并执行自定义校验规则
     *
     * @param file          文件
     * @param importType    导入类型
     * @param importLogList 日志集
     * @return res
     */
    private List<?> doReadFileAndCheck(MultipartFile file, String importType, List<DataImportInfo> importLogList) throws IOException {
        return strategyProcess.doReadFileAndCheck(file, importLogList, importType);
    }

    /**
     * 校验excel文件格式
     *
     * @param file file
     */
    private void checkFile(MultipartFile file) {
        String xlsName = "XLS";
        String xlsxName = "XLSX";
        String csvName = "CSV";
        if (null == file) {
            throw new ExcelException("请选择excel文件！");
        }
        String fileName = file.getOriginalFilename();
        if (StrUtil.isEmpty(fileName)) {
            throw new ExcelException("获取文件名称失败，请选择正确的文件！");
        }
        if (!fileName.toUpperCase().endsWith(xlsName) && !fileName.toUpperCase().endsWith(xlsxName) && !fileName.toUpperCase().endsWith(csvName)) {
            throw new ExcelException("文件类型错误，请选择excel文件！");
        }
    }

}
