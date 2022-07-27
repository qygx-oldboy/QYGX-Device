package com.qygx.quartz.task;


import com.qygx.quartz.service.IWarnTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度-预警管理测试
 *
 */
@Component("warntask")
public class WarnTask {

    @Autowired
    private IWarnTaskService warnTaskService;

    /**
     * 定时任务预警管理
     */
    public void warnTask() {
        warnTaskService.warnTask();
    }
}