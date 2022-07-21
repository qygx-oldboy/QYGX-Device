package com.qygx.system.domain.vo;

/**
 * 质量监控基本信息
 *
 */
public class QualityVo {

    private String  date;
    private String  ban;

    public QualityVo() {
    }

    public QualityVo(String date, String ban) {
        this.date = date;
        this.ban = ban;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }
}
