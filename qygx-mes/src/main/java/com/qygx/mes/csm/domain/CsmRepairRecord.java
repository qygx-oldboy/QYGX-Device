package com.qygx.mes.csm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 备件修复记录对象 csm_repair_record
 *
 * @author qygx
 * @date 2022-10-10
 */
public class CsmRepairRecord extends BaseEntity
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

    /** 修复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修复时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date repairTime;

    /** 使用时长 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "使用时长", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date useTime;

    /** 备件批次 */
    @Excel(name = "备件批次")
    private String batchNo;

    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String attr1;

    /** 预留字段2 */
    @Excel(name = "预留字段2")
    private String attr2;

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
    public void setRepairTime(Date repairTime)
    {
        this.repairTime = repairTime;
    }

    public Date getRepairTime()
    {
        return repairTime;
    }
    public void setUseTime(Date useTime)
    {
        this.useTime = useTime;
    }

    public Date getUseTime()
    {
        return useTime;
    }
    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo()
    {
        return batchNo;
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
            .append("recordId", getRecordId())
            .append("consumaCode", getConsumaCode())
            .append("consumaName", getConsumaName())
            .append("specs", getSpecs())
            .append("nickName", getNickName())
            .append("repairTime", getRepairTime())
            .append("useTime", getUseTime())
            .append("batchNo", getBatchNo())
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
