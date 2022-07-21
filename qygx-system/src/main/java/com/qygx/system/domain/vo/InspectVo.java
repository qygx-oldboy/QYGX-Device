package com.qygx.system.domain.vo;

public class InspectVo {

    private String date;

    private int checkNum;

    private int okNum;

    private Double yieldRate;

    public int getOkNum() {
        return okNum;
    }

    public void setOkNum(int okNum) {
        this.okNum = okNum;
    }

    @Override
    public String toString() {
        return "InspectVo{" +
                "date='" + date + '\'' +
                ", checkNum=" + checkNum +
                ", okNum=" + okNum +
                ", yieldRate=" + yieldRate +
                '}';
    }

    public Double getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(Double yieldRate) {
        this.yieldRate = yieldRate;
    }

    public InspectVo(String date, int checkNum, int okNum, Double yieldRate) {
        this.date = date;
        this.checkNum = checkNum;
        this.okNum = okNum;
        this.yieldRate = yieldRate;
    }

    public InspectVo() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }


}
