package com.qygx.mes.pro.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson2.JSONArray;
import com.qygx.common.core.domain.Timeline;
import com.qygx.common.core.domain.entity.ItemType;
import com.qygx.common.utils.DateUtils;
import com.qygx.common.utils.StringUtils;
import com.qygx.mes.cal.domain.CalShift;
import com.qygx.mes.cal.mapper.CalShiftMapper;
import com.qygx.system.domain.DvMachineryRun;
import com.qygx.system.mapper.DvMachineryRunMapper;
import com.qygx.system.service.IDvMachineryRunService;
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

    @Autowired
    private CalShiftMapper calShiftMapper;

    @Autowired
    private DvMachineryRunMapper dvRunMapper;



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
    public int insertProTrace(ProTrace proTrace)  throws ParseException
    {
        //todo 添加设备运行记录  计算设备稼动率
        Date startTime = proTrace.getStartTime();  //开始生产时间
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date hmTime =df.parse(df.format(startTime));
        List<CalShift> calShifts = calShiftMapper.selectCalShiftList(new CalShift());
        for (CalShift shift:
                calShifts ) {
            boolean isTime = DateUtils.isEffectiveDate(hmTime, df.parse(shift.getStartTime()),df.parse(shift.getEndTime()));
            if(isTime){
                String upTime = DateUtils.getDate() + " " +shift.getStartTime() + ":00";
                String offTime = DateUtils.getDate() + " " + shift.getEndTime() + ":00";
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long diff  = sf.parse(offTime).getTime() - sf.parse(upTime).getTime();
                long min = diff / 60 / 1000; //相差多少分钟
                long diff2  = proTrace.getEndTime().getTime() - proTrace.getStartTime().getTime();
                long runTime = diff2 / 60 / 1000; //运行时间

//                if(proTrace.getEndTime().getTime() > sf.parse(offTime).getTime()){
//                    long diff3  = sf.parse(offTime).getTime() - proTrace.getStartTime().getTime();
//                    long diff4  =  proTrace.getEndTime().getTime() - sf.parse(offTime).getTime();  //余出的运行时间
//                }
                String date = DateUtils.parseDateToStr("yyyy-MM-dd",startTime);
                if(shift.getShiftName() == "夜班"){
                    boolean isBefore = DateUtils.isEffectiveDate(hmTime,df.parse("00:00"),df.parse(shift.getEndTime()));
                    if(isBefore){
                        //减去一天
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(startTime);
                        calendar.add(Calendar.DAY_OF_MONTH, -1);
                        date = DateUtils.parseDateToStr("yyyy-MM-dd",calendar.getTime());
                    }
                }

                //todo 查询当天班次的记录是否生成，未生成则插入，生成则更新
                DvMachineryRun dvRun = dvRunMapper.selectDvRunByShift(date, shift.getShiftName(),proTrace.getMachineryId());
                if(StringUtils.isNull(dvRun)){
                    dvRun = new DvMachineryRun();
                    dvRun.setDeviceId(proTrace.getMachineryId());
                    dvRun.setMachineryCode(proTrace.getMachineryCode());
                    dvRun.setMachineryName(proTrace.getMachineryName());
                    dvRun.setRecordDate(DateUtils.parseDate(date));
                    dvRun.setshiftName(shift.getShiftName());
                    dvRun.setStartTime(shift.getStartTime());
                    dvRun.setEndTime(shift.getEndTime());
                    dvRun.setRunTime(runTime); //运行时间计算
                    //稼动率 =  运行时间/（下班时间-上班时间-计划停机时间）
                    Double rate = (double)runTime/(min - 0);
                    dvRun.setUtilizationRate(rate); //稼动率
                    dvRunMapper.insertDvMachineryRun(dvRun);
                }
                else{
                    long time = dvRun.getRunTime() + runTime;
                    dvRun.setRunTime(time);
                    Double rate = (double)time/(min - 0);
                    dvRun.setUtilizationRate(rate);
                    dvRunMapper.updateDvMachineryRun(dvRun);
                }
            }
        }

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

        if(StringUtils.isNotNull(proTrace.getItemCode())){
            int index = proTrace.getItemCode().lastIndexOf(".");  //获取最后一个字符的下标
            proTrace.setItemCode(proTrace.getItemCode().substring(0,index));  //截取字符串 0-下标位置
        }
        List<ProTrace> proTraces = proTraceMapper.selectProTraceHistory(proTrace);
        for (ProTrace trace:
             proTraces) {
            Timeline tl = new Timeline();
            tl.setTimestamp(trace.getStartTime());
            tl.setSize("large");
            tl.setColor("#0bbd87");
            String content = "["+trace.getItemCode()+"]"+ trace.getProcessName()+"开始";
            String operator = "操作人:"+ trace.getOperator()+"";
            String device = "操作设备:" + trace.getMachineryCode()+ "";
            tl.setContent(content);
            tl.setOperator(operator);
            tl.setDevice(device);
            String runParam = trace.getdeviceParam();
            JSONArray jsonArray  = JSONArray.parseArray(runParam);
            tl.setRunParam(jsonArray);

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
