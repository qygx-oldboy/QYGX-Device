package com.qygx.system.mapper;

import java.util.List;
import com.qygx.system.domain.DeviceArchives;

/**
 * archivesMapper接口
 * 
 * @author qygx
 * @date 2022-08-12
 */
public interface DeviceArchivesMapper 
{
    /**
     * 查询archives
     * 
     * @param deviceId archives主键
     * @return archives
     */
    public DeviceArchives selectDeviceArchivesByDeviceId(Long deviceId);

    /**
     * 查询archives列表
     * 
     * @param deviceArchives archives
     * @return archives集合
     */
    public List<DeviceArchives> selectDeviceArchivesList(DeviceArchives deviceArchives);

    /**
     * 新增archives
     * 
     * @param deviceArchives archives
     * @return 结果
     */
    public int insertDeviceArchives(DeviceArchives deviceArchives);

    /**
     * 修改archives
     * 
     * @param deviceArchives archives
     * @return 结果
     */
    public int updateDeviceArchives(DeviceArchives deviceArchives);

    /**
     * 删除archives
     * 
     * @param deviceId archives主键
     * @return 结果
     */
    public int deleteDeviceArchivesByDeviceId(Long deviceId);

    /**
     * 批量删除archives
     * 
     * @param deviceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceArchivesByDeviceIds(Long[] deviceIds);


    /**
     * 校验设备名称是否唯一
     *
     * @param deviceName 设备名称
     * @return 结果
     */
    //public int checkDeviceNameUnique(String deviceName);

    /**
     * 查询archives
     *
     * @param deviceCode 设备编码
     * @return archives
     */
    public DeviceArchives selectDeviceByCode(String  deviceCode);
}
