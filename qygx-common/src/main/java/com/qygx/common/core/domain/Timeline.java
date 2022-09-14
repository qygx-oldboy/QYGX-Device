package com.qygx.common.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 时间线 数据结构实体类
 */
public class Timeline implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 内容 */
    private String content;

    /** 时间戳 */
    private Date timestamp;


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

    @Override
    public String toString() {
        return "Timeline{" +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
