package com.qygx.system.domain;

public class Operators {

    private Long id;

    /** 工号**/
    private Long operatorId;

    /** 姓名**/
    private String name;

    /** 职责**/
    private String duty;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}
