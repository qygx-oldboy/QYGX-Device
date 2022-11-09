package com.qygx.system.mapper;

import java.util.List;
import com.qygx.system.domain.DvMachineryRun;
import com.qygx.system.domain.dto.DvRunDto;
import org.apache.ibatis.annotations.Param;

/**
 * 运行记录Mapper接口
 *
 * @author qygx
 * @date 2022-09-20
 */
public interface DvMachineryRunMapper
{
    /**
     * 查询运行记录
     *
     * @param recordId 运行记录主键
     * @return 运行记录
     */
    public DvMachineryRun selectDvMachineryRunByRecordId(Long recordId);

    /**
     * 查询运行记录列表
     *
     * @param dvMachineryRun 运行记录
     * @return 运行记录集合
     */
    public List<DvMachineryRun> selectDvMachineryRunList(DvMachineryRun dvMachineryRun);

    /**
     * 新增运行记录
     *
     * @param dvMachineryRun 运行记录
     * @return 结果
     */
    public int insertDvMachineryRun(DvMachineryRun dvMachineryRun);

    /**
     * 修改运行记录
     *
     * @param dvMachineryRun 运行记录
     * @return 结果
     */
    public int updateDvMachineryRun(DvMachineryRun dvMachineryRun);

    /**
     * 删除运行记录
     *
     * @param recordId 运行记录主键
     * @return 结果
     */
    public int deleteDvMachineryRunByRecordId(Long recordId);

    /**
     * 批量删除运行记录
     *
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDvMachineryRunByRecordIds(Long[] recordIds);

    /**
     * 查询运行记录
     *
     * @param date 日期
     * @param shiftName 班次名称
     * @return 运行记录
     */
    public DvMachineryRun selectDvRunByShift(@Param("date")String date, @Param("shiftName")String shiftName, @Param("deviceCode")String deviceCode);


    /** 查询每天各工序总运行时间*/
    public List<DvRunDto> selectDailyRunTime();

}
