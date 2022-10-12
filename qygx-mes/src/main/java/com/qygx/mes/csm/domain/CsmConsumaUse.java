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

    /** 设备ID */
    private Long deviceId;

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

    /** 过期天数 */
    private Long expireDay;

    /** 更换周期 */
    private Long replaceCycle;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 备件批次 */
    private String batchNo;

    /** 预留字段2 */
    private String attr2;
    /** 预留字段3 */
    private String attr3;

    /** 预留字段4 */
    private Long repairNumber;

    /** 更换周期 */
    private Long repairCycle;



    /** 删除修复 标记 */
    private String delFlag;

    /** 修复 标记 */
    private Boolean repairFlag;

    public Boolean getRepairFlag() {
        return repairFlag;
    }

    public void setRepairFlag(Boolean repairFlag) {
        this.repairFlag = repairFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /** 修复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date repairTime;

    /** 下一次修复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "下一次修复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextRepairTime;

    private String consumaName;

    private CsmConsuma consuma;

    private DeviceArchives device;

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

    public Long getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Long expireDay) {
        this.expireDay = expireDay;
    }

    public void setRepairNumber(Long repairNumber)
    {
        this.repairNumber = repairNumber;
    }

    public Long getRepairNumber()
    {
        return repairNumber;
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



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getReplaceCycle() {
        return replaceCycle;
    }

    public void setReplaceCycle(Long replaceCycle) {
        this.replaceCycle = replaceCycle;
    }

    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public Date getNextRepairTime() {
        return nextRepairTime;
    }

    public void setNextRepairTime(Date nextRepairTime) {
        this.nextRepairTime = nextRepairTime;
    }

    public Long getRepairCycle() {
        return repairCycle;
    }

    public void setRepairCycle(Long repairCycle) {
        this.repairCycle = repairCycle;
    }

    @Override
    public String toString() {
        return "CsmConsumaUse{" +
                "consumaUseId=" + consumaUseId +
                ", consumaId=" + consumaId +
                ", deviceId=" + deviceId +
                ", replaceTime=" + replaceTime +
                ", useTime=" + useTime +
                ", nextReplaceTime=" + nextReplaceTime +
                ", expireDay=" + expireDay +
                ", replaceCycle=" + replaceCycle +
                ", status='" + status + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3='" + attr3 + '\'' +
                ", repairNumber=" + repairNumber +
                ", repairCycle=" + repairCycle +
                ", repairTime=" + repairTime +
                ", nextRepairTime=" + nextRepairTime +
                ", consumaName='" + consumaName + '\'' +
                ", consuma=" + consuma +
                ", device=" + device +
                '}';
    }
}
