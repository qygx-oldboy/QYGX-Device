package com.qygx.system.mapper;
import com.qygx.system.domain.Operators;
import com.qygx.system.domain.ProInspect;
import com.qygx.system.domain.QrCodeRecord;
import com.qygx.system.domain.dto.InspectDto;

import java.util.List;

public interface ProReportMapper {




    /**
     * 查询检查图表
     *
     *
     */
    public List<ProInspect> selectInspectChart(ProInspect proInspect);


    /**
     * 查询检查列表
     *
     *
     */
    public List<ProInspect> selectInspectList(ProInspect proInspect);




    /**
     * 查询品种（规格+材质）分组
     *
     *
     */
    public List<ProInspect> selectBreedList();

    /**
     * 根据开始时间，结束时间查
     *
     *
     */
    public List<ProInspect> selectInspectListByTime(ProInspect proInspect);


    /**
     * 查询inspect
     *
     * @param id inspect主键
     * @return inspect
     */
    public ProInspect selectInspectById(Long id);

    /**
     * 查询inspect
     *
     * @param code 批次码
     * @return inspect
     */
    public ProInspect selectInspectByCode(String code);



    /**
     * 新增inspect
     *
     * @param inspect inspect
     * @return 结果
     */
    public int insertInspect(ProInspect inspect);

    /**
     * 修改inspect
     *
     * @param inspect inspect
     * @return 结果
     */
    public int updateInspect(ProInspect inspect);

    /**
     * 删除inspect
     *
     * @param id inspect主键
     * @return 结果
     */
    public int deleteInspectById(Long id);

    /**
     * 批量删除inspect
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInspectByIds(Long[] ids);

    /**
     * 查询inspect
     *
     *
     */
    public ProInspect selectInspectByQrCode(String qrCode);


    /**
     * 查询每日的良率汇总情况
     *
     *
     */
    public List<InspectDto> selectDailyInspect(InspectDto inspectDto);


    /**
     * 查询subType下的的良率详情列表
     *
     *
     */
    public List<InspectDto> selectDailyDetail(InspectDto inspectDto);


    /**
     * 查询某一天的 全检批退和抽检批退
     *
     *
     */
    public List<InspectDto> selectDailyFullOrSpot(String name);


    /**
     * 查询某一天的 全检批退和抽检批退 的子集
     *
     *
     */
    public List<InspectDto> selectDailyByType(InspectDto inspectDto);

    /**
     * 查询批次号记录
     *
     *
     */
    public QrCodeRecord getQrCodeRecord(String qrCode);



    /**
     * 查询检验员下的的良率详情列表
     *
     *
     */
    public List<InspectDto> selectDailyPerson(InspectDto inspectDto);

    /**
     * 查询操作人列表
     *
     *
     */
    public List<Operators> selectOperators(Operators operators);

    /** 状态修改 **/
    public int updateQrCodeRecord(QrCodeRecord qrCodeRecord);

}
