package com.qygx.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 设备运行记录对象 dv_machinery_run
 *
 * @author qygx
 * @date 2022-09-20
 */
public class DvMachineryRun extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long deviceId;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String machineryCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String machineryName;

    /** 记录日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /** 开机时间   (改为记录班次上班时间） */
    @Excel(name = "开机时间")
    private String startTime;

    /** 停机时间   (改为记录班次下班时间）*/

    @Excel(name = "停行时间")
    private String endTime;

    /** 运行时间 */
    @Excel(name = "运行时间")
    private Long runTime;

    /** 计划停机 */
    @Excel(name = "计划停机")
    private Long planDownTime;

    /** 异常停机时长 */
    @Excel(name = "异常停机时长")
    private Long excepitonDownTime;

    /** 稼动时间 */
    @Excel(name = "稼动时间")
    private Long movingTime;

    /** 稼动率 */
    @Excel(name = "稼动率")
    private Double utilizationRate;


    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String attr1;

    /** 班次名称 */
    @Excel(name = "班次名称")
    private String shiftName;

    /** 预留字段3 */
    @Excel(name = "预留字段3")
    private Long attr3;

    /** 预留字段4 */
    @Excel(name = "预留字段4")
    private Long attr4;

    public void setRecordId(Long recordId)
    {
        this.recordId = recordId;
    }

    public Long getRecordId()
    {
        return recordId;
    }

    public void setDeviceId(Long deviceId)
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId()
    {
        return deviceId;
    }
    public void setMachineryCode(String machineryCode)
    {
        this.machineryCode = machineryCode;
    }

    public String getMachineryCode()
    {
        return machineryCode;
    }
    public void setMachineryName(String machineryName)
    {
        this.machineryName = machineryName;
    }

    public String getMachineryName()
    {
        return machineryName;
    }
    public void setRecordDate(Date recordDate)
    {
        this.recordDate = recordDate;
    }

    public Date getRecordDate()
    {
        return recordDate;
    }
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getStartTime()
    {
        return startTime;
    }
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public String getEndTime()
    {
        return endTime;
    }
    public void setRunTime(Long runTime)
    {
        this.runTime = runTime;
    }

    public Long getRunTime()
    {
        return runTime;
    }
    public void setPlanDownTime(Long planDownTime)
    {
        this.planDownTime = planDownTime;
    }

    public Long getPlanDownTime()
    {
        return planDownTime;
    }
    public void setExcepitonDownTime(Long excepitonDownTime)
    {
        this.excepitonDownTime = excepitonDownTime;
    }

    public Long getExcepitonDownTime()
    {
        return excepitonDownTime;
    }
    public void setMovingTime(Long movingTime)
    {
        this.movingTime = movingTime;
    }

    public Long getMovingTime()
    {
        return movingTime;
    }
    public void setUtilizationRate(Double utilizationRate)
    {
        this.utilizationRate = utilizationRate;
    }

    public Double getUtilizationRate()
    {
        return utilizationRate;
    }

    public void setAttr1(String attr1)
    {
        this.attr1 = attr1;
    }

    public String getAttr1()
    {
        return attr1;
    }
    public void setShiftName(String shiftName)
    {
        this.shiftName = shiftName;
    }

    public String getShiftName()
    {
        return shiftName;
    }
    public void setAttr3(Long attr3)
    {
        this.attr3 = attr3;
    }

    public Long getAttr3()
    {
        return attr3;
    }
    public void setAttr4(Long attr4)
    {
        this.attr4 = attr4;
    }

    public Long getAttr4()
    {
        return attr4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("recordId", getRecordId())
                .append("deviceId", getDeviceId())
                .append("machineryCode", getMachineryCode())
                .append("machineryName", getMachineryName())
                .append("recordDate", getRecordDate())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("runTime", getRunTime())
                .append("planDownTime", getPlanDownTime())
                .append("excepitonDownTime", getExcepitonDownTime())
                .append("movingTime", getMovingTime())
                .append("utilizationRate", getUtilizationRate())
                .append("attr1", getAttr1())
                .append("shiftName", getShiftName())
                .append("attr3", getAttr3())
                .append("attr4", getAttr4())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
