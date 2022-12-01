package com.qygx.mes.csm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.csm.mapper.CsmConsumaLineMapper;
import com.qygx.mes.csm.domain.CsmConsumaLine;
import com.qygx.mes.csm.service.ICsmConsumaLineService;

/**
 * 备件周期项Service业务层处理
 *
 * @author qygx
 * @date 2022-11-25
 */
@Service
public class CsmConsumaLineServiceImpl implements ICsmConsumaLineService
{
    @Autowired
    private CsmConsumaLineMapper csmConsumaLineMapper;

    /**
     * 查询备件周期项
     *
     * @param lineId 备件周期项主键
     * @return 备件周期项
     */
    @Override
    public CsmConsumaLine selectCsmConsumaLineByLineId(Long lineId)
    {
        return csmConsumaLineMapper.selectCsmConsumaLineByLineId(lineId);
    }

    /**
     * 查询备件周期项列表
     *
     * @param csmConsumaLine 备件周期项
     * @return 备件周期项
     */
    @Override
    public List<CsmConsumaLine> selectCsmConsumaLineList(CsmConsumaLine csmConsumaLine)
    {
        return csmConsumaLineMapper.selectCsmConsumaLineList(csmConsumaLine);
    }

    /**
     * 新增备件周期项
     *
     * @param csmConsumaLine 备件周期项
     * @return 结果
     */
    @Override
    public int insertCsmConsumaLine(CsmConsumaLine csmConsumaLine)
    {
        return csmConsumaLineMapper.insertCsmConsumaLine(csmConsumaLine);
    }

    /**
     * 修改备件周期项
     *
     * @param csmConsumaLine 备件周期项
     * @return 结果
     */
    @Override
    public int updateCsmConsumaLine(CsmConsumaLine csmConsumaLine)
    {
        return csmConsumaLineMapper.updateCsmConsumaLine(csmConsumaLine);
    }

    /**
     * 批量删除备件周期项
     *
     * @param lineIds 需要删除的备件周期项主键
     * @return 结果
     */
    @Override
    public int deleteCsmConsumaLineByLineIds(Long[] lineIds)
    {
        return csmConsumaLineMapper.deleteCsmConsumaLineByLineIds(lineIds);
    }

    /**
     * 删除备件周期项信息
     *
     * @param lineId 备件周期项主键
     * @return 结果
     */
    @Override
    public int deleteCsmConsumaLineByLineId(Long lineId)
    {
        return csmConsumaLineMapper.deleteCsmConsumaLineByLineId(lineId);
    }

    @Override
    public List<CsmConsumaLine> selectLineByConsumaId(Long consumaId) {
        return csmConsumaLineMapper.selectLineByConsumaId(consumaId);
    }
}
