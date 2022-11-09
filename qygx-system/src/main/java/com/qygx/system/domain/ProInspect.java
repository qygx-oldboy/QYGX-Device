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


    /** 参数主键 */
    @Excel(name = "参数主键", cellType = Excel.ColumnType.NUMERIC)
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
    private Double okPercent;

    /** 不良率 */
    @Excel(name = "不良率")
    private Double ngPercent;

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


    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 处理结果 */

    private String result;


    /** 原因分析 */

    private String remark1;
    /** 临时措施 */

    private String remark2;

    /** 长期措施 */

    private String remark3;
    /** 效果验证 */

    private String remark4;

    //临时字段  不合格原因
    private String ngStr;

    //良率阈值 0.50
    private double lianglv;

    public double getLianglv() {
        return lianglv;
    }

    public void setLianglv(double lianglv) {
        this.lianglv = lianglv;
    }



    public String getNgStr() {
        return ngStr;
    }

    public void setNgStr(String ngStr) {
        this.ngStr = ngStr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getOkPercent() {
        return okPercent;
    }

    public void setOkPercent(Double okPercent) {
        this.okPercent = okPercent;
    }

    public Double getNgPercent() {
        return ngPercent;
    }

    public void setNgPercent(Double ngPercent) {
        this.ngPercent = ngPercent;
    }

    public void setInspectedNum(Integer inspectedNum) {
        this.inspectedNum = inspectedNum;
    }

    /** 状态 */
    private Integer status;

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

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



    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProInspect)) return false;

        ProInspect inspect = (ProInspect) o;

        if (getId() != null ? !getId().equals(inspect.getId()) : inspect.getId() != null) return false;
        if (getQrCode() != null ? !getQrCode().equals(inspect.getQrCode()) : inspect.getQrCode() != null) return false;
        if (getProductCode() != null ? !getProductCode().equals(inspect.getProductCode()) : inspect.getProductCode() != null)
            return false;
        if (getProductType() != null ? !getProductType().equals(inspect.getProductType()) : inspect.getProductType() != null)
            return false;
        if (getMaterialType() != null ? !getMaterialType().equals(inspect.getMaterialType()) : inspect.getMaterialType() != null)
            return false;
        if (getMaterialQuality() != null ? !getMaterialQuality().equals(inspect.getMaterialQuality()) : inspect.getMaterialQuality() != null)
            return false;
        if (getQuantityStd() != null ? !getQuantityStd().equals(inspect.getQuantityStd()) : inspect.getQuantityStd() != null)
            return false;
        if (getInspector() != null ? !getInspector().equals(inspect.getInspector()) : inspect.getInspector() != null)
            return false;
        if (getBeginTime() != null ? !getBeginTime().equals(inspect.getBeginTime()) : inspect.getBeginTime() != null)
            return false;
        if (getEndTime() != null ? !getEndTime().equals(inspect.getEndTime()) : inspect.getEndTime() != null)
            return false;
        if (getRunTime() != null ? !getRunTime().equals(inspect.getRunTime()) : inspect.getRunTime() != null)
            return false;
        if (getOkPercent() != null ? !getOkPercent().equals(inspect.getOkPercent()) : inspect.getOkPercent() != null)
            return false;
        if (getNgPercent() != null ? !getNgPercent().equals(inspect.getNgPercent()) : inspect.getNgPercent() != null)
            return false;
        if (getInspectedNum() != null ? !getInspectedNum().equals(inspect.getInspectedNum()) : inspect.getInspectedNum() != null)
            return false;
        if (getOkNum() != null ? !getOkNum().equals(inspect.getOkNum()) : inspect.getOkNum() != null) return false;
        if (getNgNum() != null ? !getNgNum().equals(inspect.getNgNum()) : inspect.getNgNum() != null) return false;
        if (getNg1() != null ? !getNg1().equals(inspect.getNg1()) : inspect.getNg1() != null) return false;
        if (getNg2() != null ? !getNg2().equals(inspect.getNg2()) : inspect.getNg2() != null) return false;
        if (getNg3() != null ? !getNg3().equals(inspect.getNg3()) : inspect.getNg3() != null) return false;
        if (getNg4() != null ? !getNg4().equals(inspect.getNg4()) : inspect.getNg4() != null) return false;
        if (getNg5() != null ? !getNg5().equals(inspect.getNg5()) : inspect.getNg5() != null) return false;
        if (getNg6() != null ? !getNg6().equals(inspect.getNg6()) : inspect.getNg6() != null) return false;
        if (getNg7() != null ? !getNg7().equals(inspect.getNg7()) : inspect.getNg7() != null) return false;
        if (getNg8() != null ? !getNg8().equals(inspect.getNg8()) : inspect.getNg8() != null) return false;
        if (getNg9() != null ? !getNg9().equals(inspect.getNg9()) : inspect.getNg9() != null) return false;
        if (getNg10() != null ? !getNg10().equals(inspect.getNg10()) : inspect.getNg10() != null) return false;
        if (getResult() != null ? !getResult().equals(inspect.getResult()) : inspect.getResult() != null) return false;
        if (getRemark1() != null ? !getRemark1().equals(inspect.getRemark1()) : inspect.getRemark1() != null)
            return false;
        if (getRemark2() != null ? !getRemark2().equals(inspect.getRemark2()) : inspect.getRemark2() != null)
            return false;
        if (getRemark3() != null ? !getRemark3().equals(inspect.getRemark3()) : inspect.getRemark3() != null)
            return false;
        if (getRemark4() != null ? !getRemark4().equals(inspect.getRemark4()) : inspect.getRemark4() != null)
            return false;
        if (getNgStr() != null ? !getNgStr().equals(inspect.getNgStr()) : inspect.getNgStr() != null) return false;
        return getStatus() != null ? getStatus().equals(inspect.getStatus()) : inspect.getStatus() == null;
    }

    @Override
    public int hashCode() {
        int result1 = getId() != null ? getId().hashCode() : 0;
        result1 = 31 * result1 + (getQrCode() != null ? getQrCode().hashCode() : 0);
        result1 = 31 * result1 + (getProductCode() != null ? getProductCode().hashCode() : 0);
        result1 = 31 * result1 + (getProductType() != null ? getProductType().hashCode() : 0);
        result1 = 31 * result1 + (getMaterialType() != null ? getMaterialType().hashCode() : 0);
        result1 = 31 * result1 + (getMaterialQuality() != null ? getMaterialQuality().hashCode() : 0);
        result1 = 31 * result1 + (getQuantityStd() != null ? getQuantityStd().hashCode() : 0);
        result1 = 31 * result1 + (getInspector() != null ? getInspector().hashCode() : 0);
        result1 = 31 * result1 + (getBeginTime() != null ? getBeginTime().hashCode() : 0);
        result1 = 31 * result1 + (getEndTime() != null ? getEndTime().hashCode() : 0);
        result1 = 31 * result1 + (getRunTime() != null ? getRunTime().hashCode() : 0);
        result1 = 31 * result1 + (getOkPercent() != null ? getOkPercent().hashCode() : 0);
        result1 = 31 * result1 + (getNgPercent() != null ? getNgPercent().hashCode() : 0);
        result1 = 31 * result1 + (getInspectedNum() != null ? getInspectedNum().hashCode() : 0);
        result1 = 31 * result1 + (getOkNum() != null ? getOkNum().hashCode() : 0);
        result1 = 31 * result1 + (getNgNum() != null ? getNgNum().hashCode() : 0);
        result1 = 31 * result1 + (getNg1() != null ? getNg1().hashCode() : 0);
        result1 = 31 * result1 + (getNg2() != null ? getNg2().hashCode() : 0);
        result1 = 31 * result1 + (getNg3() != null ? getNg3().hashCode() : 0);
        result1 = 31 * result1 + (getNg4() != null ? getNg4().hashCode() : 0);
        result1 = 31 * result1 + (getNg5() != null ? getNg5().hashCode() : 0);
        result1 = 31 * result1 + (getNg6() != null ? getNg6().hashCode() : 0);
        result1 = 31 * result1 + (getNg7() != null ? getNg7().hashCode() : 0);
        result1 = 31 * result1 + (getNg8() != null ? getNg8().hashCode() : 0);
        result1 = 31 * result1 + (getNg9() != null ? getNg9().hashCode() : 0);
        result1 = 31 * result1 + (getNg10() != null ? getNg10().hashCode() : 0);
        result1 = 31 * result1 + (getResult() != null ? getResult().hashCode() : 0);
        result1 = 31 * result1 + (getRemark1() != null ? getRemark1().hashCode() : 0);
        result1 = 31 * result1 + (getRemark2() != null ? getRemark2().hashCode() : 0);
        result1 = 31 * result1 + (getRemark3() != null ? getRemark3().hashCode() : 0);
        result1 = 31 * result1 + (getRemark4() != null ? getRemark4().hashCode() : 0);
        result1 = 31 * result1 + (getNgStr() != null ? getNgStr().hashCode() : 0);
        result1 = 31 * result1 + (getStatus() != null ? getStatus().hashCode() : 0);
        return result1;
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
                ", remark1='" + remark1 + '\'' +
                ", remark2='" + remark2 + '\'' +
                ", remark3='" + remark3 + '\'' +
                ", remark4='" + remark4 + '\'' +
                ", ngStr='" + ngStr + '\'' +
                ", status=" + status +
                '}';
    }
}
