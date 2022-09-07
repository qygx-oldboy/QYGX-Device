package com.qygx.mes.csm.service.impl;

import java.util.List;
import com.qygx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.csm.mapper.CsmConsumaCycleMapper;
import com.qygx.mes.csm.domain.CsmConsumaCycle;
import com.qygx.mes.csm.service.ICsmConsumaCycleService;

/**
 * 耗材周期Service业务层处理
 *
 * @author qygx
 * @date 2022-09-07
 */
@Service
public class CsmConsumaCycleServiceImpl implements ICsmConsumaCycleService
{
    @Autowired
    private CsmConsumaCycleMapper csmConsumaCycleMapper;

    /**
     * 查询耗材周期
     *
     * @param consumaId 耗材周期主键
     * @return 耗材周期
     */
    @Override
    public CsmConsumaCycle selectCsmConsumaCycleByConsumaId(Long consumaId)
    {
        return csmConsumaCycleMapper.selectCsmConsumaCycleByConsumaId(consumaId);
    }

    /**
     * 查询耗材周期列表
     *
     * @param csmConsumaCycle 耗材周期
     * @return 耗材周期
     */
    @Override
    public List<CsmConsumaCycle> selectCsmConsumaCycleList(CsmConsumaCycle csmConsumaCycle)
    {
        return csmConsumaCycleMapper.selectCsmConsumaCycleList(csmConsumaCycle);
    }

    /**
     * 新增耗材周期
     *
     * @param csmConsumaCycle 耗材周期
     * @return 结果
     */
    @Override
    public int insertCsmConsumaCycle(CsmConsumaCycle csmConsumaCycle)
    {
        csmConsumaCycle.setCreateTime(DateUtils.getNowDate());
        return csmConsumaCycleMapper.insertCsmConsumaCycle(csmConsumaCycle);
    }

    /**
     * 修改耗材周期
     *
     * @param csmConsumaCycle 耗材周期
     * @return 结果
     */
    @Override
    public int updateCsmConsumaCycle(CsmConsumaCycle csmConsumaCycle)
    {
        csmConsumaCycle.setUpdateTime(DateUtils.getNowDate());
        return csmConsumaCycleMapper.updateCsmConsumaCycle(csmConsumaCycle);
    }

    /**
     * 批量删除耗材周期
     *
     * @param consumaIds 需要删除的耗材周期主键
     * @return 结果
     */
    @Override
    public int deleteCsmConsumaCycleByConsumaIds(Long[] consumaIds)
    {
        return csmConsumaCycleMapper.deleteCsmConsumaCycleByConsumaIds(consumaIds);
    }

    /**
     * 删除耗材周期信息
     *
     * @param consumaId 耗材周期主键
     * @return 结果
     */
    @Override
    public int deleteCsmConsumaCycleByConsumaId(Long consumaId)
    {
        return csmConsumaCycleMapper.deleteCsmConsumaCycleByConsumaId(consumaId);
    }
}
