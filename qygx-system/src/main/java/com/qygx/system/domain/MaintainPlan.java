package com.qygx.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 保养计划对象 maintain_plan
 *
 * @author qygx
 * @date 2022-08-30
 */
public class MaintainPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long planId;

    /** 间隔天数 */
    @Excel(name = "间隔天数")
    private Long intervalDays;

    /** 启用状态 */
    @Excel(name = "启用状态")
    private String useState;

    /** 保养人 */
    @Excel(name = "保养人")
    private Long maintainerId;

    /** 保养人名称 */
    @Excel(name = "保养人名称")
    private String maintainerNickName;

    /** 稽核人 */
    @Excel(name = "稽核人")
    private Long auditorId;

    /** 稽核人名称 */
    @Excel(name = "稽核人名称")
    private String auditorNickName;

    /** 保养等级 */
    @Excel(name = "保养等级")
    private Long level;

    /** 计划名称 */
    @Excel(name = "计划名称")
    private String name;

    /** 首次保养计划时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "首次保养计划时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstMaintainTime;

    /** 最后一次保养时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后一次保养时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastMaintainTime;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceType;

    /** 保养计划明细信息 */
    private List<MaintainPlanDetail> maintainPlanDetailList;

    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getPlanId()
    {
        return planId;
    }
    public void setIntervalDays(Long intervalDays)
    {
        this.intervalDays = intervalDays;
    }

    public Long getIntervalDays()
    {
        return intervalDays;
    }
    public void setUseState(String useState)
    {
        this.useState = useState;
    }

    public String getUseState()
    {
        return useState;
    }
    public void setMaintainerId(Long maintainerId)
    {
        this.maintainerId = maintainerId;
    }

    public Long getMaintainerId()
    {
        return maintainerId;
    }
    public void setMaintainerNickName(String maintainerNickName)
    {
        this.maintainerNickName = maintainerNickName;
    }

    public String getMaintainerNickName()
    {
        return maintainerNickName;
    }
    public void setAuditorId(Long auditorId)
    {
        this.auditorId = auditorId;
    }

    public Long getAuditorId()
    {
        return auditorId;
    }
    public void setAuditorNickName(String auditorNickName)
    {
        this.auditorNickName = auditorNickName;
    }

    public String getAuditorNickName()
    {
        return auditorNickName;
    }
    public void setLevel(Long level)
    {
        this.level = level;
    }

    public Long getLevel()
    {
        return level;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setFirstMaintainTime(Date firstMaintainTime)
    {
        this.firstMaintainTime = firstMaintainTime;
    }

    public Date getFirstMaintainTime()
    {
        return firstMaintainTime;
    }
    public void setLastMaintainTime(Date lastMaintainTime)
    {
        this.lastMaintainTime = lastMaintainTime;
    }

    public Date getLastMaintainTime()
    {
        return lastMaintainTime;
    }
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    public String getDeviceType()
    {
        return deviceType;
    }

    public List<MaintainPlanDetail> getMaintainPlanDetailList()
    {
        return maintainPlanDetailList;
    }

    public void setMaintainPlanDetailList(List<MaintainPlanDetail> maintainPlanDetailList)
    {
        this.maintainPlanDetailList = maintainPlanDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("planId", getPlanId())
                .append("intervalDays", getIntervalDays())
                .append("useState", getUseState())
                .append("maintainerId", getMaintainerId())
                .append("maintainerNickName", getMaintainerNickName())
                .append("auditorId", getAuditorId())
                .append("auditorNickName", getAuditorNickName())
                .append("level", getLevel())
                .append("name", getName())
                .append("firstMaintainTime", getFirstMaintainTime())
                .append("lastMaintainTime", getLastMaintainTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("deviceType", getDeviceType())
                .append("maintainPlanDetailList", getMaintainPlanDetailList())
                .toString();
    }
}
