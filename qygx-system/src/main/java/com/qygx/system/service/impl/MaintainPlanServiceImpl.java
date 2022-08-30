package com.qygx.system.service.impl;

import java.util.List;

import com.qygx.common.constant.DeviceConstants;
import com.qygx.common.constant.UserConstants;
import com.qygx.common.core.domain.entity.SysUser;
import com.qygx.common.utils.DateUtils;
import com.qygx.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.qygx.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.qygx.system.domain.MaintainPlanDetail;
import com.qygx.system.mapper.MaintainPlanMapper;
import com.qygx.system.domain.MaintainPlan;
import com.qygx.system.service.IMaintainPlanService;

/**
 * 保养计划Service业务层处理
 * 
 * @author qygx
 * @date 2022-08-12
 */
@Service
public class MaintainPlanServiceImpl implements IMaintainPlanService 
{
    @Autowired
    private MaintainPlanMapper maintainPlanMapper;

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询保养计划
     * 
     * @param planId 保养计划主键
     * @return 保养计划
     */
    @Override
    public MaintainPlan selectMaintainPlanByPlanId(Long planId)
    {
        return maintainPlanMapper.selectMaintainPlanByPlanId(planId);
    }

    /**
     * 查询保养计划列表
     * 
     * @param maintainPlan 保养计划
     * @return 保养计划
     */
    @Override
    public List<MaintainPlan> selectMaintainPlanList(MaintainPlan maintainPlan)
    {
        return maintainPlanMapper.selectMaintainPlanList(maintainPlan);
    }

    /**
     * 新增保养计划
     * 
     * @param maintainPlan 保养计划
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMaintainPlan(MaintainPlan maintainPlan)
    {
        maintainPlan.setCreateTime(DateUtils.getNowDate());

        Long maintainerId = maintainPlan.getMaintainerId();
        if(StringUtils.isNotNull(maintainerId)){
            SysUser sysUser = userMapper.selectUserById(maintainerId);
            maintainPlan.setMaintainerNickName(sysUser.getNickName());
        }
        int rows = maintainPlanMapper.insertMaintainPlan(maintainPlan);
        insertMaintainPlanDetail(maintainPlan);
        return rows;
    }

    /**
     * 修改保养计划
     * 
     * @param maintainPlan 保养计划
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMaintainPlan(MaintainPlan maintainPlan)
    {
        maintainPlan.setUpdateTime(DateUtils.getNowDate());
        maintainPlanMapper.deleteMaintainPlanDetailByPlanId(maintainPlan.getPlanId());
        insertMaintainPlanDetail(maintainPlan);

        Long maintainerId = maintainPlan.getMaintainerId();
        if(StringUtils.isNotNull(maintainerId)){
            SysUser sysUser = userMapper.selectUserById(maintainerId);
            maintainPlan.setMaintainerNickName(sysUser.getNickName());
        }
        return maintainPlanMapper.updateMaintainPlan(maintainPlan);
    }

    /**
     * 批量删除保养计划
     * 
     * @param planIds 需要删除的保养计划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMaintainPlanByPlanIds(Long[] planIds)
    {
        maintainPlanMapper.deleteMaintainPlanDetailByPlanIds(planIds);
        return maintainPlanMapper.deleteMaintainPlanByPlanIds(planIds);
    }

    /**
     * 删除保养计划信息
     * 
     * @param planId 保养计划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMaintainPlanByPlanId(Long planId)
    {
        maintainPlanMapper.deleteMaintainPlanDetailByPlanId(planId);
        return maintainPlanMapper.deleteMaintainPlanByPlanId(planId);
    }

    /**
     * 新增保养计划明细信息
     * 
     * @param maintainPlan 保养计划对象
     */
    public void insertMaintainPlanDetail(MaintainPlan maintainPlan)
    {
        List<MaintainPlanDetail> maintainPlanDetailList = maintainPlan.getMaintainPlanDetailList();
        Long planId = maintainPlan.getPlanId();
        if (StringUtils.isNotNull(maintainPlanDetailList))
        {
            List<MaintainPlanDetail> list = new ArrayList<MaintainPlanDetail>();
            for (MaintainPlanDetail maintainPlanDetail : maintainPlanDetailList)
            {
                maintainPlanDetail.setPlanId(planId);
                list.add(maintainPlanDetail);
            }
            if (list.size() > 0)
            {
                maintainPlanMapper.batchMaintainPlanDetail(list);
            }
        }
    }


    /**
     * 修改计划状态
     *
     * @param maintainPlan 计划信息
     * @return 结果
     */
    @Override
    public int updatePlanStatus(MaintainPlan maintainPlan)
    {
        return maintainPlanMapper.updateMaintainPlan(maintainPlan);
    }

    /**
     * 校验 计划（设备类型+保养等级）  是否唯一
     *
     * @param maintainPlan 计划信息
     * @return 结果
     */
    @Override
    public String checkPlanUnique(MaintainPlan maintainPlan)
    {
        int count = maintainPlanMapper.checkPlanUnique(maintainPlan);
        if (count > 0)
        {
            return DeviceConstants.NOT_UNIQUE;
        }
        return DeviceConstants.UNIQUE;
    }
}
