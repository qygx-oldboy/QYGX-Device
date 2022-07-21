package com.qygx.test;

import com.qygx.system.domain.ProInspect;
import com.qygx.system.domain.vo.InspectVo;
import com.qygx.system.service.IProReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.text.ParseException;
import java.util.List;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {

    @Autowired
    private IProReportService reportService;

    @Test()
    public void test() throws ParseException {

        List<InspectVo> inspectVos = this.reportService.selectShiftInspectList(new ProInspect());
        for (InspectVo iv : inspectVos ) {
            System.out.println(iv.toString());
        }

    }


}