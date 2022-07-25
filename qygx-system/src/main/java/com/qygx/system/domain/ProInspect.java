package com.qygx.system.domain;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * inspect对象 inspect
 *
 * @author qygx
 * @date 2022-07-22
 */
public class ProInspect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "序号", cellType = Excel.ColumnType.NUMERIC)
    private Long id;

    /** 批次号 */
    @Excel(name = "批次号")
    private String qrCode;

    /** 生产编号 */
    @Excel(name = "生产编号")
    private String productCode;

    /** 生产类型 */
    @Excel(name = "生产类型")
    private String productType;

    /** 物料类型 */
    @Excel(name = "物料类型")
    private String materialType;

    /** 材料 */
    @Excel(name = "材料")
    private String materialQuality;

    /** 标准成盘数 */
    @Excel(name = "标准成盘数")
    private Integer quantityStd;

    /** 检验员 */
    @Excel(name = "检验员")
    private String inspector;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 检验用时 */
//    @JsonFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "检验用时", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date runTime;

    /** 良率 */
    @Excel(name = "良率")
    private Integer okPercent;

    /** 不良率 */
    @Excel(name = "不良率")
    private Integer ngPercent;

    /** 检验数 */
    @Excel(name = "检验数")
    private Integer inspectedNum;

    /** 合格数 */
    @Excel(name = "合格数")
    private Integer okNum;

    /** 不良数 */
    @Excel(name = "不良数")
    private Integer ngNum;

    /** 材质不良 */
    @Excel(name = "材质不良")
    private Integer ng1;

    /** 失园 */
    @Excel(name = "失园")
    private Integer ng2;

    /** 内裂 */
    @Excel(name = "内裂")
    private Integer ng3;

    /** 坑点 */
    @Excel(name = "坑点")
    private Integer ng4;

    /** 尺寸不良 */
    @Excel(name = "尺寸不良")
    private Integer ng5;

    /** 发雾 */
    @Excel(name = "发雾")
    private Integer ng6;

    /** 伤痕 */
    @Excel(name = "伤痕")
    private Integer ng7;

    /** 印渍 */
    @Excel(name = "印渍")
    private Integer ng8;

    /** 不洁 */
    @Excel(name = "不洁")
    private Integer ng9;

    /** 其他 */
    @Excel(name = "其他")
    private Integer ng10;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 状态 */
    private Integer status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setQrCode(String qrCode)
    {
        this.qrCode = qrCode;
    }

    public String getQrCode()
    {
        return qrCode;
    }
    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }
    public void setProductType(String productType)
    {
        this.productType = productType;
    }

    public String getProductType()
    {
        return productType;
    }
    public void setMaterialType(String materialType)
    {
        this.materialType = materialType;
    }

    public String getMaterialType()
    {
        return materialType;
    }
    public void setMaterialQuality(String materialQuality)
    {
        this.materialQuality = materialQuality;
    }

    public String getMaterialQuality()
    {
        return materialQuality;
    }
    public void setQuantityStd(Integer quantityStd)
    {
        this.quantityStd = quantityStd;
    }

    public Integer getQuantityStd()
    {
        return quantityStd;
    }
    public void setInspector(String inspector)
    {
        this.inspector = inspector;
    }

    public String getInspector()
    {
        return inspector;
    }
    public void setBeginTime(Date beginTime)
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime()
    {
        return beginTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setOkPercent(Integer okPercent)
    {
        this.okPercent = okPercent;
    }

    public Integer getOkPercent()
    {
        return okPercent;
    }
    public void setNgPercent(Integer ngPercent)
    {
        this.ngPercent = ngPercent;
    }

    public Integer getNgPercent()
    {
        return ngPercent;
    }
    public void setInspectedNum(Integer inspectedNum)
    {
        this.inspectedNum = inspectedNum;
    }

    public Integer getInspectedNum()
    {
        return inspectedNum;
    }
    public void setOkNum(Integer okNum)
    {
        this.okNum = okNum;
    }

    public Integer getOkNum()
    {
        return okNum;
    }
    public void setNgNum(Integer ngNum)
    {
        this.ngNum = ngNum;
    }

    public Integer getNgNum()
    {
        return ngNum;
    }
    public void setNg1(Integer ng1)
    {
        this.ng1 = ng1;
    }

    public Integer getNg1()
    {
        return ng1;
    }
    public void setNg2(Integer ng2)
    {
        this.ng2 = ng2;
    }

    public Integer getNg2()
    {
        return ng2;
    }
    public void setNg3(Integer ng3)
    {
        this.ng3 = ng3;
    }

    public Integer getNg3()
    {
        return ng3;
    }
    public void setNg4(Integer ng4)
    {
        this.ng4 = ng4;
    }

    public Integer getNg4()
    {
        return ng4;
    }
    public void setNg5(Integer ng5)
    {
        this.ng5 = ng5;
    }

    public Integer getNg5()
    {
        return ng5;
    }
    public void setNg6(Integer ng6)
    {
        this.ng6 = ng6;
    }

    public Integer getNg6()
    {
        return ng6;
    }
    public void setNg7(Integer ng7)
    {
        this.ng7 = ng7;
    }

    public Integer getNg7()
    {
        return ng7;
    }
    public void setNg8(Integer ng8)
    {
        this.ng8 = ng8;
    }

    public Integer getNg8()
    {
        return ng8;
    }
    public void setNg9(Integer ng9)
    {
        this.ng9 = ng9;
    }

    public Integer getNg9()
    {
        return ng9;
    }
    public void setNg10(Integer ng10)
    {
        this.ng10 = ng10;
    }

    public Integer getNg10()
    {
        return ng10;
    }
    public void setResult(String result)
    {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ProInspect{" +
                "id=" + id +
                ", qrCode='" + qrCode + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productType='" + productType + '\'' +
                ", materialType='" + materialType + '\'' +
                ", materialQuality='" + materialQuality + '\'' +
                ", quantityStd=" + quantityStd +
                ", inspector='" + inspector + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", runTime=" + runTime +
                ", okPercent=" + okPercent +
                ", ngPercent=" + ngPercent +
                ", inspectedNum=" + inspectedNum +
                ", okNum=" + okNum +
                ", ngNum=" + ngNum +
                ", ng1=" + ng1 +
                ", ng2=" + ng2 +
                ", ng3=" + ng3 +
                ", ng4=" + ng4 +
                ", ng5=" + ng5 +
                ", ng6=" + ng6 +
                ", ng7=" + ng7 +
                ", ng8=" + ng8 +
                ", ng9=" + ng9 +
                ", ng10=" + ng10 +
                ", result='" + result + '\'' +
                ", status=" + status +
                '}';
    }

    public String getResult()
    {
        return result;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }


}
