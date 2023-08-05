package com.zzf.excel.test.controller;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.excel.EasyExcel;
import com.zzf.excel.test.model.Student;
import com.zzf.excel.core.common.ExcelUtils;
import com.zzf.excel.core.domain.factory.IExcelServiceFactory;
import com.zzf.excel.core.enums.ExcelImportTypeEnum;
import com.zzf.excel.core.exception.ExcelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: zzf
 * @description: 测试控制器
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private IExcelServiceFactory factory;

    @GetMapping(value = "/testImport")
    public String testImport(MultipartFile file, String strategy) {
        try {
            return factory.doImportExcel(file, strategy, ExcelImportTypeEnum.FULL_IMPORT.getCode());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ExcelException(e.getMessage());
        }
    }

    /** 导出模板*/
    @GetMapping(value = "/exportTemplate")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        OutputStream outputStream = ExcelUtils.getOutputStream(response, "学生");
        EasyExcel.write(outputStream, Student.class)
                .autoCloseStream(Boolean.TRUE)
                .sheet("学生").doWrite(ListUtil.empty());
    }

}
