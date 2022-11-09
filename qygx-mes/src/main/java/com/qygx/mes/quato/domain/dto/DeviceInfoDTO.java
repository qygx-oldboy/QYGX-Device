package com.qygx.mes.quato.domain.dto;


import com.qygx.system.domain.DeviceArchives;

import java.util.List;

public class DeviceInfoDTO {

    private DeviceDTO device; //设备

    private List<QuatoDTO> quatoList;


    public DeviceDTO getDevice() {
        return device;
    }

    public void setDevice(DeviceDTO device) {
        this.device = device;
    }

    public List<QuatoDTO> getQuatoList() {
        return quatoList;
    }

    public void setQuatoList(List<QuatoDTO> quatoList) {
        this.quatoList = quatoList;
    }
}
