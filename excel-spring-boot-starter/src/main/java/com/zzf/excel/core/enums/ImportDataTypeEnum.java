package com.zzf.excel.core.enums;

/**
 * @author: zzf
 * @description: 导入日志类别
 */
public enum ImportDataTypeEnum {

    /**
     * 汇总
     */
    GATHER("GATHER", "汇总"),

    /**
     * 失败
     */
    ERROR("ERROR", "失败"),

    /**
     * 警告
     */
    WARNING("WARNING", "警告"),

    /**
     * 正常
     */
    INFO("INFO", "正常");

    private String code;

    private String desc;

    ImportDataTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
