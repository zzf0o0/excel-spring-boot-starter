package com.zzf.excel.core.annotation;

import java.lang.annotation.*;

/**
 * @author: zzf
 * @description: 表格导入校验
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExcelPropertyCheck {

    boolean passCheckTag() default false; //校验标志，在某些情况下，无论填没填，不需要校验

    boolean tag() default false;

    boolean required() default false; //是否必填,默认非必填

    boolean checkFormat() default false; //是否进行格式检验，默认不进行。

    String type() default ""; //格式检验类型，int

    int length() default -1; //长度校验， int 字符串的长度，-1不进行校验

    String[] value() default {""};

    String defaultValue() default "";

    Class<?>[] enumCheckClazz() default {};

}
