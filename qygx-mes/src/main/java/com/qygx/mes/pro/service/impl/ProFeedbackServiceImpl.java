package com.qygx.mes.pro.service.impl;

import com.qygx.common.utils.DateUtils;
import com.qygx.mes.pro.domain.ProFeedback;
import com.qygx.mes.pro.mapper.ProFeedbackMapper;
import com.qygx.mes.pro.service.IProFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产报工记录Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-07-10
 */
@Service
public class ProFeedbackServiceImpl implements IProFeedbackService 
{
    @Autowired
    private ProFeedbackMapper proFeedbackMapper;

    /**
     * 查询生产报工记录
     * 
     * @param recordId 生产报工记录主键
     * @return 生产报工记录
     */
    @Override
    public ProFeedback selectProFeedbackByRecordId(Long recordId)
    {
        return proFeedbackMapper.selectProFeedbackByRecordId(recordId);
    }

    /**
     * 查询生产报工记录列表
     * 
     * @param proFeedback 生产报工记录
     * @return 生产报工记录
     */
    @Override
    public List<ProFeedback> selectProFeedbackList(ProFeedback proFeedback)
    {
        return proFeedbackMapper.selectProFeedbackList(proFeedback);
    }

    /**
     * 新增生产报工记录
     * 
     * @param proFeedback 生产报工记录
     * @return 结果
     */
    @Override
    public int insertProFeedback(ProFeedback proFeedback)
    {
        proFeedback.setCreateTime(DateUtils.getNowDate());
        return proFeedbackMapper.insertProFeedback(proFeedback);
    }

    /**
     * 修改生产报工记录
     * 
     * @param proFeedback 生产报工记录
     * @return 结果
     */
    @Override
    public int updateProFeedback(ProFeedback proFeedback)
    {
        proFeedback.setUpdateTime(DateUtils.getNowDate());
        return proFeedbackMapper.updateProFeedback(proFeedback);
    }

    /**
     * 批量删除生产报工记录
     * 
     * @param recordIds 需要删除的生产报工记录主键
     * @return 结果
     */
    @Override
    public int deleteProFeedbackByRecordIds(Long[] recordIds)
    {
        return proFeedbackMapper.deleteProFeedbackByRecordIds(recordIds);
    }

    /**
     * 删除生产报工记录信息
     * 
     * @param recordId 生产报工记录主键
     * @return 结果
     */
    @Override
    public int deleteProFeedbackByRecordId(Long recordId)
    {
        return proFeedbackMapper.deleteProFeedbackByRecordId(recordId);
    }
}
