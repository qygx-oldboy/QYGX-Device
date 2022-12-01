package com.qygx.system.service;

import java.util.List;
import com.qygx.system.domain.DvMachineryRun;
import com.qygx.system.domain.dto.DvRunDto;
import com.qygx.system.domain.dto.CropRateDto;

/**
 * 运行记录Service接口
 *
 * @author qygx
 * @date 2022-09-20
 */
public interface IDvMachineryRunService
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
     * 批量删除运行记录
     *
     * @param recordIds 需要删除的运行记录主键集合
     * @return 结果
     */
    public int deleteDvMachineryRunByRecordIds(Long[] recordIds);

    /**
     * 删除运行记录信息
     *
     * @param recordId 运行记录主键
     * @return 结果
     */
    public int deleteDvMachineryRunByRecordId(Long recordId);


    /**
     * 查询运行记录
     *
     * @param date 日期
     * @param shiftName 班次名称
     * @param deviceCode 设备编号
     * @return 运行记录
     */
    public DvMachineryRun selectDvRunByShift(String date,String shiftName,String deviceCode);


    /** 查询各工序运行时间*/
    public List<DvRunDto> selectProcessRunTime(DvMachineryRun dvMachineryRun);


    /** 查询各人员运行时间*/
    public List<CropRateDto> selectPersonRunTime(DvMachineryRun dvMachineryRun);

    /** 查询各设备运行时间*/
    public List<CropRateDto> selectDeviceRunTime(DvMachineryRun dvMachineryRun);


    /** 查询人员列表姓名*/
    public List<String> selectPersonName();

    /** 查询设备名称列表*/
    public List<String> selectDeviceName();
}
