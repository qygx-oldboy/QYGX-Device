package com.qygx.system.service.impl;

import com.qygx.common.annotation.DataSource;
import com.qygx.common.enums.DataSourceType;
import com.qygx.common.utils.DateUtils;
import com.qygx.system.domain.ProInspect;
import com.qygx.system.domain.vo.BreedVo;
import com.qygx.system.domain.vo.InspectVo;
import com.qygx.system.domain.vo.QualityVo;
import com.qygx.system.mapper.ProReportMapper;
import com.qygx.system.service.IProReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProReportServiceImpl implements IProReportService {

    @Autowired
    private ProReportMapper reportMapper;


    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<InspectVo> selectInspectList(ProInspect proInspect) throws ParseException {
        List<ProInspect> listInspect = reportMapper.selectInspectList(proInspect);
        List<InspectVo> inspectVos = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        for (int i = 0; i < listInspect.size(); i++) {
            Date beginTime = listInspect.get(i).getBeginTime();
            String fmtDate = DateUtils.isClassesDate(beginTime);
            //实现查找，找到则返回匹配的对象，找不到则返回null
            InspectVo iv = inspectVos.stream().filter(item -> item.getDate().equals(fmtDate)) .findAny()
                    .orElse(null);
            if(iv != null){
                int checkNum = listInspect.get(i).getInspectedNum() + iv.getCheckNum();
                int okNum = listInspect.get(i).getOkNum() + iv.getOkNum();
                double yieldRate = (float)okNum / checkNum ;

                iv.setCheckNum(checkNum);
                iv.setOkNum(okNum);
                iv.setYieldRate(yieldRate);
            }
            else{
                //todo 新建对象
                int checkNum = listInspect.get(i).getInspectedNum();
                int okNum = listInspect.get(i).getOkNum();
                double yieldRate = (float)okNum / checkNum ;
                iv = new InspectVo(fmtDate,checkNum,okNum,yieldRate);
                inspectVos.add(iv);
            }
        }
        return inspectVos;
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List selectBreedList() {
        List<ProInspect> breeds = this.reportMapper.selectBreedList();
        List<BreedVo> result = new ArrayList<>();
        for (int i = 0; i < breeds.size(); i++) {
            String productType = breeds.get(i).getProductType();
            String materialQuality = breeds.get(i).getMaterialQuality();
            if(productType != null && productType.length() != 0
                    && materialQuality != null && materialQuality.length() != 0){
                String str = productType + "." + materialQuality;
                result.add(new BreedVo(str));
            }
        }
        return result;
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<InspectVo> selectShiftInspectList(ProInspect proInspect) throws ParseException {
        List<InspectVo> inspectVos = new ArrayList<>();
        Date nowDate = DateUtils.getNowDate();
        //todo 白班 2022-07-18 07:30:00  —— 2022-07-18 19:30:00
        //todo 夜班 2022-07-18 19:31:00  —— 2022-07-19 07:29:00
        Date[] dates = DateUtils.classesDateRange(nowDate);

        proInspect.setBeginTime(dates[0]);
        proInspect.setEndTime(dates[1]);
        List<ProInspect> proInspects = this.reportMapper.selectInspectListByTime(proInspect);

        for (ProInspect pi: proInspects ) {
            int checkNum = pi.getInspectedNum();
            int okNum = pi.getOkNum();
            double yieldRate = (float)okNum / checkNum ;
            InspectVo iv = new InspectVo(pi.getQrCode(),
                    checkNum,okNum,yieldRate);
            inspectVos.add(iv);
        }
        return inspectVos;
    }

    @Override
    public QualityVo info() throws ParseException {

        Date nowDate = DateUtils.getNowDate();
        String str = DateUtils.isClassesDate(nowDate);
        String date = DateUtils.parseDateToStr("YYYY-MM-dd",nowDate);
        String ban = str.substring(10);

        return new QualityVo(date,ban);
    }


}
