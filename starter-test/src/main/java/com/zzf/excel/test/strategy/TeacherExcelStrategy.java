package com.zzf.excel.test.strategy;

import cn.hutool.core.collection.ListUtil;

import com.zzf.excel.core.annotation.StrategyModel;
import com.zzf.excel.core.domain.strategy.flow.AbstractBaseImportExcel;
import com.zzf.excel.core.support.DataImportInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author: zzf
 * @description: 老师策略
 */
@StrategyModel(strategyName = "teacher")
public class TeacherExcelStrategy extends AbstractBaseImportExcel {

    @Override
    public List<?> doReadFileAndCheck(MultipartFile file, List<DataImportInfo> importLogList, String importType) throws IOException {
        return ListUtil.empty();
    }

    @Override
    public void doImplementExcelImport(List<?> qualifiedExcelDataList, List<DataImportInfo> importLogList, String importType) {

    }

    @Override
    public void doAllImportOrIncrementImport(List<?> qualifiedExcelDataList, List<DataImportInfo> importLogList) {

    }

}
