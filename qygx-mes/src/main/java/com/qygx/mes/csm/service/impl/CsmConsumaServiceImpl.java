package com.qygx.mes.csm.service.impl;

import java.util.List;

import com.qygx.common.constant.UserConstants;
import com.qygx.common.utils.DateUtils;
import com.qygx.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.csm.mapper.CsmConsumaMapper;
import com.qygx.mes.csm.domain.CsmConsuma;
import com.qygx.mes.csm.service.ICsmConsumaService;

/**
 * 备件档案Service业务层处理
 *
 * @author qygx
 * @date 2022-09-09
 */
@Service
public class CsmConsumaServiceImpl implements ICsmConsumaService
{
    @Autowired
    private CsmConsumaMapper csmConsumaMapper;

    /**
     * 查询备件档案
     *
     * @param consumaId 备件档案主键
     * @return 备件档案
     */
    @Override
    public CsmConsuma selectCsmConsumaByConsumaId(Long consumaId)
    {
        return csmConsumaMapper.selectCsmConsumaByConsumaId(consumaId);
    }

    /**
     * 查询备件档案列表
     *
     * @param csmConsuma 备件档案
     * @return 备件档案
     */
    @Override
    public List<CsmConsuma> selectCsmConsumaList(CsmConsuma csmConsuma)
    {
        return csmConsumaMapper.selectCsmConsumaList(csmConsuma);
    }

    /**
     * 新增备件档案
     *
     * @param csmConsuma 备件档案
     * @return 结果
     */
    @Override
    public int insertCsmConsuma(CsmConsuma csmConsuma)
    {
        csmConsuma.setCreateTime(DateUtils.getNowDate());
        return csmConsumaMapper.insertCsmConsuma(csmConsuma);
    }

    /**
     * 修改备件档案
     *
     * @param csmConsuma 备件档案
     * @return 结果
     */
    @Override
    public int updateCsmConsuma(CsmConsuma csmConsuma)
    {
        csmConsuma.setUpdateTime(DateUtils.getNowDate());
        return csmConsumaMapper.updateCsmConsuma(csmConsuma);
    }

    /**
     * 批量删除备件档案
     *
     * @param consumaIds 需要删除的备件档案主键
     * @return 结果
     */
    @Override
    public int deleteCsmConsumaByConsumaIds(Long[] consumaIds)
    {
        return csmConsumaMapper.deleteCsmConsumaByConsumaIds(consumaIds);
    }

    /**
     * 删除备件档案信息
     *
     * @param consumaId 备件档案主键
     * @return 结果
     */
    @Override
    public int deleteCsmConsumaByConsumaId(Long consumaId)
    {
        return csmConsumaMapper.deleteCsmConsumaByConsumaId(consumaId);
    }

    @Override
    public CsmConsuma selectConsumaByCode(String consumaCode) {
        return csmConsumaMapper.selectConsumaByCode(consumaCode);
    }

    @Override
    public String checkConsumaCodeUnique(CsmConsuma csmConsuma) {
        CsmConsuma consuma = csmConsumaMapper.selectConsumaByCode(csmConsuma.getConsumaCode());
        Long consumaId = csmConsuma.getConsumaId()==null?-1L:csmConsuma.getConsumaId();
        if(StringUtils.isNotNull(consuma) && consuma.getConsumaId().longValue() != consumaId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
