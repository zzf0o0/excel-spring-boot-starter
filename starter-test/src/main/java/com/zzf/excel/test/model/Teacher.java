package com.zzf.excel.test.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**
 * @author: zzf
 * @description: 老师
 */
@HeadRowHeight(value = 40)
public class Teacher {

    @ExcelProperty(value = "姓名")
    @ColumnWidth(value = 20)
    private String tecName;

    @ExcelProperty(value = "年龄")
    @ColumnWidth(value = 20)
    private Integer age;

    public String getTecName() {
        return tecName;
    }

    public void setTecName(String tecName) {
        this.tecName = tecName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
