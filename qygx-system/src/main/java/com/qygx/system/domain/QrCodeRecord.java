package com.qygx.system.domain;

public class QrCodeRecord {

    private Long id;

    /**批次号**/
    private String qrCode;

    /**产品代码**/
    private String productCode;

    /**品名**/
    private String productType;

    /**材质**/
    private String materialQuality;

    /**原料规格**/
    private String materialSpec;

    /**初始数量**/
    private int quantity;

    /**单轴机成盘数**/
    private int quantitySum;

    /**3轴机成盘数**/
    private int quantity3;

    /**4轴机成盘数**/
    private int quantity4;

    /**创建工序**/
    private String processName;

    /**加工结果**/
    private String processResult;

    /**加工状态**/
    private String processStatus;

    /**当前数量**/
    private int currentQuantity;

    /**备注**/
    private String remark;


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

    public String getMaterialQuality() {
        return materialQuality;
    }

    public void setMaterialQuality(String materialQuality) {
        this.materialQuality = materialQuality;
    }

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantitySum() {
        return quantitySum;
    }

    public void setQuantitySum(int quantitySum) {
        this.quantitySum = quantitySum;
    }

    public int getQuantity3() {
        return quantity3;
    }

    public void setQuantity3(int quantity3) {
        this.quantity3 = quantity3;
    }

    public int getQuantity4() {
        return quantity4;
    }

    public void setQuantity4(int quantity4) {
        this.quantity4 = quantity4;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessResult() {
        return processResult;
    }

    public void setProcessResult(String processResult) {
        this.processResult = processResult;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
