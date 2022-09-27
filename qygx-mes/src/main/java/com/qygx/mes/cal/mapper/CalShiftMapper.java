package com.qygx.mes.cal.mapper;

import java.util.List;
import com.qygx.mes.cal.domain.CalShift;

/**
 * 班次设置Mapper接口
 * 
 * @author qygx
 * @date 2022-09-26
 */
public interface CalShiftMapper 
{
    /**
     * 查询班次设置
     * 
     * @param shiftId 班次设置主键
     * @return 班次设置
     */
    public CalShift selectCalShiftByShiftId(Long shiftId);

    /**
     * 查询班次设置列表
     * 
     * @param calShift 班次设置
     * @return 班次设置集合
     */
    public List<CalShift> selectCalShiftList(CalShift calShift);

    /**
     * 新增班次设置
     * 
     * @param calShift 班次设置
     * @return 结果
     */
    public int insertCalShift(CalShift calShift);

    /**
     * 修改班次设置
     * 
     * @param calShift 班次设置
     * @return 结果
     */
    public int updateCalShift(CalShift calShift);

    /**
     * 删除班次设置
     * 
     * @param shiftId 班次设置主键
     * @return 结果
     */
    public int deleteCalShiftByShiftId(Long shiftId);

    /**
     * 批量删除班次设置
     * 
     * @param shiftIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCalShiftByShiftIds(Long[] shiftIds);
}
