package com.qygx.mes.csm.service.impl;

import java.util.List;
import com.qygx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.csm.mapper.CsmRepairRecordMapper;
import com.qygx.mes.csm.domain.CsmRepairRecord;
import com.qygx.mes.csm.service.ICsmRepairRecordService;

/**
 * 备件修复记录Service业务层处理
 * 
 * @author qygx
 * @date 2022-10-10
 */
@Service
public class CsmRepairRecordServiceImpl implements ICsmRepairRecordService 
{
    @Autowired
    private CsmRepairRecordMapper csmRepairRecordMapper;

    /**
     * 查询备件修复记录
     * 
     * @param recordId 备件修复记录主键
     * @return 备件修复记录
     */
    @Override
    public CsmRepairRecord selectCsmRepairRecordByRecordId(Long recordId)
    {
        return csmRepairRecordMapper.selectCsmRepairRecordByRecordId(recordId);
    }

    /**
     * 查询备件修复记录列表
     * 
     * @param csmRepairRecord 备件修复记录
     * @return 备件修复记录
     */
    @Override
    public List<CsmRepairRecord> selectCsmRepairRecordList(CsmRepairRecord csmRepairRecord)
    {
        return csmRepairRecordMapper.selectCsmRepairRecordList(csmRepairRecord);
    }

    /**
     * 新增备件修复记录
     * 
     * @param csmRepairRecord 备件修复记录
     * @return 结果
     */
    @Override
    public int insertCsmRepairRecord(CsmRepairRecord csmRepairRecord)
    {
        csmRepairRecord.setCreateTime(DateUtils.getNowDate());
        return csmRepairRecordMapper.insertCsmRepairRecord(csmRepairRecord);
    }

    /**
     * 修改备件修复记录
     * 
     * @param csmRepairRecord 备件修复记录
     * @return 结果
     */
    @Override
    public int updateCsmRepairRecord(CsmRepairRecord csmRepairRecord)
    {
        csmRepairRecord.setUpdateTime(DateUtils.getNowDate());
        return csmRepairRecordMapper.updateCsmRepairRecord(csmRepairRecord);
    }

    /**
     * 批量删除备件修复记录
     * 
     * @param recordIds 需要删除的备件修复记录主键
     * @return 结果
     */
    @Override
    public int deleteCsmRepairRecordByRecordIds(Long[] recordIds)
    {
        return csmRepairRecordMapper.deleteCsmRepairRecordByRecordIds(recordIds);
    }

    /**
     * 删除备件修复记录信息
     * 
     * @param recordId 备件修复记录主键
     * @return 结果
     */
    @Override
    public int deleteCsmRepairRecordByRecordId(Long recordId)
    {
        return csmRepairRecordMapper.deleteCsmRepairRecordByRecordId(recordId);
    }
}
