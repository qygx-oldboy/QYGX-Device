package com.qygx.system.mapper;

import java.util.List;

import com.qygx.system.domain.MaintainPlan;
import com.qygx.system.domain.MiddleDevicePlan;

/**
 * 计划设备中间表Mapper接口
 *
 * @author qygx
 * @date 2022-08-29
 */
public interface MiddleDevicePlanMapper
{
    /**
     * 查询计划设备中间表
     *
     * @param id 计划设备中间表主键
     * @return 计划设备中间表
     */
    public MiddleDevicePlan selectMiddleDevicePlanById(Long id);

    /**
     * 查询计划设备中间表列表
     *
     * @param middleDevicePlan 计划设备中间表
     * @return 计划设备中间表集合
     */
    public List<MiddleDevicePlan> selectMiddleDevicePlanList(MiddleDevicePlan middleDevicePlan);

    /**
     * 新增计划设备中间表
     *
     * @param middleDevicePlan 计划设备中间表
     * @return 结果
     */
    public int insertMiddleDevicePlan(MiddleDevicePlan middleDevicePlan);

    /**
     * 修改计划设备中间表
     *
     * @param middleDevicePlan 计划设备中间表
     * @return 结果
     */
    public int updateMiddleDevicePlan(MiddleDevicePlan middleDevicePlan);

    /**
     * 删除计划设备中间表
     *
     * @param id 计划设备中间表主键
     * @return 结果
     */
    public int deleteMiddleDevicePlanById(Long id);

    /**
     * 批量删除计划设备中间表
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMiddleDevicePlanByIds(Long[] ids);

    /**
     * 校验计划设备是否唯一
     *
     * @param middleDevicePlan 计划设备中间表
     * @return 结果
     */
    public int checkMiddleUnique(MiddleDevicePlan middleDevicePlan);
}
