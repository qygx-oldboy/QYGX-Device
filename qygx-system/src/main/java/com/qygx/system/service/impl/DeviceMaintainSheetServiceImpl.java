package com.qygx.system.service.impl;

import java.util.List;
import com.qygx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.qygx.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.qygx.system.domain.DeviceMaintainDetail;
import com.qygx.system.mapper.DeviceMaintainSheetMapper;
import com.qygx.system.domain.DeviceMaintainSheet;
import com.qygx.system.service.IDeviceMaintainSheetService;

/**
 * 保养记录Service业务层处理
 * 
 * @author qygx
 * @date 2022-08-29
 */
@Service
public class DeviceMaintainSheetServiceImpl implements IDeviceMaintainSheetService 
{
    @Autowired
    private DeviceMaintainSheetMapper deviceMaintainSheetMapper;

    /**
     * 查询保养记录
     * 
     * @param maintainSheetId 保养记录主键
     * @return 保养记录
     */
    @Override
    public DeviceMaintainSheet selectDeviceMaintainSheetByMaintainSheetId(Long maintainSheetId)
    {
        return deviceMaintainSheetMapper.selectDeviceMaintainSheetByMaintainSheetId(maintainSheetId);
    }

    /**
     * 查询保养记录列表
     * 
     * @param deviceMaintainSheet 保养记录
     * @return 保养记录
     */
    @Override
    public List<DeviceMaintainSheet> selectDeviceMaintainSheetList(DeviceMaintainSheet deviceMaintainSheet)
    {
        return deviceMaintainSheetMapper.selectDeviceMaintainSheetList(deviceMaintainSheet);
    }

    /**
     * 新增保养记录
     * 
     * @param deviceMaintainSheet 保养记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDeviceMaintainSheet(DeviceMaintainSheet deviceMaintainSheet)
    {
        deviceMaintainSheet.setCreateTime(DateUtils.getNowDate());
        int rows = deviceMaintainSheetMapper.insertDeviceMaintainSheet(deviceMaintainSheet);
        insertDeviceMaintainDetail(deviceMaintainSheet);
        return rows;
    }

    /**
     * 修改保养记录
     * 
     * @param deviceMaintainSheet 保养记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDeviceMaintainSheet(DeviceMaintainSheet deviceMaintainSheet)
    {
        deviceMaintainSheet.setUpdateTime(DateUtils.getNowDate());
        deviceMaintainSheetMapper.deleteDeviceMaintainDetailByMaintainSheetId(deviceMaintainSheet.getMaintainSheetId());
        insertDeviceMaintainDetail(deviceMaintainSheet);
        deviceMaintainSheet.setStatus("1"); //已处理
        return deviceMaintainSheetMapper.updateDeviceMaintainSheet(deviceMaintainSheet);
    }

    /**
     * 批量删除保养记录
     * 
     * @param maintainSheetIds 需要删除的保养记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDeviceMaintainSheetByMaintainSheetIds(Long[] maintainSheetIds)
    {
        deviceMaintainSheetMapper.deleteDeviceMaintainDetailByMaintainSheetIds(maintainSheetIds);
        return deviceMaintainSheetMapper.deleteDeviceMaintainSheetByMaintainSheetIds(maintainSheetIds);
    }

    /**
     * 删除保养记录信息
     * 
     * @param maintainSheetId 保养记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDeviceMaintainSheetByMaintainSheetId(Long maintainSheetId)
    {
        deviceMaintainSheetMapper.deleteDeviceMaintainDetailByMaintainSheetId(maintainSheetId);
        return deviceMaintainSheetMapper.deleteDeviceMaintainSheetByMaintainSheetId(maintainSheetId);
    }

    /**
     * 新增保养记录明细信息
     * 
     * @param deviceMaintainSheet 保养记录对象
     */
    public void insertDeviceMaintainDetail(DeviceMaintainSheet deviceMaintainSheet)
    {
        List<DeviceMaintainDetail> deviceMaintainDetailList = deviceMaintainSheet.getDeviceMaintainDetailList();
        Long maintainSheetId = deviceMaintainSheet.getMaintainSheetId();
        if (StringUtils.isNotNull(deviceMaintainDetailList))
        {
            List<DeviceMaintainDetail> list = new ArrayList<DeviceMaintainDetail>();
            for (DeviceMaintainDetail deviceMaintainDetail : deviceMaintainDetailList)
            {
                deviceMaintainDetail.setMaintainSheetId(maintainSheetId);
                list.add(deviceMaintainDetail);
            }
            if (list.size() > 0)
            {
                deviceMaintainSheetMapper.batchDeviceMaintainDetail(list);
            }
        }
    }


    /**
     * 批量提交保养记录
     *
     * @param maintainSheetIds 保养记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int submitDeviceMaintainSheetByMaintainSheetIds(Long[] maintainSheetIds)
    {

        return deviceMaintainSheetMapper.submitDeviceMaintainSheetByMaintainSheetIds(maintainSheetIds);
    }
}
