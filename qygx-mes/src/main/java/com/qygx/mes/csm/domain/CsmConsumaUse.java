package com.qygx.mes.csm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qygx.system.domain.DeviceArchives;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 在用备件对象 csm_consuma_use
 *
 * @author qygx
 * @date 2022-09-07
 */
public class CsmConsumaUse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 在用耗材ID */
    private Long consumaUseId;

    /** 耗材周期ID */
    @Excel(name = "耗材周期ID")
    private Long consumaId;

    /** 规格型号 */
    private String specs;

    private String consumaCode;

    private String consumaName;


    /** 设备ID */
    private Long deviceId;

    /** 设备编号 */
    private String deviceCode;

    /** 设备名称 */
    private String deviceName;

    /** 更换时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更换时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date replaceTime;

    /** 使用时长 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "使用时长", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useTime;

    /** 下一次更换时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "下一次更换时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextReplaceTime;

    /** 更换周期 */
    private Long replaceCycle;

    /** 备件批次 */
    private String batchNo;

    /** 预留字段2 */
    private String attr2;
    /** 预留字段3 */
    private String attr3;

    //过期天数
    private String expireDay;

    private int repairNumber;

    //延长小时数
    private int expandTime;

    private CsmConsuma consuma;

    private DeviceArchives device;


    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getConsumaCode() {
        return consumaCode;
    }

    public void setConsumaCode(String consumaCode) {
        this.consumaCode = consumaCode;
    }

    public String getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(String expireDay) {
        this.expireDay = expireDay;
    }

    public void setConsumaUseId(Long consumaUseId)
    {
        this.consumaUseId = consumaUseId;
    }

    public Long getConsumaUseId()
    {
        return consumaUseId;
    }
    public void setConsumaId(Long consumaId)
    {
        this.consumaId = consumaId;
    }

    public Long getConsumaId()
    {
        return consumaId;
    }
    public void setDeviceId(Long deviceId)
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId()
    {
        return deviceId;
    }
    public void setReplaceTime(Date replaceTime)
    {
        this.replaceTime = replaceTime;
    }

    public Date getReplaceTime()
    {
        return replaceTime;
    }
    public void setUseTime(Date useTime)
    {
        this.useTime = useTime;
    }

    public Date getUseTime()
    {
        return useTime;
    }
    public void setNextReplaceTime(Date nextReplaceTime)
    {
        this.nextReplaceTime = nextReplaceTime;
    }

    public Date getNextReplaceTime()
    {
        return nextReplaceTime;
    }

    public void setbatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getbatchNo()
    {
        return batchNo;
    }

    public void setAttr2(String attr2)
    {
        this.attr2 = attr2;
    }

    public String getAttr2()
    {
        return attr2;
    }

    public void setAttr3(String attr3)
    {
        this.attr3 = attr3;
    }

    public String getAttr3()
    {
        return attr3;
    }

    public int getExpandTime() {
        return expandTime;
    }

    public void setExpandTime(int expandTime) {
        this.expandTime = expandTime;
    }

    public CsmConsuma getConsuma() {
        return consuma;
    }

    public void setConsuma(CsmConsuma consuma) {
        this.consuma = consuma;
    }

    public String getConsumaName() {
        return consumaName;
    }

    public void setConsumaName(String consumaName) {
        this.consumaName = consumaName;
    }

    public DeviceArchives getDevice() {
        return device;
    }

    public void setDevice(DeviceArchives device) {
        this.device = device;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Long getReplaceCycle() {
        return replaceCycle;
    }

    public void setReplaceCycle(Long replaceCycle) {
        this.replaceCycle = replaceCycle;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public int getRepairNumber() {
        return repairNumber;
    }

    public void setRepairNumber(int repairNumber) {
        this.repairNumber = repairNumber;
    }

    @Override
    public String toString() {
        return "CsmConsumaUse{" +
                "consumaUseId=" + consumaUseId +
                ", consumaId=" + consumaId +
                ", deviceId=" + deviceId +
                ", deviceCode='" + deviceCode + '\'' +
                ", replaceTime=" + replaceTime +
                ", useTime=" + useTime +
                ", nextReplaceTime=" + nextReplaceTime +
                ", replaceCycle=" + replaceCycle +
                ", batchNo='" + batchNo + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3='" + attr3 + '\'' +
                ", expireDay='" + expireDay + '\'' +
                ", consumaName='" + consumaName + '\'' +
                ", consuma=" + consuma +
                ", device=" + device +
                '}';
    }
}
