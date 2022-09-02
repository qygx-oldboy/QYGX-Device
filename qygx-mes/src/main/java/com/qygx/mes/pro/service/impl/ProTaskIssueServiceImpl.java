package com.qygx.mes.pro.service.impl;

import com.qygx.common.constant.UserConstants;
import com.qygx.common.utils.DateUtils;
import com.qygx.common.utils.StringUtils;
import com.qygx.mes.pro.domain.ProTaskIssue;
import com.qygx.mes.pro.mapper.ProTaskIssueMapper;
import com.qygx.mes.pro.service.IProTaskIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产任务投料Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-07-22
 */
@Service
public class ProTaskIssueServiceImpl implements IProTaskIssueService 
{
    @Autowired
    private ProTaskIssueMapper proTaskIssueMapper;

    /**
     * 查询生产任务投料
     * 
     * @param recordId 生产任务投料主键
     * @return 生产任务投料
     */
    @Override
    public ProTaskIssue selectProTaskIssueByRecordId(Long recordId)
    {
        return proTaskIssueMapper.selectProTaskIssueByRecordId(recordId);
    }

    /**
     * 查询生产任务投料列表
     * 
     * @param proTaskIssue 生产任务投料
     * @return 生产任务投料
     */
    @Override
    public List<ProTaskIssue> selectProTaskIssueList(ProTaskIssue proTaskIssue)
    {
        return proTaskIssueMapper.selectProTaskIssueList(proTaskIssue);
    }

    @Override
    public String checkUnique(ProTaskIssue proTaskIssue) {
        ProTaskIssue taskIssue = proTaskIssueMapper.checkUnique(proTaskIssue);
        if(StringUtils.isNotNull(taskIssue)){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增生产任务投料
     * 
     * @param proTaskIssue 生产任务投料
     * @return 结果
     */
    @Override
    public int insertProTaskIssue(ProTaskIssue proTaskIssue)
    {
        proTaskIssue.setCreateTime(DateUtils.getNowDate());
        return proTaskIssueMapper.insertProTaskIssue(proTaskIssue);
    }

    /**
     * 修改生产任务投料
     * 
     * @param proTaskIssue 生产任务投料
     * @return 结果
     */
    @Override
    public int updateProTaskIssue(ProTaskIssue proTaskIssue)
    {
        proTaskIssue.setUpdateTime(DateUtils.getNowDate());
        return proTaskIssueMapper.updateProTaskIssue(proTaskIssue);
    }

    /**
     * 批量删除生产任务投料
     * 
     * @param recordIds 需要删除的生产任务投料主键
     * @return 结果
     */
    @Override
    public int deleteProTaskIssueByRecordIds(Long[] recordIds)
    {
        return proTaskIssueMapper.deleteProTaskIssueByRecordIds(recordIds);
    }

    /**
     * 删除生产任务投料信息
     * 
     * @param recordId 生产任务投料主键
     * @return 结果
     */
    @Override
    public int deleteProTaskIssueByRecordId(Long recordId)
    {
        return proTaskIssueMapper.deleteProTaskIssueByRecordId(recordId);
    }
}
