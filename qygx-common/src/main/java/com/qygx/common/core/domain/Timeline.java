package com.qygx.common.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 时间线 数据结构实体类
 */
public class Timeline implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 内容 */
    private String content;

    /** 时间戳 */
    private Date timestamp;

    /** 设备运行参数 **/
    private List runParam;

    /** 操作人 **/
    private String operator;

    /** 设备 **/
    private String device;

    private String size;

    private String color;


    public List getRunParam() {
        return runParam;
    }

    public void setRunParam(List runParam) {
        this.runParam = runParam;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "Timeline{" +
                "content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", runParam=" + runParam +
                ", operator='" + operator + '\'' +
                ", device='" + device + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
