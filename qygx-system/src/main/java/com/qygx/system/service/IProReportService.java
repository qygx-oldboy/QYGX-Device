package com.qygx.system.service;

import com.qygx.common.core.domain.entity.SysUser;
import com.qygx.system.domain.ProInspect;
import com.qygx.system.domain.vo.InspectVo;
import com.qygx.system.domain.vo.QualityVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 数据报表 服务层
 *
 * @author ruoyi
 */
public interface IProReportService {

    /**
     * 按品种（=规格.材质）查看检验数和良率
     *
     */
    public List<InspectVo> selectInspectList(ProInspect proInspect) throws ParseException;

    /**
     * 查询inspect列表
     *
     * @param proInspect inspect
     * @return inspect集合
     */
    public List<ProInspect> selectProInspectList(ProInspect proInspect);

    /**
     * 查分组
     *
     */
    public List selectBreedList();


    /**
     * 查当前班次的 记录
     *
     */
    public List<InspectVo> selectShiftInspectList(ProInspect proInspect) throws ParseException;

    /**
     * 基础信息
     *
     */
    public QualityVo info() throws ParseException;

    /**
     * 查询inspect
     *
     * @param id inspect主键
     * @return inspect
     */
    public ProInspect selectProInspectById(Long id);


    /**
     * 新增inspect
     *
     * @param proInspect inspect
     * @return 结果
     */
    public int insertProInspect(ProInspect proInspect);

    /**
     * 修改inspect
     *
     * @param proInspect inspect
     * @return 结果
     */
    public int updateProInspect(ProInspect proInspect);

    /**
     * 批量删除inspect
     *
     * @param ids 需要删除的inspect主键集合
     * @return 结果
     */
    public int deleteProInspectByIds(Long[] ids);

    /**
     * 删除inspect信息
     *
     * @param id inspect主键
     * @return 结果
     */
    public int deleteProInspectById(Long id);


    /**
     * 导入检测数据
     *
     * @param inspectList 检测列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importInspect(List<ProInspect> inspectList, Boolean isUpdateSupport, String operName);

}
