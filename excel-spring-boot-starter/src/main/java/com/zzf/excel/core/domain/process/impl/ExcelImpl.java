package com.zzf.excel.core.domain.process.impl;

import cn.hutool.core.util.EnumUtil;
import com.zzf.excel.core.domain.process.AbstractImportExcel;
import com.zzf.excel.core.domain.strategy.IExcelStrategyProcess;
import com.zzf.excel.core.enums.ExcelImportTypeEnum;
import com.zzf.excel.core.enums.ImportDataTypeEnum;
import com.zzf.excel.core.support.DataImportInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author:  zzf 
 * @description:  默认实现
 */
public class ExcelImpl extends AbstractImportExcel {

    private final Logger logger = LoggerFactory.getLogger(ExcelImpl.class);

    @Override
    protected String importLogInsertBuildResult(String importType, IExcelStrategyProcess strategyProcess, List<DataImportInfo> importLogList, String strategyName) {
        if (importLogList.size() == 0) {
            return "导入成功，无日志信息";
        }
        Date date = new Date();
        LinkedHashMap<String, ExcelImportTypeEnum> excelImportEnumMap = EnumUtil.getEnumMap(ExcelImportTypeEnum.class);

        Optional<DataImportInfo> first = importLogList.stream()
                .filter(k -> ImportDataTypeEnum.GATHER.getCode().equals(k.getInfoType())).findFirst();
        DataImportInfo parentDataImportInfo = first.orElse(null);
        assert parentDataImportInfo != null;
        parentDataImportInfo.setImportProgram(excelImportEnumMap.get(importType).getDesc());
        parentDataImportInfo.setCreateTime(date);
        parentDataImportInfo.setTableName(strategyName);
        // 拿到总条数
        String remark = parentDataImportInfo.getRemark();
        long errorCount = importLogList.stream().filter(k -> ImportDataTypeEnum.ERROR.getCode().equals(k.getInfoType())).count();
        long warningCount = importLogList.stream().filter(k -> ImportDataTypeEnum.WARNING.getCode().equals(k.getInfoType())).count();
        String resultMsg = "导入数据完成：总数据：" + remark + "条;错误信息" + errorCount + "条;警告信息" + warningCount + "条;详情请查看日志明细";
        logger.info(resultMsg);
        return resultMsg;

    }
}
