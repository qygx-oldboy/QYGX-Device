package com.qygx.system.domain;


import com.qygx.common.annotation.Excel;

import java.util.Date;

/**
 * 检验数统计
 *
 * @author yxc
 */
public class ProInspect {

    /** 参数主键 */
    @Excel(name = "参数主键", cellType = Excel.ColumnType.NUMERIC)
    private Long id;

    private String qrCode;

    private String productCode;

    private String productType;

    private String materialType;

    private String materialQuality;

    private int quantityStd;

    private String inspector;

    private Date beginTime;

    private Date endTime;

    @Override
    public String toString() {
        return "ProInspect{" +
                "id=" + id +
                ", qrCode='" + qrCode + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productType='" + productType + '\'' +
                ", materialType='" + materialType + '\'' +
                ", materialQuality='" + materialQuality + '\'' +
                ", quantityStd=" + quantityStd +
                ", inspector='" + inspector + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", runTime=" + runTime +
                ", inspectedNum=" + inspectedNum +
                ", okNum=" + okNum +
                ", ngNum=" + ngNum +
                '}';
    }

    private Date runTime;

    private int inspectedNum;

    private int okNum;

    private int ngNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialQuality() {
        return materialQuality;
    }

    public void setMaterialQuality(String materialQuality) {
        this.materialQuality = materialQuality;
    }

    public int getQuantityStd() {
        return quantityStd;
    }

    public void setQuantityStd(int quantityStd) {
        this.quantityStd = quantityStd;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }


    public int getInspectedNum() {
        return inspectedNum;
    }

    public void setInspectedNum(int inspectedNum) {
        this.inspectedNum = inspectedNum;
    }

    public int getOkNum() {
        return okNum;
    }

    public void setOkNum(int okNum) {
        this.okNum = okNum;
    }

    public int getNgNum() {
        return ngNum;
    }

    public void setNgNum(int ngNum) {
        this.ngNum = ngNum;
    }

    public ProInspect() {
    }


}
