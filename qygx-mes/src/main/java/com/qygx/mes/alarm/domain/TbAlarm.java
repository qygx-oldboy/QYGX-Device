package com.qygx.mes.alarm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 告警管理对象 tb_alarm
 * 
 * @author qygx
 * @date 2022-10-19
 */
public class TbAlarm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 报警指标名称 */
    @Excel(name = "报警指标名称")
    private String name;

    /** 关联指标名称 */
    @Excel(name = "关联指标名称")
    private Long quotaId;

    /** 运算符 */
    @Excel(name = "运算符")
    private String operator;

    /** 报警阈值 */
    @Excel(name = "报警阈值")
    private Long threshold;

    /** 抱紧级别 1:一般 2:严重 */
    @Excel(name = "抱紧级别 1:一般 2:严重")
    private Long level;

    /** 沉默周期 */
    @Excel(name = "沉默周期")
    private Long cycle;

    /** web钩子 */
    @Excel(name = "web钩子")
    private String webhook;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setQuotaId(Long quotaId) 
    {
        this.quotaId = quotaId;
    }

    public Long getQuotaId() 
    {
        return quotaId;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setThreshold(Long threshold) 
    {
        this.threshold = threshold;
    }

    public Long getThreshold() 
    {
        return threshold;
    }
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }
    public void setCycle(Long cycle) 
    {
        this.cycle = cycle;
    }

    public Long getCycle() 
    {
        return cycle;
    }
    public void setWebhook(String webhook) 
    {
        this.webhook = webhook;
    }

    public String getWebhook() 
    {
        return webhook;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("quotaId", getQuotaId())
            .append("operator", getOperator())
            .append("threshold", getThreshold())
            .append("level", getLevel())
            .append("cycle", getCycle())
            .append("webhook", getWebhook())
            .toString();
    }
}
