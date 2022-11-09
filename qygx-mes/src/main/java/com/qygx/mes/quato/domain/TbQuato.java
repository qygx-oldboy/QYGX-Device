package com.qygx.mes.quato.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 指标管理对象 tb_quato
 *
 * @author qygx
 * @date 2022-10-18
 */
public class TbQuato extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 指标名称 */
    @Excel(name = "指标名称")
    private String name;

    /** 指标单位 */
    @Excel(name = "指标单位")
    private String unit;

    /** 报文主题 */
    @Excel(name = "报文主题")
    private String subject;

    /** 指标值字段 */
    @Excel(name = "指标值字段")
    private String valueKey;

    /** 设备识别码字段 */
    @Excel(name = "设备识别码字段")
    private String snKey;

    /** web钩子 */
    @Excel(name = "web钩子")
    private String webhook;

    /** 指标字段类型 Double、Int、Boolean */
    @Excel(name = "指标字段类型 Double、Int、Boolean")
    private String valueType;

    /** 参考值 */
    @Excel(name = "参考值")
    private String referenceValue;

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
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getSubject()
    {
        return subject;
    }
    public void setValueKey(String valueKey)
    {
        this.valueKey = valueKey;
    }

    public String getValueKey()
    {
        return valueKey;
    }
    public void setSnKey(String snKey)
    {
        this.snKey = snKey;
    }

    public String getSnKey()
    {
        return snKey;
    }
    public void setWebhook(String webhook)
    {
        this.webhook = webhook;
    }

    public String getWebhook()
    {
        return webhook;
    }
    public void setValueType(String valueType)
    {
        this.valueType = valueType;
    }

    public String getValueType()
    {
        return valueType;
    }
    public void setReferenceValue(String referenceValue)
    {
        this.referenceValue = referenceValue;
    }

    public String getReferenceValue()
    {
        return referenceValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("unit", getUnit())
            .append("subject", getSubject())
            .append("valueKey", getValueKey())
            .append("snKey", getSnKey())
            .append("webhook", getWebhook())
            .append("valueType", getValueType())
            .append("referenceValue", getReferenceValue())
            .toString();
    }
}
