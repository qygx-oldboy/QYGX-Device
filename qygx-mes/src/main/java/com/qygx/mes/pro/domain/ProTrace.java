package com.qygx.mes.pro.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;
import com.qygx.system.domain.DvMachineryRun;

/**
 * 生产追溯对象 pro_trace
 *
 * @author qygx
 * @date 2022-09-12
 */
public class ProTrace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 追溯ID */
    private Long traceId;

    /** 产品物料编码 */
    @Excel(name = "产品物料编码")
    private String itemCode;

    /** 产品物料名称 */
    @Excel(name = "产品物料名称")
    private String itemName;

    /** 工序ID */
    private Long processId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String processCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

    /** 设备ID */
    private Long machineryId;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String machineryCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String machineryName;

    /** 作业人员 */
    @Excel(name = "作业人员")
    private String operator;

    /** 开始生产时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始生产时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 生产时长 */
    @Excel(name = "生产时长")
    private Long duration;

    /** 完成生产时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "完成生产时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 设备运行参数 */
    @Excel(name = "设备运行参数")
    private String deviceParam;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;



    public void setTraceId(Long traceId)
    {
        this.traceId = traceId;
    }

    public Long getTraceId()
    {
        return traceId;
    }
    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }

    public String getItemCode()
    {
        return itemCode;
    }
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemName()
    {
        return itemName;
    }
    public void setProcessId(Long processId)
    {
        this.processId = processId;
    }

    public Long getProcessId()
    {
        return processId;
    }
    public void setProcessCode(String processCode)
    {
        this.processCode = processCode;
    }

    public String getProcessCode()
    {
        return processCode;
    }
    public void setProcessName(String processName)
    {
        this.processName = processName;
    }

    public String getProcessName()
    {
        return processName;
    }
    public void setMachineryId(Long machineryId)
    {
        this.machineryId = machineryId;
    }

    public Long getMachineryId()
    {
        return machineryId;
    }
    public void setMachineryCode(String machineryCode)
    {
        this.machineryCode = machineryCode;
    }

    public String getMachineryCode()
    {
        return machineryCode;
    }
    public void setMachineryName(String machineryName)
    {
        this.machineryName = machineryName;
    }

    public String getMachineryName()
    {
        return machineryName;
    }
    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getOperator()
    {
        return operator;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setDuration(Long duration)
    {
        this.duration = duration;
    }

    public Long getDuration()
    {
        return duration;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setdeviceParam(String deviceParam)
    {
        this.deviceParam = deviceParam;
    }

    public String getdeviceParam()
    {
        return deviceParam;
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
        return "ProTrace{" +
                "traceId=" + traceId +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", processId=" + processId +
                ", processCode='" + processCode + '\'' +
                ", processName='" + processName + '\'' +
                ", machineryId=" + machineryId +
                ", machineryCode='" + machineryCode + '\'' +
                ", machineryName='" + machineryName + '\'' +
                ", operator='" + operator + '\'' +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", endTime=" + endTime +
                ", deviceParam='" + deviceParam + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
