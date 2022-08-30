package com.qygx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 保养计划明细对象 maintain_plan_detail
 * 
 * @author qygx
 * @date 2022-08-12
 */
public class MaintainPlanDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 保养详情id */
    private Long planDetailId;

    /** 保养内容 */
    @Excel(name = "保养内容")
    private String content;

    /** 标准 */
    @Excel(name = "标准")
    private String standard;

    /** 保养计划id */
    @Excel(name = "保养计划id")
    private Long planId;

    public void setPlanDetailId(Long planDetailId) 
    {
        this.planDetailId = planDetailId;
    }

    public Long getPlanDetailId() 
    {
        return planDetailId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setStandard(String standard) 
    {
        this.standard = standard;
    }

    public String getStandard() 
    {
        return standard;
    }
    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("planDetailId", getPlanDetailId())
            .append("content", getContent())
            .append("standard", getStandard())
            .append("planId", getPlanId())
            .toString();
    }
}
