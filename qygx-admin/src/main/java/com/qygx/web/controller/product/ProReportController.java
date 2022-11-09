package com.qygx.web.controller.product;

import com.qygx.common.annotation.Log;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.PageDomain;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.core.page.TableSupport;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.system.domain.ProInspect;
import com.qygx.system.domain.dto.InspectDto;
import com.qygx.system.domain.vo.InspectVo;
import com.qygx.system.service.IProReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    /**
     * 查询inspect列表
     */
    @PreAuthorize("@ss.hasPermi('product:inspect:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProInspect proInspect)
    {
        startPage();
        List<ProInspect> list = reportService.selectProInspectList(proInspect);
        return getDataTable(list);
    }


    /**
     * 查询inspect列表
     */
    @PreAuthorize("@ss.hasPermi('product:inspect:list')")
    @GetMapping("/unusualList")
    public TableDataInfo unusualList(ProInspect proInspect)
    {
        startPage();
        List<ProInspect> unusualList = reportService.selectProInspectUnusualList(proInspect);
        return getDataTable(unusualList);
    }



    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<ProInspect> util = new ExcelUtil<ProInspect>(ProInspect.class);
        util.importTemplateExcel(response, "检验数据");
    }

    /**
     * 导出inspect列表
     */
    @PreAuthorize("@ss.hasPermi('product:inspect:export')")
    @Log(title = "inspect", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProInspect proInspect)
    {
        List<ProInspect> list = reportService.selectProInspectList(proInspect);
        ExcelUtil<ProInspect> util = new ExcelUtil<ProInspect>(ProInspect.class);
        util.exportExcel(response, list, "inspect数据");
    }

    /**
     * 获取inspect详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:inspect:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(reportService.selectProInspectById(id));
    }

    /**
     * 新增inspect
     */
    @PreAuthorize("@ss.hasPermi('product:inspect:add')")
    @Log(title = "inspect", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProInspect proInspect)
    {
        return toAjax(reportService.insertProInspect(proInspect));
    }

    /**
     * 修改inspect
     */
    @PreAuthorize("@ss.hasPermi('product:inspect:edit')")
    @Log(title = "inspect", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit( @RequestBody ProInspect proInspect)
    {
        return toAjax(reportService.updateProInspect(proInspect));
    }

    /**
     * 删除inspect
     */
    @PreAuthorize("@ss.hasPermi('product:inspect:remove')")
    @Log(title = "inspect", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reportService.deleteProInspectByIds(ids));
    }


    @Log(title = "检验列表", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('product:inspect:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ProInspect> util = new ExcelUtil<>(ProInspect.class);
        List<ProInspect> inspectList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = reportService.importInspect(inspectList, updateSupport, operName);
        return AjaxResult.success(message);
    }



    /**
     * 查询每日质量汇总列表
     */
    @PreAuthorize("@ss.hasPermi('product:inspect:list')")
    @GetMapping("/dailyList")
    public TableDataInfo dailyList(InspectDto inspectDto)
    {
        startPage();
        List<InspectDto> inspectDtoList = reportService.selectDailyInspectList(inspectDto);
        return getDataTable(inspectDtoList);
    }


    /**
     * 查询subType下的的良率详情列表
     *
     *
     */
    @PreAuthorize("@ss.hasPermi('product:inspect:list')")
    @GetMapping(value = "/getDailyDetail")
    public AjaxResult getDailyDetail(InspectDto inspectDto)
    {
        List<InspectDto> inspectDtoList = reportService.selectDailyDetail(inspectDto);
        return AjaxResult.success(inspectDtoList);
    }

    /**
     * 查询某一天的 全检批退和抽检批退
     */
    @PreAuthorize("@ss.hasPermi('product:inspect:list')")
    @GetMapping(value = "/getDailyFullOrSpot/{name}")
    public AjaxResult getDailyFullOrSpot(@PathVariable("name") String name)
    {
        List<InspectDto> inspectDtoList = reportService.selectDailyFullOrSpot(name);
        return AjaxResult.success(inspectDtoList);
    }

    /**
     * 查询某一天的 全检批退或抽检批退 的子集
     */
    @PreAuthorize("@ss.hasPermi('product:inspect:list')")
    @GetMapping(value = "/getDailyByType")
    public AjaxResult getDailyByType(InspectDto inspectDto)
    {
        List<InspectDto> inspectDtoList = reportService.selectDailyByType(inspectDto);
        return AjaxResult.success(inspectDtoList);
    }


}




