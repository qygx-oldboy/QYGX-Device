package com.qygx.system.mapper;
import com.qygx.system.domain.ProInspect;
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
}
