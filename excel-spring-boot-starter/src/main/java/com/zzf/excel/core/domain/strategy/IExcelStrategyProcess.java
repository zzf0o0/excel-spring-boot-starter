package com.zzf.excel.core.domain.strategy;

import com.zzf.excel.core.support.DataImportInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @description: 策略接口，提供导入流程实现支撑
 * @author zzf
 */
public interface IExcelStrategyProcess {

    /**
     * 读取文件并校验
     *
     * @param file          file
     * @param importLogList req
     * @param importType    req
     * @return res
     * @throws IOException IO异常
     */
    List<?> doReadFileAndCheck(MultipartFile file, List<DataImportInfo> importLogList, String importType) throws IOException;

    /**
     * 实现业务导入
     *
     * @param qualifiedExcelDataList req
     * @param importLogList          req
     * @param importType             req
     */
    void doImplementExcelImport(List<?> qualifiedExcelDataList, List<DataImportInfo> importLogList, String importType);

    /**
     * 增量/全量 导入
     *
     * @param qualifiedExcelDataList req
     * @param importLogList          req
     */
    void doAllImportOrIncrementImport(List<?> qualifiedExcelDataList, List<DataImportInfo> importLogList);

    /**
     * 校验
     *
     * @param qualifiedExcelDataList req
     * @param importLogList          req
     */
    void doCheck(List<?> qualifiedExcelDataList, List<DataImportInfo> importLogList);

}
