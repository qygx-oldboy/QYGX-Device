package com.qygx.system.service;

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
}
