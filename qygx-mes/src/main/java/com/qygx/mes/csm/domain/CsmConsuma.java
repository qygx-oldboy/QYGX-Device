package com.qygx.mes.csm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 备件档案对象 csm_consuma
 *
 * @author qygx
 * @date 2022-09-09
 */
public class CsmConsuma extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 备件ID */
    private Long consumaId;

    /** 备件编号 */
    @Excel(name = "备件编号")
    private String consumaCode;

    /** 备件名称 */
    @Excel(name = "备件名称")
    private String consumaName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String specs;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private String enableFlag;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;



    public void setConsumaId(Long consumaId)
    {
        this.consumaId = consumaId;
    }

    public Long getConsumaId()
    {
        return consumaId;
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
    public void setEnableFlag(String enableFlag)
    {
        this.enableFlag = enableFlag;
    }

    public String getEnableFlag()
    {
        return enableFlag;
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


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("consumaId", getConsumaId())
            .append("consumaCode", getConsumaCode())
            .append("consumaName", getConsumaName())
            .append("specs", getSpecs())
            .append("enableFlag", getEnableFlag())
            .append("remark", getRemark())
            .append("attr1", getAttr1())
            .append("attr2", getAttr2())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
