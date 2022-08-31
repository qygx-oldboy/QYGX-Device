package com.qygx.system.service;

import java.util.List;
import com.qygx.system.domain.DeviceMaintainSheet;

/**
 * 保养记录Service接口
 * 
 * @author qygx
 * @date 2022-08-29
 */
public interface IDeviceMaintainSheetService 
{
    /**
     * 查询保养记录
     * 
     * @param maintainSheetId 保养记录主键
     * @return 保养记录
     */
    public DeviceMaintainSheet selectDeviceMaintainSheetByMaintainSheetId(Long maintainSheetId);

    /**
     * 查询保养记录列表
     * 
     * @param deviceMaintainSheet 保养记录
     * @return 保养记录集合
     */
    public List<DeviceMaintainSheet> selectDeviceMaintainSheetList(DeviceMaintainSheet deviceMaintainSheet);

    /**
     * 新增保养记录
     * 
     * @param deviceMaintainSheet 保养记录
     * @return 结果
     */
    public int insertDeviceMaintainSheet(DeviceMaintainSheet deviceMaintainSheet);

    /**
     * 修改保养记录
     * 
     * @param deviceMaintainSheet 保养记录
     * @return 结果
     */
    public int updateDeviceMaintainSheet(DeviceMaintainSheet deviceMaintainSheet);

    /**
     * 批量删除保养记录
     * 
     * @param maintainSheetIds 需要删除的保养记录主键集合
     * @return 结果
     */
    public int deleteDeviceMaintainSheetByMaintainSheetIds(Long[] maintainSheetIds);

    /**
     * 删除保养记录信息
     * 
     * @param maintainSheetId 保养记录主键
     * @return 结果
     */
    public int deleteDeviceMaintainSheetByMaintainSheetId(Long maintainSheetId);

    /**
     * 批量提交保养记录
     *
     * @param maintainSheetIds 保养记录主键集合
     * @return 结果
     */
    public int submitDeviceMaintainSheetByMaintainSheetIds(Long[] maintainSheetIds);
}
