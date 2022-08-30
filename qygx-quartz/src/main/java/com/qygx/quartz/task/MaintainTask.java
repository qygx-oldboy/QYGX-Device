package com.qygx.quartz.task;


import com.qygx.quartz.service.DeviceMaintainService;
import com.qygx.quartz.service.IWarnTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度-保养任务测试
 *
 */
@Component("maintainTask")
public class MaintainTask {

    @Autowired
    private DeviceMaintainService deviceMaintainService;

    /**
     * 生成保养单
     */
    public void sheet() {
        deviceMaintainService.generateSheet();
    }
}