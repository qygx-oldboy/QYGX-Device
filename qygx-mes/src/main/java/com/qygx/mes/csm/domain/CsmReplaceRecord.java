package com.qygx.mes.csm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 备件更换记录对象 csm_replace_record
 *
 * @author qygx
 * @date 2022-09-22
 */
public class CsmReplaceRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 备件编号 */
    @Excel(name = "备件编号")
    private String consumaCode;

    /** 备件名称 */
    @Excel(name = "备件名称")
    private String consumaName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String specs;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 更换时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更换时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date replaceTime;

    /** 备件批次 */
    @Excel(name = "备件批次")
    private String batchNo;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 预留字段3 */
    @Excel(name = "预留字段3")
    private Long consumaUseId;

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
    public void setConsumaCode(String consumaCode)
    {
        this.consumaCode = consumaCode;
    }

    public String getConsumaCode()
    {
        return consumaCode;
    }
    public void setConsumaName(String consumaName)
    {
        this.consumaName = consumaName;
    }

    public String getConsumaName()
    {
        return consumaName;
    }
    public void setSpecs(String specs)
    {
        this.specs = specs;
    }

    public String getSpecs()
    {
        return specs;
    }
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getNickName()
    {
        return nickName;
    }
    public void setReplaceTime(Date replaceTime)
    {
        this.replaceTime = replaceTime;
    }

    public Date getReplaceTime()
    {
        return replaceTime;
    }
    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo()
    {
        return batchNo;
    }
    public void setdeviceCode(String deviceCode)
    {
        this.deviceCode = deviceCode;
    }

    public String getdeviceCode()
    {
        return deviceCode;
    }
    public void setdeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getdeviceName()
    {
        return deviceName;
    }

    public Long getConsumaUseId() {
        return consumaUseId;
    }

    public void setConsumaUseId(Long consumaUseId) {
        this.consumaUseId = consumaUseId;
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
            .append("consumaCode", getConsumaCode())
            .append("consumaName", getConsumaName())
            .append("specs", getSpecs())
            .append("nickName", getNickName())
            .append("replaceTime", getReplaceTime())
            .append("batchNo", getBatchNo())
            .append("remark", getRemark())
            .append("deviceCode", getdeviceCode())
            .append("deviceName", getdeviceName())
            .append("consumaUseId", getConsumaUseId())
            .append("attr4", getAttr4())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
