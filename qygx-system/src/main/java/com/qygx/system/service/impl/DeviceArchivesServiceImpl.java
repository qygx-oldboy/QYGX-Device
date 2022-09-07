package com.qygx.system.service.impl;

import java.util.List;

import com.qygx.common.annotation.DataSource;
import com.qygx.common.constant.DeviceConstants;
import com.qygx.common.constant.UserConstants;
import com.qygx.common.enums.DataSourceType;
import com.qygx.common.exception.ServiceException;
import com.qygx.common.utils.DateUtils;
import com.qygx.common.utils.StringUtils;
import com.qygx.common.utils.bean.BeanValidators;
import com.qygx.system.domain.ProInspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.system.mapper.DeviceArchivesMapper;
import com.qygx.system.domain.DeviceArchives;
import com.qygx.system.service.IDeviceArchivesService;

import javax.validation.Validator;

/**
 * archivesService业务层处理
 * 
 * @author qygx
 * @date 2022-08-12
 */
@Service
public class DeviceArchivesServiceImpl implements IDeviceArchivesService 
{
    private static final Logger log = LoggerFactory.getLogger(DeviceArchivesServiceImpl.class);

    @Autowired
    private DeviceArchivesMapper deviceArchivesMapper;

    @Autowired
    protected Validator validator;

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
//        int count = deviceArchivesMapper.checkDeviceNameUnique(deviceName);
//        if (count > 0)
//        {
//            return DeviceConstants.NOT_UNIQUE;
//        }
//        return DeviceConstants.UNIQUE;
        return null;
    }

    @Override
    public String importDevice(List<DeviceArchives> deviceList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(deviceList) || deviceList.size() == 0)
        {
            throw new ServiceException("导入检测数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DeviceArchives device : deviceList)
        {
            try
            {

                DeviceArchives i = deviceArchivesMapper.selectDeviceByCode(device.getDeviceCode());
                if (StringUtils.isNull(i))
                {
                    BeanValidators.validateWithException(validator, device);
                    this.insertDeviceArchives(device);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、设备编码 " + device.getDeviceCode() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, device);
                    device.setDeviceId(i.getDeviceId());
                    this.updateDeviceArchives(device);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、设备编码 " + device.getDeviceCode() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、设备编码 " + device.getDeviceCode() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、设备编码 " + device.getDeviceCode() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
