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

    @Override
    public List<ProTrace> selectLastProTraceList(ProTrace proTrace) {
        return proTraceMapper.selectLastProTraceList(proTrace);
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
        Date startTime = proTrace.getStartTime();  //开始生产时间   2022-10-28 08:30:00
        List<CalShift> calShifts = calShiftMapper.selectCalShiftList(new CalShift()); //查询班次
        for (CalShift shift:
                calShifts ) {
            //如果 班次的结束时间 不大于开始时间，则为同一天，  否则则是跨天
            String date = DateUtils.parseDateToStr("yyyy-MM-dd",startTime);
            String upTime = date + " " +shift.getStartTime() + ":00";
            String offTime = date + " " + shift.getEndTime() + ":00";

            if( shift.getEndTime().compareTo(shift.getStartTime()) < 0){        //如果endTime 小于 startTime
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startTime);
                calendar.add(Calendar.DAY_OF_MONTH, -1);//减一天
                date = DateUtils.parseDateToStr("yyyy-MM-dd",calendar.getTime());
                upTime = date + " " +shift.getStartTime() + ":00";
            }
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            boolean isTime = DateUtils.isEffectiveDate(startTime, sf.parse(upTime),sf.parse(offTime));
            if(isTime){
                List<DvMachineryRun> runRecordList = new ArrayList<>();
                DvMachineryRun runRecord = new DvMachineryRun();
               // runRecord.setDeviceId(proTrace.getMachineryId());
                runRecord.setMachineryCode(proTrace.getMachineryCode());
                runRecord.setMachineryName(proTrace.getMachineryName());
                runRecord.setProcessId(proTrace.getProcessId());
                runRecord.setProcessCode(proTrace.getProcessCode());
                runRecord.setProcessName(proTrace.getProcessName());

                runRecord.setRecordDate(DateUtils.parseDate(date));
                runRecord.setShiftName(shift.getShiftName());
                runRecord.setStartTime(shift.getStartTime());
                runRecord.setEndTime(shift.getEndTime());
                long diff = proTrace.getEndTime().getTime() - (proTrace.getStartTime().getTime() - 1000);
                long runTime = diff / 60 / 1000;
                runRecord.setRunTime(runTime); //运行时间

                //如果生产结束时间 大于 班次结束时间  需要拆分两条记录
                if(proTrace.getEndTime().getTime() > sf.parse(offTime).getTime()){
                    DvMachineryRun runRecordClone   = runRecord.clone();
                    DvMachineryRun runRecordClone2   = runRecord.clone();
                    runRecordClone.setEndTime(offTime);
                    runRecordClone2.setStartTime(offTime);
                    runRecordList.add(runRecordClone);
                    runRecordList.add(runRecordClone2);
                }else{

                    runRecordList.add(runRecord);
                }
                this.handleRunRecord(runRecordList);
                break;
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
     *  公式 =  运行时间/（班次开始时间到当前时间）
     */

    public void handleRunRecord(List<DvMachineryRun> runRecordList) throws ParseException {

        for (DvMachineryRun runRecord :
                runRecordList) {
            String date = DateUtils.parseDateToStr("yyyy-MM-dd", runRecord.getRecordDate());
            DvMachineryRun dvRun = dvRunMapper.selectDvRunByShift( date, runRecord.getShiftName(),runRecord.getMachineryCode());

//            String upTime = date + " " +runRecord.getStartTime() + ":00";
//            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            long diff  = DateUtils.getNowDate().getTime() - sf.parse(upTime).getTime();
//            long min = Math.abs(diff)/ 60 / 1000; //分母
            if(StringUtils.isNull(dvRun)){
                //稼动率
             //   Double rate = (double)runRecord.getRunTime()/(min - 0);
             //   runRecord.setUtilizationRate(rate); //稼动率
                dvRunMapper.insertDvMachineryRun(runRecord);
            }
            else{
                long time = dvRun.getRunTime() + runRecord.getRunTime();
                dvRun.setRunTime(time);
            //    Double rate = (double)time/(min - 0);
             //   dvRun.setUtilizationRate(rate);
                dvRunMapper.updateDvMachineryRun(dvRun);
            }
        }
    }
}
