package com.qygx.system.mapper;

import java.util.List;
import com.qygx.system.domain.MaintainPlan;
import com.qygx.system.domain.MaintainPlanDetail;

/**
 * 保养计划Mapper接口
 *
 * @author qygx
 * @date 2022-08-12
 */
public interface MaintainPlanMapper
{
    /**
     * 查询保养计划
     *
     * @param planId 保养计划主键
     * @return 保养计划
     */
    public MaintainPlan selectMaintainPlanByPlanId(Long planId);

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
     * 删除保养计划
     *
     * @param planId 保养计划主键
     * @return 结果
     */
    public int deleteMaintainPlanByPlanId(Long planId);

    /**
     * 批量删除保养计划
     *
     * @param planIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaintainPlanByPlanIds(Long[] planIds);

    /**
     * 批量删除保养计划明细
     *
     * @param planIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaintainPlanDetailByPlanIds(Long[] planIds);

    /**
     * 批量新增保养计划明细
     *
     * @param maintainPlanDetailList 保养计划明细列表
     * @return 结果
     */
    public int batchMaintainPlanDetail(List<MaintainPlanDetail> maintainPlanDetailList);


    /**
     * 通过保养计划主键删除保养计划明细信息
     *
     * @param planId 保养计划ID
     * @return 结果
     */
    public int deleteMaintainPlanDetailByPlanId(Long planId);


    /**
     * 校验计划是否唯一
     *
     * @param maintainPlan 计划信息
     * @return 结果
     */
    public int checkPlanUnique(MaintainPlan maintainPlan);
}
