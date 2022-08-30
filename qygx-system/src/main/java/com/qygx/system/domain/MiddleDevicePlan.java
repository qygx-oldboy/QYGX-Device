package com.qygx.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 middle_device_plan
 *
 * @author qygx
 * @date 2022-08-29
 */
public class MiddleDevicePlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 设备id */
    @Excel(name = "设备id")
    private Long deviceId;

    /** 保养计划id */
    @Excel(name = "保养计划id")
    private Long planId;

    /** 本次时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "本次时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date thisTime;

    /** 下次时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下次时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextTime;

    /** 删除标记 */
    private String delFlag;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setDeviceId(Long deviceId)
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId()
    {
        return deviceId;
    }
    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getPlanId()
    {
        return planId;
    }
    public void setThisTime(Date thisTime)
    {
        this.thisTime = thisTime;
    }

    public Date getThisTime()
    {
        return thisTime;
    }
    public void setNextTime(Date nextTime)
    {
        this.nextTime = nextTime;
    }

    public Date getNextTime()
    {
        return nextTime;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("deviceId", getDeviceId())
                .append("planId", getPlanId())
                .append("thisTime", getThisTime())
                .append("nextTime", getNextTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
