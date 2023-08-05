package com.zzf.excel.core.support;

import org.springframework.context.ApplicationContext;

import java.util.Date;

/**
 * @author: zzf
 * @description: 导入日志
 */
public class DataImportInfo {

    private Integer id;

    private String tableName;

    private String infoType;

    private String remark;

    private String creator;

    private Date createTime;

    private String importProgram;

    private String uid;

    private String errorDetails;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getImportProgram() {
        return importProgram;
    }

    public void setImportProgram(String importProgram) {
        this.importProgram = importProgram;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    @Override
    public String toString() {
        return "DataImportInfo{" +
                "id=" + id +
                ", tableName='" + tableName + '\'' +
                ", infoType='" + infoType + '\'' +
                ", remark='" + remark + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", importProgram='" + importProgram + '\'' +
                ", uid='" + uid + '\'' +
                ", errorDetails='" + errorDetails + '\'' +
                '}';
    }

}
