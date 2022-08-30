package com.qygx.system.mapper;

import java.util.List;
import com.qygx.system.domain.DeviceMaintainSheet;
import com.qygx.system.domain.DeviceMaintainDetail;

/**
 * 保养记录Mapper接口
 * 
 * @author qygx
 * @date 2022-08-29
 */
public interface DeviceMaintainSheetMapper 
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
     * 删除保养记录
     * 
     * @param maintainSheetId 保养记录主键
     * @return 结果
     */
    public int deleteDeviceMaintainSheetByMaintainSheetId(Long maintainSheetId);

    /**
     * 批量删除保养记录
     * 
     * @param maintainSheetIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceMaintainSheetByMaintainSheetIds(Long[] maintainSheetIds);

    /**
     * 批量删除保养记录明细
     * 
     * @param maintainSheetIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceMaintainDetailByMaintainSheetIds(Long[] maintainSheetIds);
    
    /**
     * 批量新增保养记录明细
     * 
     * @param deviceMaintainDetailList 保养记录明细列表
     * @return 结果
     */
    public int batchDeviceMaintainDetail(List<DeviceMaintainDetail> deviceMaintainDetailList);
    

    /**
     * 通过保养记录主键删除保养记录明细信息
     * 
     * @param maintainSheetId 保养记录ID
     * @return 结果
     */
    public int deleteDeviceMaintainDetailByMaintainSheetId(Long maintainSheetId);
}
