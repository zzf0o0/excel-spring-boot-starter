package com.zzf.excel.test.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**
 * @author: zzf
 * @description: 学生
 */
@HeadRowHeight(value = 40)
public class Student {

    @ExcelProperty(value = "姓名")
    @ColumnWidth(value = 20)
    private String stuName;

    @ExcelProperty(value = "年龄")
    @ColumnWidth(value = 20)
    private Integer age;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
