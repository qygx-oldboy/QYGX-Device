package com.qygx.mes.wm.service.impl;

import com.qygx.common.utils.DateUtils;
import com.qygx.mes.wm.domain.WmIssueLine;
import com.qygx.mes.wm.mapper.WmIssueLineMapper;
import com.qygx.mes.wm.service.IWmIssueLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产领料单行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-07-14
 */
@Service
public class WmIssueLineServiceImpl implements IWmIssueLineService 
{
    @Autowired
    private WmIssueLineMapper wmIssueLineMapper;

    /**
     * 查询生产领料单行
     * 
     * @param lineId 生产领料单行主键
     * @return 生产领料单行
     */
    @Override
    public WmIssueLine selectWmIssueLineByLineId(Long lineId)
    {
        return wmIssueLineMapper.selectWmIssueLineByLineId(lineId);
    }

    /**
     * 查询生产领料单行列表
     * 
     * @param wmIssueLine 生产领料单行
     * @return 生产领料单行
     */
    @Override
    public List<WmIssueLine> selectWmIssueLineList(WmIssueLine wmIssueLine)
    {
        return wmIssueLineMapper.selectWmIssueLineList(wmIssueLine);
    }

    /**
     * 新增生产领料单行
     * 
     * @param wmIssueLine 生产领料单行
     * @return 结果
     */
    @Override
    public int insertWmIssueLine(WmIssueLine wmIssueLine)
    {
        wmIssueLine.setCreateTime(DateUtils.getNowDate());
        return wmIssueLineMapper.insertWmIssueLine(wmIssueLine);
    }

    /**
     * 修改生产领料单行
     * 
     * @param wmIssueLine 生产领料单行
     * @return 结果
     */
    @Override
    public int updateWmIssueLine(WmIssueLine wmIssueLine)
    {
        wmIssueLine.setUpdateTime(DateUtils.getNowDate());
        return wmIssueLineMapper.updateWmIssueLine(wmIssueLine);
    }

    /**
     * 批量删除生产领料单行
     * 
     * @param lineIds 需要删除的生产领料单行主键
     * @return 结果
     */
    @Override
    public int deleteWmIssueLineByLineIds(Long[] lineIds)
    {
        return wmIssueLineMapper.deleteWmIssueLineByLineIds(lineIds);
    }

    /**
     * 删除生产领料单行信息
     * 
     * @param lineId 生产领料单行主键
     * @return 结果
     */
    @Override
    public int deleteWmIssueLineByLineId(Long lineId)
    {
        return wmIssueLineMapper.deleteWmIssueLineByLineId(lineId);
    }

    @Override
    public int deleteByIssueHeaderId(Long issueId) {
        return wmIssueLineMapper.deleteByIssueHeaderId(issueId);
    }
}
