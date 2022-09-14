package com.qygx.mes.pro.service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import com.qygx.common.core.domain.Timeline;
import com.qygx.common.core.domain.TreeSelect;
import com.qygx.common.core.domain.entity.ItemType;
import com.qygx.mes.pro.domain.ProTrace;

/**
 * 生产追溯Service接口
 *
 * @author qygx
 * @date 2022-09-12
 */
public interface IProTraceService
{
    /**
     * 查询生产追溯
     *
     * @param traceId 生产追溯主键
     * @return 生产追溯
     */
    public ProTrace selectProTraceByTraceId(Long traceId);

    /**
     * 查询生产追溯列表
     *
     * @param proTrace 生产追溯
     * @return 生产追溯集合
     */
    public List<ProTrace> selectProTraceList(ProTrace proTrace);

    /**
     * 新增生产追溯
     *
     * @param proTrace 生产追溯
     * @return 结果
     */
    public int insertProTrace(ProTrace proTrace);

    /**
     * 修改生产追溯
     *
     * @param proTrace 生产追溯
     * @return 结果
     */
    public int updateProTrace(ProTrace proTrace);

    /**
     * 批量删除生产追溯
     *
     * @param traceIds 需要删除的生产追溯主键集合
     * @return 结果
     */
    public int deleteProTraceByTraceIds(Long[] traceIds);

    /**
     * 删除生产追溯信息
     *
     * @param traceId 生产追溯主键
     * @return 结果
     */
    public int deleteProTraceByTraceId(Long traceId);


    /**
     * 查询生产追溯历史
     *
     */
    public List<Timeline> getTraceList(ProTrace proTrace);
}
