package com.qygx.system.domain.dto;

import com.qygx.common.annotation.Excel;

import java.io.Serializable;

public class InspectDto implements Serializable {

    /**质检id */
    private Long id;


    private String name;

    /**检验数的总和 */
    private int checkTotal;

    /**良品数的总和 */
    private int goodTotal;

    /**良率 */
    private Double okPercent;

    /** 材质不良比率 */
    private Double ng1;

    /** 失园比率 */
    private Double ng2;

    /** 内裂比率 */
    private Double ng3;

    /** 坑点比率*/
    private Double ng4;

    /** 尺寸不良比率 */
    private Double ng5;

    /** 发雾比率 */
    private Double ng6;

    /** 伤痕比率 */
    private Double ng7;

    /** 印渍比率 */
    private Double ng8;

    /** 不洁比率 */
    private Double ng9;

    /** 其他比率 */
    private Double ng10;

    /** 是否有儿子*/
    private Boolean hasChildren;


    /** 日期*/
    private String date;

    /** 父类型*/
    private String type;

    /** 子类型*/
    private String subType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCheckTotal() {
        return checkTotal;
    }

    public void setCheckTotal(int checkTotal) {
        this.checkTotal = checkTotal;
    }

    public int getGoodTotal() {
        return goodTotal;
    }

    public void setGoodTotal(int goodTotal) {
        this.goodTotal = goodTotal;
    }

    public Double getOkPercent() {
        return okPercent;
    }

    public void setOkPercent(Double okPercent) {
        this.okPercent = okPercent;
    }

    public Double getNg1() {
        return ng1;
    }

    public void setNg1(Double ng1) {
        this.ng1 = ng1;
    }

    public Double getNg2() {
        return ng2;
    }

    public void setNg2(Double ng2) {
        this.ng2 = ng2;
    }

    public Double getNg3() {
        return ng3;
    }

    public void setNg3(Double ng3) {
        this.ng3 = ng3;
    }

    public Double getNg4() {
        return ng4;
    }

    public void setNg4(Double ng4) {
        this.ng4 = ng4;
    }

    public Double getNg5() {
        return ng5;
    }

    public void setNg5(Double ng5) {
        this.ng5 = ng5;
    }

    public Double getNg6() {
        return ng6;
    }

    public void setNg6(Double ng6) {
        this.ng6 = ng6;
    }

    public Double getNg7() {
        return ng7;
    }

    public void setNg7(Double ng7) {
        this.ng7 = ng7;
    }

    public Double getNg8() {
        return ng8;
    }

    public void setNg8(Double ng8) {
        this.ng8 = ng8;
    }

    public Double getNg9() {
        return ng9;
    }

    public void setNg9(Double ng9) {
        this.ng9 = ng9;
    }

    public Double getNg10() {
        return ng10;
    }

    public void setNg10(Double ng10) {
        this.ng10 = ng10;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
}
