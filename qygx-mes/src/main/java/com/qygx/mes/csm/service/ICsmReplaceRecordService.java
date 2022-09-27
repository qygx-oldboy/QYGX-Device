package com.qygx.mes.csm.service;

import java.util.List;
import com.qygx.mes.csm.domain.CsmReplaceRecord;

/**
 * 备件更换记录Service接口
 *
 * @author qygx
 * @date 2022-09-22
 */
public interface ICsmReplaceRecordService
{
    /**
     * 查询备件更换记录
     *
     * @param recordId 备件更换记录主键
     * @return 备件更换记录
     */
    public CsmReplaceRecord selectCsmReplaceRecordByRecordId(Long recordId);

    /**
     * 查询备件更换记录列表
     *
     * @param csmReplaceRecord 备件更换记录
     * @return 备件更换记录集合
     */
    public List<CsmReplaceRecord> selectCsmReplaceRecordList(CsmReplaceRecord csmReplaceRecord);

    /**
     * 新增备件更换记录
     *
     * @param csmReplaceRecord 备件更换记录
     * @return 结果
     */
    public int insertCsmReplaceRecord(CsmReplaceRecord csmReplaceRecord);

    /**
     * 修改备件更换记录
     *
     * @param csmReplaceRecord 备件更换记录
     * @return 结果
     */
    public int updateCsmReplaceRecord(CsmReplaceRecord csmReplaceRecord);

    /**
     * 批量删除备件更换记录
     *
     * @param recordIds 需要删除的备件更换记录主键集合
     * @return 结果
     */
    public int deleteCsmReplaceRecordByRecordIds(Long[] recordIds);

    /**
     * 删除备件更换记录信息
     *
     * @param recordId 备件更换记录主键
     * @return 结果
     */
    public int deleteCsmReplaceRecordByRecordId(Long recordId);
}
