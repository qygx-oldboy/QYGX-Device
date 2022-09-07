package com.qygx.mes.csm.mapper;

import java.util.List;
import com.qygx.mes.csm.domain.CsmConsumaCycle;

/**
 * 耗材周期Mapper接口
 *
 * @author qygx
 * @date 2022-09-07
 */
public interface CsmConsumaCycleMapper
{
    /**
     * 查询耗材周期
     *
     * @param consumaId 耗材周期主键
     * @return 耗材周期
     */
    public CsmConsumaCycle selectCsmConsumaCycleByConsumaId(Long consumaId);

    /**
     * 查询耗材周期列表
     *
     * @param csmConsumaCycle 耗材周期
     * @return 耗材周期集合
     */
    public List<CsmConsumaCycle> selectCsmConsumaCycleList(CsmConsumaCycle csmConsumaCycle);

    /**
     * 新增耗材周期
     *
     * @param csmConsumaCycle 耗材周期
     * @return 结果
     */
    public int insertCsmConsumaCycle(CsmConsumaCycle csmConsumaCycle);

    /**
     * 修改耗材周期
     *
     * @param csmConsumaCycle 耗材周期
     * @return 结果
     */
    public int updateCsmConsumaCycle(CsmConsumaCycle csmConsumaCycle);

    /**
     * 删除耗材周期
     *
     * @param consumaId 耗材周期主键
     * @return 结果
     */
    public int deleteCsmConsumaCycleByConsumaId(Long consumaId);

    /**
     * 批量删除耗材周期
     *
     * @param consumaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCsmConsumaCycleByConsumaIds(Long[] consumaIds);
}
