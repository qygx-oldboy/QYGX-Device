package com.qygx.mes.cal.service.impl;

import java.util.List;
import com.qygx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.cal.mapper.CalShiftMapper;
import com.qygx.mes.cal.domain.CalShift;
import com.qygx.mes.cal.service.ICalShiftService;

/**
 * 班次设置Service业务层处理
 * 
 * @author qygx
 * @date 2022-09-26
 */
@Service
public class CalShiftServiceImpl implements ICalShiftService 
{
    @Autowired
    private CalShiftMapper calShiftMapper;

    /**
     * 查询班次设置
     * 
     * @param shiftId 班次设置主键
     * @return 班次设置
     */
    @Override
    public CalShift selectCalShiftByShiftId(Long shiftId)
    {
        return calShiftMapper.selectCalShiftByShiftId(shiftId);
    }

    /**
     * 查询班次设置列表
     * 
     * @param calShift 班次设置
     * @return 班次设置
     */
    @Override
    public List<CalShift> selectCalShiftList(CalShift calShift)
    {
        return calShiftMapper.selectCalShiftList(calShift);
    }

    /**
     * 新增班次设置
     * 
     * @param calShift 班次设置
     * @return 结果
     */
    @Override
    public int insertCalShift(CalShift calShift)
    {
        calShift.setCreateTime(DateUtils.getNowDate());
        return calShiftMapper.insertCalShift(calShift);
    }

    /**
     * 修改班次设置
     * 
     * @param calShift 班次设置
     * @return 结果
     */
    @Override
    public int updateCalShift(CalShift calShift)
    {
        calShift.setUpdateTime(DateUtils.getNowDate());
        return calShiftMapper.updateCalShift(calShift);
    }

    /**
     * 批量删除班次设置
     * 
     * @param shiftIds 需要删除的班次设置主键
     * @return 结果
     */
    @Override
    public int deleteCalShiftByShiftIds(Long[] shiftIds)
    {
        return calShiftMapper.deleteCalShiftByShiftIds(shiftIds);
    }

    /**
     * 删除班次设置信息
     * 
     * @param shiftId 班次设置主键
     * @return 结果
     */
    @Override
    public int deleteCalShiftByShiftId(Long shiftId)
    {
        return calShiftMapper.deleteCalShiftByShiftId(shiftId);
    }
}
