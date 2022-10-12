package com.qygx.mes.csm.service;

import java.util.List;
import com.qygx.mes.csm.domain.CsmRepairRecord;

/**
 * 备件修复记录Service接口
 * 
 * @author qygx
 * @date 2022-10-10
 */
public interface ICsmRepairRecordService 
{
    /**
     * 查询备件修复记录
     * 
     * @param recordId 备件修复记录主键
     * @return 备件修复记录
     */
    public CsmRepairRecord selectCsmRepairRecordByRecordId(Long recordId);

    /**
     * 查询备件修复记录列表
     * 
     * @param csmRepairRecord 备件修复记录
     * @return 备件修复记录集合
     */
    public List<CsmRepairRecord> selectCsmRepairRecordList(CsmRepairRecord csmRepairRecord);

    /**
     * 新增备件修复记录
     * 
     * @param csmRepairRecord 备件修复记录
     * @return 结果
     */
    public int insertCsmRepairRecord(CsmRepairRecord csmRepairRecord);

    /**
     * 修改备件修复记录
     * 
     * @param csmRepairRecord 备件修复记录
     * @return 结果
     */
    public int updateCsmRepairRecord(CsmRepairRecord csmRepairRecord);

    /**
     * 批量删除备件修复记录
     * 
     * @param recordIds 需要删除的备件修复记录主键集合
     * @return 结果
     */
    public int deleteCsmRepairRecordByRecordIds(Long[] recordIds);

    /**
     * 删除备件修复记录信息
     * 
     * @param recordId 备件修复记录主键
     * @return 结果
     */
    public int deleteCsmRepairRecordByRecordId(Long recordId);
}
