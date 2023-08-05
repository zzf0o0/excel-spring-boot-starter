package com.zzf.excel.core.common;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.SyncReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.zzf.excel.core.exception.ExcelException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author: zzf
 * @description: excel工具类
 */

public class ExcelUtils {

    @SuppressWarnings("unchecked")
    public static <T> List<T> readExcel(InputStream inputStream, Class<?> clazz, int headRowNumber) {
        if (null == inputStream) {
            throw new ExcelException("InputStream can not be null!");
        }
        SyncReadListener listener = new SyncReadListener();
        ReadSheet readSheet = new ReadSheet();
        readSheet.setClazz(clazz);
        readSheet.setHeadRowNumber(headRowNumber);
        ExcelReader reader = EasyExcel.read(inputStream).registerReadListener(listener).build();
        reader.read(readSheet);
        reader.finish();
        return (List<T>) listener.getList();
    }

    public static ServletOutputStream getOutputStream(HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition",
                "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
        return response.getOutputStream();
    }


}
