package com.zzf.excel.core.exception;

/**
 * @description: excel组件自定义异常
 * @author zzf
 */
public class ExcelException extends RuntimeException {

    public ExcelException(String msg) {
        super(msg);
    }

    public ExcelException(String msg, Throwable cause) {
        super(msg, cause);
    }

}