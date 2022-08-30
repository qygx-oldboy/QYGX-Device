package com.qygx.quartz.service.impl;
import com.qygx.common.utils.DateUtils;
import com.qygx.common.utils.StringUtils;
import com.qygx.quartz.service.DeviceMaintainService;
import com.qygx.system.domain.*;
import com.qygx.system.mapper.DeviceArchivesMapper;
import com.qygx.system.mapper.DeviceMaintainSheetMapper;
import com.qygx.system.mapper.MaintainPlanMapper;
import com.qygx.system.mapper.MiddleDevicePlanMapper;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DeviceMaintainServiceImpl implements DeviceMaintainService {

    @Autowired
    private MaintainPlanMapper planMapper;

    @Autowired
    private DeviceArchivesMapper archivesMapper;


    @Autowired
    private MiddleDevicePlanMapper devicePlanMapper;


    @Autowired
    private DeviceMaintainSheetMapper sheetMapper;








    /**
     * 生成保养单
     *
     */
    @Override
    public void generateSheet() {

        //todo  查询所有开启的保养计划，
        //废弃todo  遍历保养计划， 根据设备类型  添加/删除 “设备-计划中间表”
        //废弃todo  遍历中间表，当“下次时间”为空的时候，则根据保养计划首次时间，生成保养单 （如果当前时间>保养计划首次时间，则立即或者 间隔天数 生成保养单）
        //废弃todo  否则根据“下次时间”生成保养单

        List<MaintainPlan> maintainPlans = planMapper.selectMaintainPlanList(new MaintainPlan());
        List<MaintainPlan> openPlans =  maintainPlans.stream().filter(m -> m.getUseState().equals("0")).collect(Collectors.toList());

        //List<MiddleDevicePlan> middleDevicePlans = devicePlanMapper.selectMiddleDevicePlanList(new MiddleDevicePlan());

        Date time = DateUtils.parseDate(DateUtils.getDate());

        for (MaintainPlan plan:
                openPlans) {
            //根据设备类型查询设备
            DeviceArchives deviceArchives =  new DeviceArchives();
            deviceArchives.setDeviceType(plan.getDeviceType());
            List<DeviceArchives> das = archivesMapper.selectDeviceArchivesList(deviceArchives);

//            for (DeviceArchives da:
//                    das ) {
//                MiddleDevicePlan middleDevicePlan =  new MiddleDevicePlan();
//                middleDevicePlan.setDeviceId(da.getDeviceId());
//                middleDevicePlan.setPlanId(plan.getPlanId());
//                //如果中间表没有该记录， 添加记录
//                if(this.checkMiddleUnique(middleDevicePlan).equals("0")){
//                    devicePlanMapper.insertMiddleDevicePlan(middleDevicePlan);
//                }
//            }
            //todo  如果最后一次保养时间为空， 则按照首次保养计划时间 生成保养单，生成后记录最后一次保养时间
            //todo  如果最后一次保养时间不为空，则判断 当前时间 与 最后一次保养时间+间隔时间 （当前时间小于，则到达间隔时间生成保养单，大于则立即生成）

            if(StringUtils.isNull(plan.getLastMaintainTime())){
                Date firstMaintainTime = plan.getFirstMaintainTime();
                //Date1 时间与 Date2 相等
                if(firstMaintainTime.compareTo(time) == 0){
                    for (DeviceArchives da:
                            das ) {

                        //添加保养任务
                        DeviceMaintainSheet sheet = new DeviceMaintainSheet();
                        sheet.setDeviceId(da.getDeviceId());  //设备id
                        sheet.setMaintainerId(plan.getMaintainerId()); //保养人id
                        sheet.setStatus("0"); //未完成
                        sheet.setCreateTime(time);
                        sheetMapper.insertDeviceMaintainSheet(sheet);


                        //添加保养任务详情
                        List<DeviceMaintainDetail> sheetDetailList = new ArrayList<>();
                        Long maintainSheetId = sheet.getMaintainSheetId();
                        List<MaintainPlanDetail> planDetailList = plan.getMaintainPlanDetailList();
                        for (MaintainPlanDetail planDetail:
                                planDetailList) {
                            DeviceMaintainDetail d =  new DeviceMaintainDetail();
                            d.setContent(planDetail.getContent());
                            d.setStandard(planDetail.getStandard());
                            d.setMaintainSheetId(maintainSheetId);
                            sheetDetailList.add(d);
                        }
                        sheetMapper.batchDeviceMaintainDetail(sheetDetailList);

                    }
                    plan.setLastMaintainTime(time);
                    planMapper.updateMaintainPlan(plan);
                }
            }else{
                // 得到几天后(间隔)的时间
                Calendar now = Calendar.getInstance();
                now.setTime(plan.getLastMaintainTime());
                now.set(Calendar.DATE, now.get(Calendar.DATE) + plan.getIntervalDays().intValue());
                if(now.getTime().compareTo(time) == 0 || now.getTime().compareTo(time) < 0 ){
                    for (DeviceArchives da:
                            das ) {
                        DeviceMaintainSheet sheet = new DeviceMaintainSheet();
                        sheet.setDeviceId(da.getDeviceId());  //设备id
                        sheet.setMaintainerId(plan.getMaintainerId()); //保养人id
                        sheet.setStatus("0"); //未完成
                        sheet.setCreateTime(time);
                        sheetMapper.insertDeviceMaintainSheet(sheet);
                    }
                    plan.setLastMaintainTime(time);
                    planMapper.updateMaintainPlan(plan);
                }
            }


        }



    }


    /**
     * 校验 设备和计划  在中间表 是否唯一
     *
     * @param middleDevicePlan
     * @return 结果
     */
    public String checkMiddleUnique(MiddleDevicePlan middleDevicePlan)
    {
        int count = devicePlanMapper.checkMiddleUnique(middleDevicePlan);
        if (count > 0)
        {
            return "1";
        }
        return "0";
    }
}
