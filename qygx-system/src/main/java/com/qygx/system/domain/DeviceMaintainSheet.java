package com.qygx.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qygx.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 保养记录对象 device_maintain_sheet
 *
 * @author qygx
 * @date 2022-08-30
 */
public class DeviceMaintainSheet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long maintainSheetId;

    /** 保养工单 */
    @Excel(name = "保养工单")
    private String sheetNumber;

    /** 保养人id */
    @Excel(name = "保养人id")
    private Long maintainerId;

    /** 单据状态 */
    @Excel(name = "单据状态")
    private String status;


    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 设备id */
    @Excel(name = "设备id")
    private Long deviceId;


    /** 截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    /** 保养记录明细信息 */
    private List<DeviceMaintainDetail> deviceMaintainDetailList;

    private SysUser maintainer;

    private DeviceArchives device;


    private String maintainerName;

    private String deviceName;


    public String getMaintainerName() {
        return maintainerName;
    }

    public void setMaintainerName(String maintainerName) {
        this.maintainerName = maintainerName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public DeviceArchives getDevice() {
        return device;
    }

    public void setDevice(DeviceArchives device) {
        this.device = device;
    }

    public SysUser getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(SysUser maintainer) {
        this.maintainer = maintainer;
    }

    public void setMaintainSheetId(Long maintainSheetId)
    {
        this.maintainSheetId = maintainSheetId;
    }

    public Long getMaintainSheetId()
    {
        return maintainSheetId;
    }
    public void setSheetNumber(String sheetNumber)
    {
        this.sheetNumber = sheetNumber;
    }

    public String getSheetNumber()
    {
        return sheetNumber;
    }
    public void setMaintainerId(Long maintainerId)
    {
        this.maintainerId = maintainerId;
    }

    public Long getMaintainerId()
    {
        return maintainerId;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setDeviceId(Long deviceId)
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId()
    {
        return deviceId;
    }

    public List<DeviceMaintainDetail> getDeviceMaintainDetailList()
    {
        return deviceMaintainDetailList;
    }

    public void setDeviceMaintainDetailList(List<DeviceMaintainDetail> deviceMaintainDetailList)
    {
        this.deviceMaintainDetailList = deviceMaintainDetailList;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("maintainSheetId", getMaintainSheetId())
                .append("sheetNumber", getSheetNumber())
                .append("maintainerId", getMaintainerId())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("endTime", getEndTime())
                .append("deviceId", getDeviceId())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("deviceMaintainDetailList", getDeviceMaintainDetailList())
                .toString();
    }
}
