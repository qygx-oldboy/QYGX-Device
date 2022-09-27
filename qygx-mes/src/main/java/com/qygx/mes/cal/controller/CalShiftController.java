package com.qygx.mes.cal.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qygx.common.annotation.Log;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.enums.BusinessType;
import com.qygx.mes.cal.domain.CalShift;
import com.qygx.mes.cal.service.ICalShiftService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 班次设置Controller
 *
 * @author qygx
 * @date 2022-09-26
 */
@RestController
@RequestMapping("/mes/cal/shift")
public class CalShiftController extends BaseController
{
    @Autowired
    private ICalShiftService calShiftService;

    /**
     * 查询班次设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:list')")
    @GetMapping("/list")
    public TableDataInfo list(CalShift calShift)
    {
        startPage();
        List<CalShift> list = calShiftService.selectCalShiftList(calShift);
        return getDataTable(list);
    }

    /**
     * 导出班次设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:export')")
    @Log(title = "班次设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CalShift calShift)
    {
        List<CalShift> list = calShiftService.selectCalShiftList(calShift);
        ExcelUtil<CalShift> util = new ExcelUtil<CalShift>(CalShift.class);
        util.exportExcel(response, list, "班次设置数据");
    }

    /**
     * 获取班次设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:query')")
    @GetMapping(value = "/{shiftId}")
    public AjaxResult getInfo(@PathVariable("shiftId") Long shiftId)
    {
        return AjaxResult.success(calShiftService.selectCalShiftByShiftId(shiftId));
    }

    /**
     * 新增班次设置
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:add')")
    @Log(title = "班次设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CalShift calShift)
    {
        return toAjax(calShiftService.insertCalShift(calShift));
    }

    /**
     * 修改班次设置
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:edit')")
    @Log(title = "班次设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CalShift calShift)
    {
        return toAjax(calShiftService.updateCalShift(calShift));
    }

    /**
     * 删除班次设置
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:remove')")
    @Log(title = "班次设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{shiftIds}")
    public AjaxResult remove(@PathVariable Long[] shiftIds)
    {
        return toAjax(calShiftService.deleteCalShiftByShiftIds(shiftIds));
    }
}
