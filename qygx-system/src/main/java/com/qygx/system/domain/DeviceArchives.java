package com.qygx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qygx.common.annotation.Excel;
import com.qygx.common.core.domain.BaseEntity;

/**
 * archives对象 device_archives
 * 
 * @author qygx
 * @date 2022-08-12
 */
public class DeviceArchives extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备id */
    private Long deviceId;

    /** 设备ip */
    @Excel(name = "设备ip")
    private String ip;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String name;

    /** 规格 */
    @Excel(name = "规格")
    private String standard;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceType;

    /** 生产厂商 */
    @Excel(name = "生产厂商")
    private String manufacturer;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private String status;

    /** 安装地点 */
    @Excel(name = "安装地点")
    private String location;

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStandard(String standard) 
    {
        this.standard = standard;
    }

    public String getStandard() 
    {
        return standard;
    }
    public void setDeviceType(String deviceType) 
    {
        this.deviceType = deviceType;
    }

    public String getDeviceType() 
    {
        return deviceType;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deviceId", getDeviceId())
            .append("ip", getIp())
            .append("name", getName())
            .append("standard", getStandard())
            .append("deviceType", getDeviceType())
            .append("manufacturer", getManufacturer())
            .append("status", getStatus())
            .append("location", getLocation())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
