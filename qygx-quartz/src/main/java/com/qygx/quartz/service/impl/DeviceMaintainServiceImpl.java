package com.qygx.quartz.service.impl;
import com.qygx.common.utils.DateUtils;
import com.qygx.common.utils.StringUtils;
import com.qygx.mes.dv.domain.DvCheckMachinery;
import com.qygx.mes.dv.mapper.DvCheckMachineryMapper;
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
    private DvCheckMachineryMapper dvCheckMapper;

    @Autowired
    private DeviceMaintainSheetMapper sheetMapper;








    /**
     * 生成保养单
     *
     */
    @Override
    public void generateSheet() {

        //todo  查询所有开启的保养计划
        List<MaintainPlan> maintainPlans = planMapper.selectMaintainPlanList(new MaintainPlan());
        List<MaintainPlan> openPlans =  maintainPlans.stream().filter(m -> m.getUseState().equals("0")).collect(Collectors.toList());
        Date time = DateUtils.parseDate(DateUtils.getDate());  //当前日期
        Calendar calendar = Calendar.getInstance();


        for (MaintainPlan plan:
                openPlans) {
            //todo  如果最后一次保养时间为空， 则按照设备首次保养计划时间 提前一周 生成保养单，生成后记录最后一次保养时间
            //todo  如果最后一次保养时间不为空，则判断 当前时间 与 最后一次保养时间+间隔时间 （当前时间小于，则到达间隔时间生成保养单，大于则立即生成）

            //查询该计划下 所有的设备
            DvCheckMachinery dvCheck = new DvCheckMachinery();
            dvCheck.setPlanId(plan.getPlanId());
            List<DvCheckMachinery> checkList = dvCheckMapper.selectDvCheckMachineryList(dvCheck);
            for (DvCheckMachinery check:
                    checkList ) {
                if (StringUtils.isNull(check.getLastTime())) {
                    Date firstTime = check.getFirstTime();
                    calendar.setTime(firstTime);
                    calendar.add(Calendar.WEEK_OF_YEAR, -1);
                    Date time1 = calendar.getTime();
                    //Date1 时间与 Date2 相等
                    if (time1.compareTo(time) == 0) {
                        this.generateSheetByPlan(plan,check);
                    }
                }
                else{
                    // 得到几天后(间隔)的时间

                    calendar.setTime(check.getLastTime());
                    calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + plan.getIntervalDays().intValue() - 7);
                    if(calendar.getTime().compareTo(time) == 0 || calendar.getTime().compareTo(time) < 0 ){
                        generateSheetByPlan(plan,check);
                    }
                }
            }
        }
    }


    /**
     * 实际生成保养单
     * @param plan
     * @param check
     */
    public void generateSheetByPlan(MaintainPlan plan,DvCheckMachinery check)
    {
            //添加保养任务
            DeviceMaintainSheet sheet = new DeviceMaintainSheet();
            sheet.setDeviceId(check.getMachineryId());

            sheet.setMaintainerId(plan.getMaintainerId());
            sheet.setStatus("0");
            sheet.setCreateTime(DateUtils.getNowDate());
            sheet.setDeadline(check.getFirstTime()); //截止时间
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

            check.setLastTime(DateUtils.parseDate(DateUtils.getDate()));
            dvCheckMapper.updateDvCheckMachinery(check);
    }
}
