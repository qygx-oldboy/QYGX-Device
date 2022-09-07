package com.qygx.mes.dv.service.impl;

import com.qygx.common.constant.UserConstants;
import com.qygx.common.utils.DateUtils;
import com.qygx.common.utils.StringUtils;
import com.qygx.mes.dv.domain.DvCheckSubject;
import com.qygx.mes.dv.mapper.DvCheckSubjectMapper;
import com.qygx.mes.dv.service.IDvCheckSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 点检项目Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-18
 */
@Service
public class DvCheckSubjectServiceImpl implements IDvCheckSubjectService 
{
    @Autowired
    private DvCheckSubjectMapper dvCheckSubjectMapper;

    /**
     * 查询点检项目
     * 
     * @param recordId 点检项目主键
     * @return 点检项目
     */
    @Override
    public DvCheckSubject selectDvCheckSubjectByRecordId(Long recordId)
    {
        return dvCheckSubjectMapper.selectDvCheckSubjectByRecordId(recordId);
    }

    /**
     * 查询点检项目列表
     * 
     * @param dvCheckSubject 点检项目
     * @return 点检项目
     */
    @Override
    public List<DvCheckSubject> selectDvCheckSubjectList(DvCheckSubject dvCheckSubject)
    {
        return dvCheckSubjectMapper.selectDvCheckSubjectList(dvCheckSubject);
    }

    @Override
    public String checkSubjectUnique(DvCheckSubject dvCheckSubject) {
        DvCheckSubject subject = dvCheckSubjectMapper.checkSubjectUnique(dvCheckSubject);
        Long recordId = dvCheckSubject.getRecordId()==null?-1L:dvCheckSubject.getRecordId();
        if(StringUtils.isNotNull(subject) && subject.getRecordId().longValue() != recordId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }

        return UserConstants.UNIQUE;
    }

    /**
     * 新增点检项目
     * 
     * @param dvCheckSubject 点检项目
     * @return 结果
     */
    @Override
    public int insertDvCheckSubject(DvCheckSubject dvCheckSubject)
    {
        dvCheckSubject.setCreateTime(DateUtils.getNowDate());
        return dvCheckSubjectMapper.insertDvCheckSubject(dvCheckSubject);
    }

    /**
     * 修改点检项目
     * 
     * @param dvCheckSubject 点检项目
     * @return 结果
     */
    @Override
    public int updateDvCheckSubject(DvCheckSubject dvCheckSubject)
    {
        dvCheckSubject.setUpdateTime(DateUtils.getNowDate());
        return dvCheckSubjectMapper.updateDvCheckSubject(dvCheckSubject);
    }

    /**
     * 批量删除点检项目
     * 
     * @param recordIds 需要删除的点检项目主键
     * @return 结果
     */
    @Override
    public int deleteDvCheckSubjectByRecordIds(Long[] recordIds)
    {
        return dvCheckSubjectMapper.deleteDvCheckSubjectByRecordIds(recordIds);
    }

    /**
     * 删除点检项目信息
     * 
     * @param recordId 点检项目主键
     * @return 结果
     */
    @Override
    public int deleteDvCheckSubjectByRecordId(Long recordId)
    {
        return dvCheckSubjectMapper.deleteDvCheckSubjectByRecordId(recordId);
    }
}
