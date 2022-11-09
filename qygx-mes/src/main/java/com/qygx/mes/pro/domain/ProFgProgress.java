package com.qygx.mes.pro.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 返工进度对象 pro_fg_progress
 *
 * @author qygx
 * @date 2022-10-27
 */
public class ProFgProgress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchCode;

    /** 返工品数量  */
    @Excel(name = "返工品数量 ")
    private Long reworkQuantity;

    /** 精抛数量 */
    @Excel(name = "精抛数量")
    private Long polishQuantity;

    /** 检验数量 */
    @Excel(name = "检验数量")
    private Long inspectQuantity;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setBatchCode(String batchCode)
    {
        this.batchCode = batchCode;
    }

    public String getBatchCode()
    {
        return batchCode;
    }
    public void setReworkQuantity(Long reworkQuantity)
    {
        this.reworkQuantity = reworkQuantity;
    }

    public Long getReworkQuantity()
    {
        return reworkQuantity;
    }
    public void setPolishQuantity(Long polishQuantity)
    {
        this.polishQuantity = polishQuantity;
    }

    public Long getPolishQuantity()
    {
        return polishQuantity;
    }
    public void setInspectQuantity(Long inspectQuantity)
    {
        this.inspectQuantity = inspectQuantity;
    }

    public Long getInspectQuantity()
    {
        return inspectQuantity;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("batchCode", getBatchCode())
            .append("reworkQuantity", getReworkQuantity())
            .append("polishQuantity", getPolishQuantity())
            .append("inspectQuantity", getInspectQuantity())
            .append("status", getStatus())
            .toString();
    }
}
