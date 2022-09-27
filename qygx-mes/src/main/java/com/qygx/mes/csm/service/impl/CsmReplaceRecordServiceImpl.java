package com.qygx.mes.csm.service.impl;

import java.util.List;
import com.qygx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.csm.mapper.CsmReplaceRecordMapper;
import com.qygx.mes.csm.domain.CsmReplaceRecord;
import com.qygx.mes.csm.service.ICsmReplaceRecordService;

/**
 * 备件更换记录Service业务层处理
 *
 * @author qygx
 * @date 2022-09-22
 */
@Service
public class CsmReplaceRecordServiceImpl implements ICsmReplaceRecordService
{
    @Autowired
    private CsmReplaceRecordMapper csmReplaceRecordMapper;

    /**
     * 查询备件更换记录
     *
     * @param recordId 备件更换记录主键
     * @return 备件更换记录
     */
    @Override
    public CsmReplaceRecord selectCsmReplaceRecordByRecordId(Long recordId)
    {
        return csmReplaceRecordMapper.selectCsmReplaceRecordByRecordId(recordId);
    }

    /**
     * 查询备件更换记录列表
     *
     * @param csmReplaceRecord 备件更换记录
     * @return 备件更换记录
     */
    @Override
    public List<CsmReplaceRecord> selectCsmReplaceRecordList(CsmReplaceRecord csmReplaceRecord)
    {
        return csmReplaceRecordMapper.selectCsmReplaceRecordList(csmReplaceRecord);
    }

    /**
     * 新增备件更换记录
     *
     * @param csmReplaceRecord 备件更换记录
     * @return 结果
     */
    @Override
    public int insertCsmReplaceRecord(CsmReplaceRecord csmReplaceRecord)
    {
        csmReplaceRecord.setCreateTime(DateUtils.getNowDate());
        return csmReplaceRecordMapper.insertCsmReplaceRecord(csmReplaceRecord);
    }

    /**
     * 修改备件更换记录
     *
     * @param csmReplaceRecord 备件更换记录
     * @return 结果
     */
    @Override
    public int updateCsmReplaceRecord(CsmReplaceRecord csmReplaceRecord)
    {
        csmReplaceRecord.setUpdateTime(DateUtils.getNowDate());
        return csmReplaceRecordMapper.updateCsmReplaceRecord(csmReplaceRecord);
    }

    /**
     * 批量删除备件更换记录
     *
     * @param recordIds 需要删除的备件更换记录主键
     * @return 结果
     */
    @Override
    public int deleteCsmReplaceRecordByRecordIds(Long[] recordIds)
    {
        return csmReplaceRecordMapper.deleteCsmReplaceRecordByRecordIds(recordIds);
    }

    /**
     * 删除备件更换记录信息
     *
     * @param recordId 备件更换记录主键
     * @return 结果
     */
    @Override
    public int deleteCsmReplaceRecordByRecordId(Long recordId)
    {
        return csmReplaceRecordMapper.deleteCsmReplaceRecordByRecordId(recordId);
    }
}
