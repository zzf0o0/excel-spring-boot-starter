package com.zzf.excel.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 策略类扫描注解，标注此注解都会被识别为一个导入/导出策略
 * @author zzf
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface StrategyModel {

    /**
     * 策略标识注解名称
     *
     * @return strategy name
     */
    String strategyName();

}
