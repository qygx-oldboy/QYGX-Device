package com.qygx.mes.wm.controller;

import com.qygx.common.annotation.Log;
import com.qygx.common.constant.UserConstants;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.mes.wm.domain.WmWarehouse;
import com.qygx.mes.wm.service.IWmStorageAreaService;
import com.qygx.mes.wm.service.IWmStorageLocationService;
import com.qygx.mes.wm.service.IWmWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 仓库设置Controller
 * 
 * @author yinjinlu
 * @date 2022-05-07
 */
@RestController
@RequestMapping("/mes/wm/warehouse")
public class WmWarehouseController extends BaseController
{
    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    /**
     * 查询仓库设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:warehouse:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmWarehouse wmWarehouse)
    {
        startPage();
        List<WmWarehouse> list = wmWarehouseService.selectWmWarehouseList(wmWarehouse);
        return getDataTable(list);
    }

    /**
     * 查询树型的列表
     * @return
     */
    @GetMapping("/getTreeList")
    public AjaxResult getTreeList(){
        return AjaxResult.success(wmWarehouseService.getTreeList());
    }

    /**
     * 导出仓库设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:warehouse:export')")
    @Log(title = "仓库设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmWarehouse wmWarehouse)
    {
        List<WmWarehouse> list = wmWarehouseService.selectWmWarehouseList(wmWarehouse);
        ExcelUtil<WmWarehouse> util = new ExcelUtil<WmWarehouse>(WmWarehouse.class);
        util.exportExcel(response, list, "仓库设置数据");
    }

    /**
     * 获取仓库设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:warehouse:query')")
    @GetMapping(value = "/{warehouseId}")
    public AjaxResult getInfo(@PathVariable("warehouseId") Long warehouseId)
    {
        return AjaxResult.success(wmWarehouseService.selectWmWarehouseByWarehouseId(warehouseId));
    }

    /**
     * 新增仓库设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:warehouse:add')")
    @Log(title = "仓库设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmWarehouse wmWarehouse)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmWarehouseService.checkWarehouseCodeUnique(wmWarehouse))){
            return AjaxResult.error("仓库编码已存在！");
        }
        if(UserConstants.NOT_UNIQUE.equals(wmWarehouseService.checkWarehouseNameUnique(wmWarehouse))){
            return AjaxResult.error("仓库名称已存在！");
        }

        return toAjax(wmWarehouseService.insertWmWarehouse(wmWarehouse));
    }

    /**
     * 修改仓库设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:warehouse:edit')")
    @Log(title = "仓库设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmWarehouse wmWarehouse)
    {
        return toAjax(wmWarehouseService.updateWmWarehouse(wmWarehouse));
    }

    /**
     * 删除仓库设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:warehouse:remove')")
    @Log(title = "仓库设置", businessType = BusinessType.DELETE)
    @Transactional
	@DeleteMapping("/{warehouseIds}")
    public AjaxResult remove(@PathVariable Long[] warehouseIds)
    {
        for (Long wahouseId: warehouseIds
             ) {
            wmStorageLocationService.deleteByWarehouseId(wahouseId);
            wmStorageAreaService.deleteByWarehouseId(wahouseId);
        }

        return toAjax(wmWarehouseService.deleteWmWarehouseByWarehouseIds(warehouseIds));
    }
}
