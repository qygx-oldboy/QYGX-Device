package com.qygx.web.controller.product;

import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.utils.DateUtils;
import com.qygx.system.domain.ProInspect;
import com.qygx.system.domain.vo.InspectVo;
import com.qygx.system.service.IProReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 数据报表 信息操作处理
 *
 * @author yxc
 */
@Api("数据报表")
@RestController
@RequestMapping("/product/report")
public class ProReportController extends BaseController {

    @Autowired
    private IProReportService reportService;

    /**
     * 获取检验数和良率折线图
     */
    @ApiOperation("获取检验数和良率折线图")
    @PreAuthorize("@ss.hasPermi('product:report:getChart')")
    @GetMapping("/getChart")
    public AjaxResult getChart(ProInspect proInspect) throws ParseException {

        HashMap<String, Object> map = new HashMap<>();

        //todo 返回一个map
        ArrayList<String> cols = new ArrayList<>();
        cols.add("date");
        cols.add("checkNum");
        //cols.add("okNum");
        cols.add("yieldRate");
        map.put("columns",cols);
        //todo [{日期:xxxx,检验数:xxx，良率:xxxx}]
        List<InspectVo> listInspect = reportService.selectInspectList(proInspect);
        map.put("rows",listInspect);
        return AjaxResult.success(map);
    }


    /**
     * 获取品种列表
     */
    @PreAuthorize("@ss.hasPermi('product:report:getChart')")
    @GetMapping("/getBreedList")
    public AjaxResult getBreedList( ) {
        return AjaxResult.success(reportService.selectBreedList());
    }


    /**
     * 获取实时质量监控
     */
    @PreAuthorize("@ss.hasPermi('product:report:getChart')")
    @GetMapping("/getShift")
    public AjaxResult getShift(ProInspect proInspect) throws ParseException {
        HashMap<String, Object> map = new HashMap<>();
        //todo 返回一个map
        ArrayList<String> cols = new ArrayList<>();
        cols.add("date");
        //cols.add("checkNum");
        //cols.add("okNum");
        cols.add("yieldRate");
        map.put("columns",cols);
        List<InspectVo> listInspect = reportService.selectShiftInspectList(proInspect);
        map.put("rows",listInspect);
        return AjaxResult.success(map);
    }

    /**
     * 获取实时质量监控
     */
    @PreAuthorize("@ss.hasPermi('product:report:getChart')")
    @GetMapping("/info")
    public AjaxResult info() throws ParseException {
        return AjaxResult.success(reportService.info());
    }


}




