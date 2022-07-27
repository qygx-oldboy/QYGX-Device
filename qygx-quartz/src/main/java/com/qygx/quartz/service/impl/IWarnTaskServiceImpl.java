package com.qygx.quartz.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.qygx.framework.websocket.WebSocketUsers;
import com.qygx.quartz.service.IWarnTaskService;
import com.qygx.system.domain.ProInspect;
import com.qygx.system.service.IProReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IWarnTaskServiceImpl implements IWarnTaskService {

    @Autowired
    private IProReportService iProReportService;

    private List<ProInspect> list = new ArrayList<>();

    private List<ProInspect> newList;

//    private boolean stopMe = true;
//    public void stopMe() {
//        stopMe = false;
//    }





    @Override
    public void warnTask() {
        System.out.println("定时任务开启了");
        //todo 查询  与 上一次进行对比，如果有变化，则推送
        ProInspect proInspect = new ProInspect();
        proInspect.setOkNum(0);
        if(list.size() == 0){
            list = iProReportService.selectProInspectList(proInspect);
        }
        newList = iProReportService.selectProInspectList(proInspect);
        for (ProInspect pi : newList) {
            if(!list.contains(pi)){
                list.add(pi);
                WebSocketUsers.sendMessageToUsersByText(JSON.toJSONString(pi));
            }
        }
    }
}
