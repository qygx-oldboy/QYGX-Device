package com.qygx.mes.csm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * 备件周期项对象 csm_consuma_line
 *
 * @author qygx
 * @date 2022-11-25
 */
public class CsmConsumaLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long lineId;

    /** 备件id */
    private Long consumaId;

    /** 工序 */
    @Excel(name = "工序")
    private String processName;

    /** 更换周期 */
    @Excel(name = "更换周期")
    private Long replaceCycle;

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }
    public void setProcessName(String processName)
    {
        this.processName = processName;
    }

    public String getProcessName()
    {
        return processName;
    }
    public void setReplaceCycle(Long replaceCycle)
    {
        this.replaceCycle = replaceCycle;
    }

    public Long getReplaceCycle()
    {
        return replaceCycle;
    }

    public Long getConsumaId() {
        return consumaId;
    }

    public void setConsumaId(Long consumaId) {
        this.consumaId = consumaId;
    }

    @Override
    public String toString() {
        return "CsmConsumaLine{" +
                "lineId=" + lineId +
                ", consumaId=" + consumaId +
                ", processName='" + processName + '\'' +
                ", replaceCycle=" + replaceCycle +
                '}';
    }
}
