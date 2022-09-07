package com.qygx.mes.dv.controller;

import com.qygx.common.annotation.Log;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.mes.dv.domain.DvMachinery;
import com.qygx.mes.dv.service.IDvMachineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 设备Controller
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
@RestController
@RequestMapping("/mes/dv/machinery")
public class DvMachineryController extends BaseController
{
    @Autowired
    private IDvMachineryService dvMachineryService;

    /**
     * 查询设备列表
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:list')")
    @GetMapping("/list")
    public TableDataInfo list(DvMachinery dvMachinery)
    {
        startPage();
        List<DvMachinery> list = dvMachineryService.selectDvMachineryList(dvMachinery);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DvMachinery dvMachinery)
    {
        List<DvMachinery> list = dvMachineryService.selectDvMachineryList(dvMachinery);
        ExcelUtil<DvMachinery> util = new ExcelUtil<DvMachinery>(DvMachinery.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:query')")
    @GetMapping(value = "/{machineryId}")
    public AjaxResult getInfo(@PathVariable("machineryId") Long machineryId)
    {
        return AjaxResult.success(dvMachineryService.selectDvMachineryByMachineryId(machineryId));
    }

    /**
     * 新增设备
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DvMachinery dvMachinery)
    {
        return toAjax(dvMachineryService.insertDvMachinery(dvMachinery));
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DvMachinery dvMachinery)
    {
        return toAjax(dvMachineryService.updateDvMachinery(dvMachinery));
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{machineryIds}")
    public AjaxResult remove(@PathVariable Long[] machineryIds)
    {
        return toAjax(dvMachineryService.deleteDvMachineryByMachineryIds(machineryIds));
    }
}
