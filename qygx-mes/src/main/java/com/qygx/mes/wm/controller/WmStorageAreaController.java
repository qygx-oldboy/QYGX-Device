package com.qygx.mes.wm.controller;

import com.qygx.common.annotation.Log;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.mes.wm.domain.WmStorageArea;
import com.qygx.mes.wm.service.IWmStorageAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 库位设置Controller
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
@RestController
@RequestMapping("/mes/wm/area")
public class WmStorageAreaController extends BaseController
{
    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    /**
     * 查询库位设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmStorageArea wmStorageArea)
    {
        startPage();
        List<WmStorageArea> list = wmStorageAreaService.selectWmStorageAreaList(wmStorageArea);
        return getDataTable(list);
    }

    /**
     * 导出库位设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:export')")
    @Log(title = "库位设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmStorageArea wmStorageArea)
    {
        List<WmStorageArea> list = wmStorageAreaService.selectWmStorageAreaList(wmStorageArea);
        ExcelUtil<WmStorageArea> util = new ExcelUtil<WmStorageArea>(WmStorageArea.class);
        util.exportExcel(response, list, "库位设置数据");
    }

    /**
     * 获取库位设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:query')")
    @GetMapping(value = "/{areaId}")
    public AjaxResult getInfo(@PathVariable("areaId") Long areaId)
    {
        return AjaxResult.success(wmStorageAreaService.selectWmStorageAreaByAreaId(areaId));
    }

    /**
     * 新增库位设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:add')")
    @Log(title = "库位设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmStorageArea wmStorageArea)
    {
        return toAjax(wmStorageAreaService.insertWmStorageArea(wmStorageArea));
    }

    /**
     * 修改库位设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:edit')")
    @Log(title = "库位设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmStorageArea wmStorageArea)
    {
        return toAjax(wmStorageAreaService.updateWmStorageArea(wmStorageArea));
    }

    /**
     * 删除库位设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:remove')")
    @Log(title = "库位设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{areaIds}")
    public AjaxResult remove(@PathVariable Long[] areaIds)
    {
        return toAjax(wmStorageAreaService.deleteWmStorageAreaByAreaIds(areaIds));
    }
}
