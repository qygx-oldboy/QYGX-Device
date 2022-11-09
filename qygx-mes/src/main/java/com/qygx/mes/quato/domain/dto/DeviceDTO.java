package com.qygx.mes.quato.domain.dto;

import com.qygx.common.annotation.Excel;

import java.io.Serializable;

public class DeviceDTO implements Serializable {

    /** 设备id */
    private Long deviceId;

    /** 设备编码 */
    private String deviceCode;

    /** 设备名称 */
    private String name;

    /** 是否在线 */
    private Boolean online;


    /** 设备状态 */
    private String status;

    /**是否告警 */
    private Boolean alarm;
    /**告警名称 */
    private String alarmName;

    /**告警级别 */
    private Long level;


    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getAlarm() {
        return alarm;
    }

    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
