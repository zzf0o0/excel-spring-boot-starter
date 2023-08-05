package com.zzf.excel.test.strategy;


import com.zzf.excel.core.domain.strategy.flow.AbstractBaseImportExcel;
import com.zzf.excel.test.model.Student;
import com.zzf.excel.core.annotation.StrategyModel;
import com.zzf.excel.core.common.ExcelUtils;
import com.zzf.excel.core.support.DataImportInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author: zzf
 * @description: 学生策略
 */
@StrategyModel(strategyName = "student")
public class StudentExcelStrategy extends AbstractBaseImportExcel {

    @Override
    public List<?> doReadFileAndCheck(MultipartFile file, List<DataImportInfo> importLogList, String importType) throws IOException {
        return ExcelUtils.readExcel(file.getInputStream(), Student.class, 1);
    }

    @Override
    public void doImplementExcelImport(List<?> qualifiedExcelDataList, List<DataImportInfo> importLogList, String importType) {
        // TODO 逻辑处理
    }

    @Override
    public void doAllImportOrIncrementImport(List<?> qualifiedExcelDataList, List<DataImportInfo> importLogList) {
        // TODO 逻辑处理
    }

}
