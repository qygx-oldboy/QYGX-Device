package com.qygx.system.service.impl;

import java.util.List;

import com.qygx.common.constant.DeviceConstants;
import com.qygx.common.constant.UserConstants;
import com.qygx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.system.mapper.DeviceArchivesMapper;
import com.qygx.system.domain.DeviceArchives;
import com.qygx.system.service.IDeviceArchivesService;

/**
 * archivesService业务层处理
 * 
 * @author qygx
 * @date 2022-08-12
 */
@Service
public class DeviceArchivesServiceImpl implements IDeviceArchivesService 
{
    @Autowired
    private DeviceArchivesMapper deviceArchivesMapper;

    /**
     * 查询archives
     * 
     * @param deviceId archives主键
     * @return archives
     */
    @Override
    public DeviceArchives selectDeviceArchivesByDeviceId(Long deviceId)
    {
        return deviceArchivesMapper.selectDeviceArchivesByDeviceId(deviceId);
    }

    /**
     * 查询archives列表
     * 
     * @param deviceArchives archives
     * @return archives
     */
    @Override
    public List<DeviceArchives> selectDeviceArchivesList(DeviceArchives deviceArchives)
    {
        return deviceArchivesMapper.selectDeviceArchivesList(deviceArchives);
    }

    /**
     * 新增archives
     * 
     * @param deviceArchives archives
     * @return 结果
     */
    @Override
    public int insertDeviceArchives(DeviceArchives deviceArchives)
    {
        deviceArchives.setCreateTime(DateUtils.getNowDate());
        return deviceArchivesMapper.insertDeviceArchives(deviceArchives);
    }

    /**
     * 修改archives
     * 
     * @param deviceArchives archives
     * @return 结果
     */
    @Override
    public int updateDeviceArchives(DeviceArchives deviceArchives)
    {
        deviceArchives.setUpdateTime(DateUtils.getNowDate());
        return deviceArchivesMapper.updateDeviceArchives(deviceArchives);
    }

    /**
     * 批量删除archives
     * 
     * @param deviceIds 需要删除的archives主键
     * @return 结果
     */
    @Override
    public int deleteDeviceArchivesByDeviceIds(Long[] deviceIds)
    {
        return deviceArchivesMapper.deleteDeviceArchivesByDeviceIds(deviceIds);
    }

    /**
     * 删除archives信息
     * 
     * @param deviceId archives主键
     * @return 结果
     */
    @Override
    public int deleteDeviceArchivesByDeviceId(Long deviceId)
    {
        return deviceArchivesMapper.deleteDeviceArchivesByDeviceId(deviceId);
    }

    /**
     * 校验设备名称是否唯一
     *
     * @param deviceName 设备名称
     * @return 结果
     */
    @Override
    public String checkDeviceNameUnique(String deviceName)
    {
        int count = deviceArchivesMapper.checkDeviceNameUnique(deviceName);
        if (count > 0)
        {
            return DeviceConstants.NOT_UNIQUE;
        }
        return DeviceConstants.UNIQUE;
    }
}
