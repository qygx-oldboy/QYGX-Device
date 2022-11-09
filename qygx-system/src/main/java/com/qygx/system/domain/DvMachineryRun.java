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
public class DvMachineryRun extends BaseEntity implements Cloneable
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


    /** 班次名称 */
    @Excel(name = "班次名称")
    private String shiftName;



    /** 工序id */
    @Excel(name = "工序id")
    private Long processId;

    /** 工序编码 */
    @Excel(name = "工序编号")
    private String processCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operator;

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


    public void setShiftName(String shiftName)
    {
        this.shiftName = shiftName;
    }

    public String getShiftName()
    {
        return shiftName;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "DvMachineryRun{" +
                "recordId=" + recordId +
                ", deviceId=" + deviceId +
                ", machineryCode='" + machineryCode + '\'' +
                ", machineryName='" + machineryName + '\'' +
                ", recordDate=" + recordDate +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", runTime=" + runTime +
                ", planDownTime=" + planDownTime +
                ", excepitonDownTime=" + excepitonDownTime +
                ", movingTime=" + movingTime +
                ", utilizationRate=" + utilizationRate +
                ", shiftName='" + shiftName + '\'' +
                ", processId=" + processId +
                ", processCode='" + processCode + '\'' +
                ", processName='" + processName + '\'' +
                '}';
    }

    //实现Cloneable的clone方法，将clone定义为public
    public DvMachineryRun clone() throws CloneNotSupportedException {
        return (DvMachineryRun) super.clone();
    }
}
