package com.qygx.test;


import cn.hutool.json.JSONUtil;
import com.qygx.mes.alarm.service.ITbAlarmService;
import com.qygx.mes.quato.domain.dto.DeviceInfoDTO;
import com.qygx.mes.quato.service.ITbQuatoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestQuato {

    @Autowired
    private ITbQuatoService quatoService;

    @Autowired
    private ITbAlarmService alarmService;

    @Test()
    public void test() {

        Map map = new HashMap<>();
        map.put("sn","123456");
        map.put("temp",31);

        DeviceInfoDTO deviceInfoDTO = quatoService.analysis("test", map);

        DeviceInfoDTO data = alarmService.verifyDeviceInfo(deviceInfoDTO);

        String json = JSONUtil.toJsonStr(data);
        System.out.println(json);


    }






}
