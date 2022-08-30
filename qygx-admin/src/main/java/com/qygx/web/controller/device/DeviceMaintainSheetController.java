package com.qygx.web.controller.device;

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
import com.qygx.system.domain.DeviceMaintainSheet;
import com.qygx.system.service.IDeviceMaintainSheetService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 保养记录Controller
 * 
 * @author qygx
 * @date 2022-08-29
 */
@RestController
@RequestMapping("/device/maintainSheet")
public class DeviceMaintainSheetController extends BaseController
{
    @Autowired
    private IDeviceMaintainSheetService deviceMaintainSheetService;

    /**
     * 查询保养记录列表
     */
    @PreAuthorize("@ss.hasPermi('device:maintainSheet:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceMaintainSheet deviceMaintainSheet)
    {
        startPage();
        List<DeviceMaintainSheet> list = deviceMaintainSheetService.selectDeviceMaintainSheetList(deviceMaintainSheet);
        return getDataTable(list);
    }

    /**
     * 导出保养记录列表
     */
    @PreAuthorize("@ss.hasPermi('device:maintainSheet:export')")
    @Log(title = "保养记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceMaintainSheet deviceMaintainSheet)
    {
        List<DeviceMaintainSheet> list = deviceMaintainSheetService.selectDeviceMaintainSheetList(deviceMaintainSheet);
        ExcelUtil<DeviceMaintainSheet> util = new ExcelUtil<DeviceMaintainSheet>(DeviceMaintainSheet.class);
        util.exportExcel(response, list, "保养记录数据");
    }

    /**
     * 获取保养记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:maintainSheet:query')")
    @GetMapping(value = "/{maintainSheetId}")
    public AjaxResult getInfo(@PathVariable("maintainSheetId") Long maintainSheetId)
    {
        return AjaxResult.success(deviceMaintainSheetService.selectDeviceMaintainSheetByMaintainSheetId(maintainSheetId));
    }

    /**
     * 新增保养记录
     */
    @PreAuthorize("@ss.hasPermi('device:maintainSheet:add')")
    @Log(title = "保养记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceMaintainSheet deviceMaintainSheet)
    {
        return toAjax(deviceMaintainSheetService.insertDeviceMaintainSheet(deviceMaintainSheet));
    }

    /**
     * 修改保养记录
     */
    @PreAuthorize("@ss.hasPermi('device:maintainSheet:edit')")
    @Log(title = "保养记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceMaintainSheet deviceMaintainSheet)
    {
        return toAjax(deviceMaintainSheetService.updateDeviceMaintainSheet(deviceMaintainSheet));
    }

    /**
     * 删除保养记录
     */
    @PreAuthorize("@ss.hasPermi('device:maintainSheet:remove')")
    @Log(title = "保养记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{maintainSheetIds}")
    public AjaxResult remove(@PathVariable Long[] maintainSheetIds)
    {
        return toAjax(deviceMaintainSheetService.deleteDeviceMaintainSheetByMaintainSheetIds(maintainSheetIds));
    }
}
