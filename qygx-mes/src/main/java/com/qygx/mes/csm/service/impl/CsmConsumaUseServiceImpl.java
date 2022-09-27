package com.qygx.mes.csm.service.impl;

import java.util.List;

import com.qygx.common.constant.UserConstants;
import com.qygx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.csm.mapper.CsmConsumaUseMapper;
import com.qygx.mes.csm.domain.CsmConsumaUse;
import com.qygx.mes.csm.service.ICsmConsumaUseService;

/**
 * 在用备件Service业务层处理
 *
 * @author qygx
 * @date 2022-09-07
 */
@Service
public class CsmConsumaUseServiceImpl implements ICsmConsumaUseService
{
    @Autowired
    private CsmConsumaUseMapper csmConsumaUseMapper;

    /**
     * 查询在用备件
     *
     * @param consumaUseId 在用备件主键
     * @return 在用备件
     */
    @Override
    public CsmConsumaUse selectCsmConsumaUseByConsumaUseId(Long consumaUseId)
    {
        return csmConsumaUseMapper.selectCsmConsumaUseByConsumaUseId(consumaUseId);
    }

    /**
     * 查询在用备件列表
     *
     * @param csmConsumaUse 在用备件
     * @return 在用备件
     */
    @Override
    public List<CsmConsumaUse> selectCsmConsumaUseList(CsmConsumaUse csmConsumaUse)
    {
        return csmConsumaUseMapper.selectCsmConsumaUseList(csmConsumaUse);
    }

    /**
     * 新增在用备件
     *
     * @param csmConsumaUse 在用备件
     * @return 结果
     */
    @Override
    public int insertCsmConsumaUse(CsmConsumaUse csmConsumaUse)
    {
        csmConsumaUse.setCreateTime(DateUtils.getNowDate());
        return csmConsumaUseMapper.insertCsmConsumaUse(csmConsumaUse);
    }

    /**
     * 修改在用备件
     *
     * @param csmConsumaUse 在用备件
     * @return 结果
     */
    @Override
    public int updateCsmConsumaUse(CsmConsumaUse csmConsumaUse)
    {
        csmConsumaUse.setUpdateTime(DateUtils.getNowDate());
        return csmConsumaUseMapper.updateCsmConsumaUse(csmConsumaUse);
    }

    /**
     * 批量删除在用备件
     *
     * @param consumaUseIds 需要删除的在用备件主键
     * @return 结果
     */
    @Override
    public int deleteCsmConsumaUseByConsumaUseIds(Long[] consumaUseIds)
    {
        return csmConsumaUseMapper.deleteCsmConsumaUseByConsumaUseIds(consumaUseIds);
    }

    /**
     * 删除在用备件信息
     *
     * @param consumaUseId 在用备件主键
     * @return 结果
     */
    @Override
    public int deleteCsmConsumaUseByConsumaUseId(Long consumaUseId)
    {
        return csmConsumaUseMapper.deleteCsmConsumaUseByConsumaUseId(consumaUseId);
    }

    /**
     * 校验批次号是否唯一
     *
     * @param batchNo 批次号
     * @return 结果
     */
    @Override
    public String checkBatchNoUnique(String batchNo)
    {
        int count = csmConsumaUseMapper.checkBatchNoUnique(batchNo);
        if (count > 0)
        {
            return "NOT_UNIQUE";
        }
        return "UNIQUE";
    }
}
