package com.qygx.system.service.impl;

import java.util.List;
import com.qygx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.system.mapper.DvMachineryRunMapper;
import com.qygx.system.domain.DvMachineryRun;
import com.qygx.system.service.IDvMachineryRunService;

/**
 * 运行记录Service业务层处理
 *
 * @author qygx
 * @date 2022-09-20
 */
@Service
public class DvMachineryRunServiceImpl implements IDvMachineryRunService
{
    @Autowired
    private DvMachineryRunMapper dvMachineryRunMapper;

    /**
     * 查询运行记录
     *
     * @param recordId 运行记录主键
     * @return 运行记录
     */
    @Override
    public DvMachineryRun selectDvMachineryRunByRecordId(Long recordId)
    {
        return dvMachineryRunMapper.selectDvMachineryRunByRecordId(recordId);
    }

    /**
     * 查询运行记录列表
     *
     * @param dvMachineryRun 运行记录
     * @return 运行记录
     */
    @Override
    public List<DvMachineryRun> selectDvMachineryRunList(DvMachineryRun dvMachineryRun)
    {
        return dvMachineryRunMapper.selectDvMachineryRunList(dvMachineryRun);
    }

    /**
     * 新增运行记录
     *
     * @param dvMachineryRun 运行记录
     * @return 结果
     */
    @Override
    public int insertDvMachineryRun(DvMachineryRun dvMachineryRun)
    {
        dvMachineryRun.setCreateTime(DateUtils.getNowDate());
        return dvMachineryRunMapper.insertDvMachineryRun(dvMachineryRun);
    }

    /**
     * 修改运行记录
     *
     * @param dvMachineryRun 运行记录
     * @return 结果
     */
    @Override
    public int updateDvMachineryRun(DvMachineryRun dvMachineryRun)
    {
        dvMachineryRun.setUpdateTime(DateUtils.getNowDate());
        return dvMachineryRunMapper.updateDvMachineryRun(dvMachineryRun);
    }

    /**
     * 批量删除运行记录
     *
     * @param recordIds 需要删除的运行记录主键
     * @return 结果
     */
    @Override
    public int deleteDvMachineryRunByRecordIds(Long[] recordIds)
    {
        return dvMachineryRunMapper.deleteDvMachineryRunByRecordIds(recordIds);
    }

    /**
     * 删除运行记录信息
     *
     * @param recordId 运行记录主键
     * @return 结果
     */
    @Override
    public int deleteDvMachineryRunByRecordId(Long recordId)
    {
        return dvMachineryRunMapper.deleteDvMachineryRunByRecordId(recordId);
    }


    /**
     * 查询运行记录
     *
     * @param date 日期
     * @param shiftName 班次名称
     * @return 运行记录
     */
    @Override
    public DvMachineryRun selectDvRunByShift(String date, String shiftName,Long deviceId) {
        return dvMachineryRunMapper.selectDvRunByShift(date,shiftName,deviceId);
    }
}
