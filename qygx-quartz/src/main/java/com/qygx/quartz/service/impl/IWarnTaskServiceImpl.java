package com.qygx.quartz.service.impl;

import com.alibaba.fastjson2.JSON;
import com.qygx.common.annotation.DataSource;
import com.qygx.common.enums.DataSourceType;
import com.qygx.common.utils.DateUtils;
import com.qygx.framework.websocket.WebSocketUsers;
import com.qygx.quartz.service.IWarnTaskService;
import com.qygx.system.domain.ProInspect;
import com.qygx.system.mapper.ProReportMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class IWarnTaskServiceImpl implements IWarnTaskService {

    @Autowired
    private ProReportMapper proReportMapper;

    static private Date time = new Date();

    @Value("${qygx.lianglv}")
    private double  LIANGLV;  //良率设定值






    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public void warnTask() {
        System.out.println("定时任务开启了");
        //todo 查询上一次 时间点 到当前时间点的数据
        ProInspect proInspect = new ProInspect();
        proInspect.setBeginTime(time);
        proInspect.setEndTime(DateUtils.getNowDate());
        //todo  如果良率低于 多少就推送
        List<ProInspect> pis = proReportMapper.selectInspectListByTime(proInspect);
        if(pis.size() > 0){
            for (ProInspect pi : pis) {
                double liangLv =  (float)pi.getOkNum()/pi.getInspectedNum();
                pi.setOkPercent(liangLv);
                if(liangLv < LIANGLV){
                    WebSocketUsers.sendMessageToUsersByText(JSON.toJSONString(pi));
                }
            }
        }
        time = DateUtils.getNowDate();
    }
}
