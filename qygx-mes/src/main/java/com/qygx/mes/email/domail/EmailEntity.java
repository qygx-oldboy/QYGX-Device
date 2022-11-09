package com.qygx.mes.email.domail;

import com.qygx.common.core.domain.BaseEntity;

public class EmailEntity extends BaseEntity {
    private String sendTo;  //收件邮箱
    private String title;   //邮箱标题
    private String detail;  //邮箱内容
    private String fileList;  //附件内容（上传到阿里云oss的附件，多附件用英文逗号隔开，文件名不能包含逗号）


    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFileList() {
        return fileList;
    }

    public void setFileList(String fileList) {
        this.fileList = fileList;
    }
}
