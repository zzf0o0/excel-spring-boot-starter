package com.zzf.excel.core.enums;


/**
 * @author: zzf
 * @description: 导入类型枚举（增量导入，全量导入）
 */

public enum ExcelImportTypeEnum {

    /**
     * 全量导入
     */
    FULL_IMPORT("FULL_IMPORT", "全量导入"),

    /**
     * 增量导入
     */
    INCREMENTAL_IMPORT("INCREMENTAL_IMPORT", "增量导入"),
    ;

    ExcelImportTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
