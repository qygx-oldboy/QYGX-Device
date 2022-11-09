package com.qygx.system.domain.dto;

import java.io.Serializable;

public class DvRunDto implements Serializable {

    /**记录日期 */
    private String recordDate;

    /** 粗抛*/
    private String cuPao;

    /** 粗磨*/
    private String cuMo;

    /** 水抛*/
    private String shuiPao;

    /** 精抛*/
    private String jingPao;

    /** 粗抛机台数量*/
    private String cuPaoNum;

    /** 粗磨机台数量*/
    private String cuMoNum;

    /** 水抛机台数量*/
    private String shuiPaoNum;

    /** 精抛机台数量*/
    private String jingPaoNum;


    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getCuPao() {
        return cuPao;
    }

    public void setCuPao(String cuPao) {
        this.cuPao = cuPao;
    }

    public String getCuMo() {
        return cuMo;
    }

    public void setCuMo(String cuMo) {
        this.cuMo = cuMo;
    }

    public String getShuiPao() {
        return shuiPao;
    }

    public void setShuiPao(String shuiPao) {
        this.shuiPao = shuiPao;
    }

    public String getJingPao() {
        return jingPao;
    }

    public void setJingPao(String jingPao) {
        this.jingPao = jingPao;
    }

    public String getCuPaoNum() {
        return cuPaoNum;
    }

    public void setCuPaoNum(String cuPaoNum) {
        this.cuPaoNum = cuPaoNum;
    }

    public String getCuMoNum() {
        return cuMoNum;
    }

    public void setCuMoNum(String cuMoNum) {
        this.cuMoNum = cuMoNum;
    }

    public String getShuiPaoNum() {
        return shuiPaoNum;
    }

    public void setShuiPaoNum(String shuiPaoNum) {
        this.shuiPaoNum = shuiPaoNum;
    }

    public String getJingPaoNum() {
        return jingPaoNum;
    }

    public void setJingPaoNum(String jingPaoNum) {
        this.jingPaoNum = jingPaoNum;
    }
}
