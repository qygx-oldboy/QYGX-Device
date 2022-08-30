package com.qygx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 保养记录明细对象 device_maintain_detail
 *
 * @author qygx
 * @date 2022-08-30
 */
public class DeviceMaintainDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long maintainDetailId;

    /** 保养单id */
    @Excel(name = "保养单id")
    private Long maintainSheetId;

    /** 保养内容 */
    @Excel(name = "保养内容")
    private String content;

    /** 要求 */
    @Excel(name = "要求")
    private String standard;

    /** 状态(0正常 1不正常 2修复) */
    @Excel(name = "状态(0正常 1不正常 2修复)")
    private String isNormal;

    public void setMaintainDetailId(Long maintainDetailId)
    {
        this.maintainDetailId = maintainDetailId;
    }

    public Long getMaintainDetailId()
    {
        return maintainDetailId;
    }
    public void setMaintainSheetId(Long maintainSheetId)
    {
        this.maintainSheetId = maintainSheetId;
    }

    public Long getMaintainSheetId()
    {
        return maintainSheetId;
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
    public void setIsNormal(String isNormal)
    {
        this.isNormal = isNormal;
    }

    public String getIsNormal()
    {
        return isNormal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("maintainDetailId", getMaintainDetailId())
                .append("maintainSheetId", getMaintainSheetId())
                .append("content", getContent())
                .append("standard", getStandard())
                .append("isNormal", getIsNormal())
                .append("remark", getRemark())
                .toString();
    }
}
