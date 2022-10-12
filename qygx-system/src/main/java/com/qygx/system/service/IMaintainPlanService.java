package com.qygx.system.service;

import java.util.List;

import com.qygx.common.core.domain.entity.SysUser;
import com.qygx.system.domain.MaintainPlan;
import com.qygx.system.domain.MaintainPlanDetail;

/**
 * 保养计划Service接口
 *
 * @author qygx
 * @date 2022-08-12
 */
public interface IMaintainPlanService
{
    /**
     * 查询保养计划
     *
     * @param planId 保养计划主键
     * @return 保养计划
     */
    public MaintainPlan selectMaintainPlanByPlanId(Long planId);


    /**
     * 查询保养计划明细
     *
     * @param planId 保养计划主键
     * @return 保养计划明细
     */
    public List<MaintainPlanDetail> selectDetailByPlanId(Long planId);

    /**
     * 查询保养计划列表
     *
     * @param maintainPlan 保养计划
     * @return 保养计划集合
     */
    public List<MaintainPlan> selectMaintainPlanList(MaintainPlan maintainPlan);

    /**
     * 新增保养计划
     *
     * @param maintainPlan 保养计划
     * @return 结果
     */
    public int insertMaintainPlan(MaintainPlan maintainPlan);

    /**
     * 修改保养计划
     *
     * @param maintainPlan 保养计划
     * @return 结果
     */
    public int updateMaintainPlan(MaintainPlan maintainPlan);

    /**
     * 批量删除保养计划
     *
     * @param planIds 需要删除的保养计划主键集合
     * @return 结果
     */
    public int deleteMaintainPlanByPlanIds(Long[] planIds);

    /**
     * 删除保养计划信息
     *
     * @param planId 保养计划主键
     * @return 结果
     */
    public int deleteMaintainPlanByPlanId(Long planId);


    /**
     * 修改计划状态
     *
     * @param maintainPlan 计划信息
     * @return 结果
     */
    public int updatePlanStatus(MaintainPlan maintainPlan);

    /**
     * 校验 计划（设备类型+保养等级）  是否唯一
     *
     * @param maintainPlan 计划信息
     * @return 结果
     */
    public String checkPlanUnique(MaintainPlan maintainPlan);
}
