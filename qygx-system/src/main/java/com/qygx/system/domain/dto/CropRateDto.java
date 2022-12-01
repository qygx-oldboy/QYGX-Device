package com.qygx.system.domain.dto;

import java.io.Serializable;

public class CropRateDto implements Serializable {

    /**记录日期 */
    private String recordDate;

    /** 名称*/
    private String name;

    /** 运行时间*/
    private String runTime;

    /** 设备数量*/
    private String dvNum;


    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getDvNum() {
        return dvNum;
    }

    public void setDvNum(String dvNum) {
        this.dvNum = dvNum;
    }
}
