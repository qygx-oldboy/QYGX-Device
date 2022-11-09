package com.qygx.mes.quato.domain.dto;


import java.io.Serializable;

/**
 * 指标DTO
 */
public class QuatoDTO implements Serializable {

    /** 主键id */
    private Long id;

    /** 指标名称 */
    private String quatoName;

    /** 指标单位 */
    private String unit;

    /** 报文主题 */
    private String subject;

    /** 指标值字段 */
    private String valueKey;

    /** 设备识别码字段 */
    private String snKey;

    /** web钩子 */
    private String webhook;

    /** 指标字段类型 Double、Int、Boolean */
    private String valueType;

    /** 参考值 */
    private String referenceValue;

    /**指标值(数值) */
    private Double value;

    /**指标值（字符串） */
    private String stringValue;

    /**设备编号 */
    private String DeviceCode;

    /**是否告警 */
    private String alarm;  // 0不告警   1告警

    /**告警名称 */
    private String alarmName;

    /**告警级别 */
    private String level;

    /**告警钩子 */
    private String alarmWebHook;

    /**沉默周期 */
    private Long cycle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuatoName() {
        return quatoName;
    }

    public void setQuatoName(String quatoName) {
        this.quatoName = quatoName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getValueKey() {
        return valueKey;
    }

    public void setValueKey(String valueKey) {
        this.valueKey = valueKey;
    }

    public String getSnKey() {
        return snKey;
    }

    public void setSnKey(String snKey) {
        this.snKey = snKey;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(String referenceValue) {
        this.referenceValue = referenceValue;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getDeviceCode() {
        return DeviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        DeviceCode = deviceCode;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAlarmWebHook() {
        return alarmWebHook;
    }

    public void setAlarmWebHook(String alarmWebHook) {
        this.alarmWebHook = alarmWebHook;
    }

    public Long getCycle() {
        return cycle;
    }

    public void setCycle(Long cycle) {
        this.cycle = cycle;
    }
}
