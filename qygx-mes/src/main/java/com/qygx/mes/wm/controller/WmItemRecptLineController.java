package com.qygx.mes.wm.controller;

import com.qygx.common.annotation.Log;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.StringUtils;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.mes.wm.domain.WmItemRecptLine;
import com.qygx.mes.wm.domain.WmStorageArea;
import com.qygx.mes.wm.domain.WmStorageLocation;
import com.qygx.mes.wm.domain.WmWarehouse;
import com.qygx.mes.wm.service.IWmItemRecptLineService;
import com.qygx.mes.wm.service.IWmStorageAreaService;
import com.qygx.mes.wm.service.IWmStorageLocationService;
import com.qygx.mes.wm.service.IWmWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 物料入库单行Controller
 * 
 * @author yinjinlu
 * @date 2022-05-22
 */
@RestController
@RequestMapping("/mes/wm/itemrecptline")
public class WmItemRecptLineController extends BaseController
{
    @Autowired
    private IWmItemRecptLineService wmItemRecptLineService;


    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    /**
     * 查询物料入库单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmItemRecptLine wmItemRecptLine)
    {
        startPage();
        List<WmItemRecptLine> list = wmItemRecptLineService.selectWmItemRecptLineList(wmItemRecptLine);
        return getDataTable(list);
    }

    /**
     * 导出物料入库单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:export')")
    @Log(title = "物料入库单行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmItemRecptLine wmItemRecptLine)
    {
        List<WmItemRecptLine> list = wmItemRecptLineService.selectWmItemRecptLineList(wmItemRecptLine);
        ExcelUtil<WmItemRecptLine> util = new ExcelUtil<WmItemRecptLine>(WmItemRecptLine.class);
        util.exportExcel(response, list, "物料入库单行数据");
    }

    /**
     * 获取物料入库单行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmItemRecptLineService.selectWmItemRecptLineByLineId(lineId));
    }

    /**
     * 新增物料入库单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:add')")
    @Log(title = "物料入库单行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmItemRecptLine wmItemRecptLine)
    {
        if(StringUtils.isNotNull(wmItemRecptLine.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmItemRecptLine.getWarehouseId());
            wmItemRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmItemRecptLine.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmItemRecptLine.getLocationId());
            wmItemRecptLine.setLocationCode(location.getLocationCode());
            wmItemRecptLine.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmItemRecptLine.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmItemRecptLine.getAreaId());
            wmItemRecptLine.setAreaCode(area.getAreaCode());
            wmItemRecptLine.setAreaName(area.getAreaName());
        }
        return toAjax(wmItemRecptLineService.insertWmItemRecptLine(wmItemRecptLine));
    }

    /**
     * 修改物料入库单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:edit')")
    @Log(title = "物料入库单行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmItemRecptLine wmItemRecptLine)
    {
        if(StringUtils.isNotNull(wmItemRecptLine.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmItemRecptLine.getWarehouseId());
            wmItemRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmItemRecptLine.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmItemRecptLine.getLocationId());
            wmItemRecptLine.setLocationCode(location.getLocationCode());
            wmItemRecptLine.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmItemRecptLine.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmItemRecptLine.getAreaId());
            wmItemRecptLine.setAreaCode(area.getAreaCode());
            wmItemRecptLine.setAreaName(area.getAreaName());
        }
        return toAjax(wmItemRecptLineService.updateWmItemRecptLine(wmItemRecptLine));
    }

    /**
     * 删除物料入库单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:remove')")
    @Log(title = "物料入库单行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmItemRecptLineService.deleteWmItemRecptLineByLineIds(lineIds));
    }
}
