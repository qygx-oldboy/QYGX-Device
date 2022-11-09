package com.qygx.test;

import com.qygx.framework.emq.EmqClient;
import com.qygx.mes.alarm.domain.TbAlarm;
import com.qygx.mes.alarm.mapper.TbAlarmMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestEmq {

    @Autowired
    private EmqClient emqClient;

    @Autowired
    private TbAlarmMapper tbAlarmMapper;

    @Test()
    public void test() {
        TbAlarm tbAlarm = new TbAlarm();
        tbAlarm.setQuotaId((long)1);
        List<TbAlarm> alarmList = tbAlarmMapper.selectTbAlarmList(tbAlarm);
        alarmList.sort((TbAlarm a, TbAlarm b) -> a.getLevel().compareTo(b.getLevel()));
        System.out.println(alarmList);
    }






}
