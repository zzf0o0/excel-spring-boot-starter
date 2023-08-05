package com.zzf.excel.core.config;

import com.zzf.excel.core.domain.factory.IExcelServiceFactory;
import com.zzf.excel.core.domain.process.impl.ExcelImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 自动装配配置
 * @author zzf
 */
@Configuration
public class ExcelAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public IExcelServiceFactory createExcelFactory() {
        return new ExcelImpl();
    }

}
