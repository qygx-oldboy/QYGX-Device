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
import com.qygx.system.domain.DeviceArchives;
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
    public int insertProTrace(ProTrace proTrace) throws ParseException, CloneNotSupportedException {
        //todo 判断班次
        Date startTime = proTrace.getStartTime();  //开始生产时间   2022-09-27 08:00:00
        List<CalShift> calShifts = calShiftMapper.selectCalShiftList(new CalShift());
        for (CalShift shift:
                calShifts ) {
            //如果 班次的结束时间 不大于开始时间，则为同一天，  否则则是跨天
            String date = DateUtils.parseDateToStr("yyyy-MM-dd",startTime);
            String upTime = date + " " +shift.getStartTime() + ":00";
            String offTime = date + " " + shift.getEndTime() + ":00";
            int timeFlag = shift.getEndTime().compareTo(shift.getStartTime());
            if(timeFlag < 0){                 //timeFlag <0 说明endTime 小于 startTime
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startTime);
                calendar.add(Calendar.DAY_OF_MONTH, -1);//减一天
                date = DateUtils.parseDateToStr("yyyy-MM-dd",calendar.getTime());
                upTime = date + " " +shift.getStartTime() + ":00";
            }
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            boolean isTime = DateUtils.isEffectiveDate(startTime, sf.parse(upTime),sf.parse(offTime));
            if(isTime){
                List <ProTrace> proTraces = new ArrayList<>();
                //如果生产结束时间 大于 班次结束时间  需要拆分两条记录
                if(proTrace.getEndTime().getTime() > sf.parse(offTime).getTime()){
                    ProTrace proTrace2   = proTrace.clone();
                    ProTrace proTrace3   = proTrace.clone();
                    proTrace2.setEndTime(sf.parse(offTime));
                    proTrace3.setStartTime(sf.parse(offTime));
                    proTraces.add(proTrace2);
                    proTraces.add(proTrace3);
                    //diff  = sf.parse(offTime).getTime() - proTrace.getStartTime().getTime();
                    //long diff2  =  proTrace.getEndTime().getTime() - sf.parse(offTime).getTime();  //余出的运行时间 到下一个班次
                }else{
                    proTraces.add(proTrace);
                }

                this.handleRunRecord(proTraces);

//                long diff  = proTrace.getEndTime().getTime() - proTrace.getStartTime().getTime();
//                DeviceArchives device = new DeviceArchives();
//                device.setDeviceId(proTrace.getMachineryId());
//                device.setDeviceCode(proTrace.getMachineryCode());
//                device.setName(proTrace.getMachineryName());
//                long runTime = diff / 60 / 1000; //运行时间
//                //todo 查询当天班次的记录是否生成，未生成则插入，生成则更新
//                this.savaRunRecord(date,shift,device,runTime);
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


    /**
     * 保存运行记录
     * @param date 日期
     * @param shift  班次
     * @param device  设备
     *
     *  公式 =  运行时间/（班次结束时间 - 班次开始时间 - 计划停机时间0）
     */
    public void savaRunRecord(String date, CalShift shift, DeviceArchives device, Long runTime) throws ParseException {

        //todo 查询当天班次的记录是否生成，未生成则插入，生成则更新
        DvMachineryRun dvRun = dvRunMapper.selectDvRunByShift(date, shift.getShiftName(),device.getDeviceId());
       // String upTime = date + " " +shift.getStartTime() + ":00";
        String offTime = date + " " + shift.getEndTime() + ":00";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long diff  = sf.parse(offTime).getTime() - DateUtils.getNowDate().getTime();
        long min = Math.abs(diff)/ 60 / 1000; //分母
        if(StringUtils.isNull(dvRun)){
            dvRun = new DvMachineryRun();
            dvRun.setDeviceId(device.getDeviceId());
            dvRun.setMachineryCode(device.getDeviceCode());
            dvRun.setMachineryName(device.getName());
            dvRun.setRecordDate(DateUtils.parseDate(date));
            dvRun.setShiftName(shift.getShiftName());
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



    public void handleRunRecord(List<ProTrace> proTraces) throws ParseException {
        for (ProTrace tarce :
                proTraces) {
            //todo 添加设备运行记录  计算设备稼动率
            Date startTime = tarce.getStartTime();  //开始生产时间   2022-09-27 08:00:00
            startTime.setTime(startTime.getTime() + 1000);
            List<CalShift> calShifts = calShiftMapper.selectCalShiftList(new CalShift());
            for (CalShift shift :
                    calShifts) {
                //如果 班次的结束时间 不大于开始时间，则为同一天，  否则则是跨天
                String date = DateUtils.parseDateToStr("yyyy-MM-dd", startTime);
                String upTime = date + " " + shift.getStartTime() + ":00";   //2022-09-27 08:00:00
                String offTime = date + " " + shift.getEndTime() + ":00";    //2022-09-27 20:00:00
                int timeFlag = shift.getEndTime().compareTo(shift.getStartTime());
                if (timeFlag < 0) {                 //timeFlag <0 说明endTime 小于 startTime
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startTime);
                    calendar.add(Calendar.DAY_OF_MONTH, -1);//减一天
                    date = DateUtils.parseDateToStr("yyyy-MM-dd", calendar.getTime());
                    upTime = date + " " + shift.getStartTime() + ":00";
                }

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                boolean isTime = DateUtils.isEffectiveDate(startTime, sf.parse(upTime), sf.parse(offTime));
                if (isTime) {
                    long diff = tarce.getEndTime().getTime() - (tarce.getStartTime().getTime() - 1000);
                    DeviceArchives device = new DeviceArchives();
                    device.setDeviceId(tarce.getMachineryId());
                    device.setDeviceCode(tarce.getMachineryCode());
                    device.setName(tarce.getMachineryName());
                    long runTime = diff / 60 / 1000; //运行时间
                    //todo 查询当天班次的记录是否生成，未生成则插入，生成则更新
                    this.savaRunRecord(date, shift, device, runTime);
                }
            }
        }
    }
}
