package com.qygx.mes.pro.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.qygx.common.core.domain.Timeline;
import com.qygx.common.core.domain.entity.ItemType;
import com.qygx.common.utils.DateUtils;
import com.qygx.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.pro.mapper.ProTraceMapper;
import com.qygx.mes.pro.domain.ProTrace;
import com.qygx.mes.pro.service.IProTraceService;

/**
 * 生产追溯Service业务层处理
 *
 * @author qygx
 * @date 2022-09-12
 */
@Service
public class ProTraceServiceImpl implements IProTraceService
{
    @Autowired
    private ProTraceMapper proTraceMapper;

    /**
     * 查询生产追溯
     *
     * @param traceId 生产追溯主键
     * @return 生产追溯
     */
    @Override
    public ProTrace selectProTraceByTraceId(Long traceId)
    {
        return proTraceMapper.selectProTraceByTraceId(traceId);
    }

    /**
     * 查询生产追溯列表
     *
     * @param proTrace 生产追溯
     * @return 生产追溯
     */
    @Override
    public List<ProTrace> selectProTraceList(ProTrace proTrace)
    {
        return proTraceMapper.selectProTraceList(proTrace);
    }

    /**
     * 新增生产追溯
     *
     * @param proTrace 生产追溯
     * @return 结果
     */
    @Override
    public int insertProTrace(ProTrace proTrace)
    {
        proTrace.setCreateTime(DateUtils.getNowDate());
        return proTraceMapper.insertProTrace(proTrace);
    }

    /**
     * 修改生产追溯
     *
     * @param proTrace 生产追溯
     * @return 结果
     */
    @Override
    public int updateProTrace(ProTrace proTrace)
    {
        proTrace.setUpdateTime(DateUtils.getNowDate());
        return proTraceMapper.updateProTrace(proTrace);
    }

    /**
     * 批量删除生产追溯
     *
     * @param traceIds 需要删除的生产追溯主键
     * @return 结果
     */
    @Override
    public int deleteProTraceByTraceIds(Long[] traceIds)
    {
        return proTraceMapper.deleteProTraceByTraceIds(traceIds);
    }

    /**
     * 删除生产追溯信息
     *
     * @param traceId 生产追溯主键
     * @return 结果
     */
    @Override
    public int deleteProTraceByTraceId(Long traceId)
    {
        return proTraceMapper.deleteProTraceByTraceId(traceId);
    }

    @Override
    public List<Timeline> getTraceList(ProTrace proTrace) {

        List<Timeline> tlist = new ArrayList<Timeline>();
        List<ProTrace> proTraces = proTraceMapper.selectProTraceList(proTrace);
        for (ProTrace trace:
             proTraces) {
            Timeline tl = new Timeline();
            tl.setTimestamp(trace.getStartTime());
            tl.setContent("["+trace.getItemCode()+"]"+ trace.getProcessName()+"开始 操作人["+ trace.getOperator()+"]" + "操作设备[" + trace.getMachineryCode()+ "]");
            tlist.add(tl);
            if(StringUtils.isNotNull(trace.getEndTime())){
                tl = new Timeline();
                tl.setTimestamp(trace.getEndTime());
                tl.setContent("["+trace.getItemCode()+"]"+ trace.getProcessName()+"完成");
                tlist.add(tl);
            }
        }
        return tlist;
    }
}
