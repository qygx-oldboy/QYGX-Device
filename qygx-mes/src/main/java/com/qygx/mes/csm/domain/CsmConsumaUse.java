package com.qygx.mes.csm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更换时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date replaceTime;

    /** 使用时长 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用时长", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useTime;

    /** 下一次更换时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下一次更换时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextReplaceTime;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

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
    public void setAttr1(String attr1) 
    {
        this.attr1 = attr1;
    }

    public String getAttr1() 
    {
        return attr1;
    }
    public void setAttr2(String attr2) 
    {
        this.attr2 = attr2;
    }

    public String getAttr2() 
    {
        return attr2;
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
            .append("consumaUseId", getConsumaUseId())
            .append("consumaId", getConsumaId())
            .append("deviceId", getDeviceId())
            .append("replaceTime", getReplaceTime())
            .append("useTime", getUseTime())
            .append("nextReplaceTime", getNextReplaceTime())
            .append("remark", getRemark())
            .append("attr1", getAttr1())
            .append("attr2", getAttr2())
            .append("attr3", getAttr3())
            .append("attr4", getAttr4())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
