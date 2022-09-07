package com.qygx.mes.dv.controller;

import cn.hutool.core.collection.CollUtil;
import com.qygx.common.annotation.Log;
import com.qygx.common.constant.UserConstants;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.mes.dv.domain.DvMachinery;
import com.qygx.mes.dv.domain.DvMachineryType;
import com.qygx.mes.dv.service.IDvMachineryService;
import com.qygx.mes.dv.service.IDvMachineryTypeService;
import com.qygx.system.strategy.AutoCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 设备类型Controller
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
@RestController
@RequestMapping("/mes/dv/machinerytype")
public class DvMachineryTypeController extends BaseController
{
    @Autowired
    private IDvMachineryTypeService dvMachineryTypeService;

    @Autowired
    private IDvMachineryService dvMachineryService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;
    /**
     * 查询设备类型列表
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinerytype:list')")
    @GetMapping("/list")
    public AjaxResult list(DvMachineryType dvMachineryType)
    {
        List<DvMachineryType> list = dvMachineryTypeService.selectDvMachineryTypeList(dvMachineryType);
        return AjaxResult.success(list);
    }

    /**
     * 导出设备类型列表
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinerytype:export')")
    @Log(title = "设备类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DvMachineryType dvMachineryType)
    {
        List<DvMachineryType> list = dvMachineryTypeService.selectDvMachineryTypeList(dvMachineryType);
        ExcelUtil<DvMachineryType> util = new ExcelUtil<DvMachineryType>(DvMachineryType.class);
        util.exportExcel(response, list, "设备类型数据");
    }

    /**
     * 获取设备类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinerytype:query')")
    @GetMapping(value = "/{machineryTypeId}")
    public AjaxResult getInfo(@PathVariable("machineryTypeId") Long machineryTypeId)
    {
        return AjaxResult.success(dvMachineryTypeService.selectDvMachineryTypeByMachineryTypeId(machineryTypeId));
    }

    /**
     * 新增设备类型
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinerytype:add')")
    @Log(title = "设备类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DvMachineryType dvMachineryType)
    {
        dvMachineryType.setMachineryTypeCode(autoCodeUtil.genSerialCode(UserConstants.MACHINERY_TYPE_CODE,null));
        return toAjax(dvMachineryTypeService.insertDvMachineryType(dvMachineryType));
    }

    /**
     * 修改设备类型
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinerytype:edit')")
    @Log(title = "设备类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DvMachineryType dvMachineryType)
    {
        return toAjax(dvMachineryTypeService.updateDvMachineryType(dvMachineryType));
    }

    /**
     * 删除设备类型
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinerytype:remove')")
    @Log(title = "设备类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{machineryTypeIds}")
    public AjaxResult remove(@PathVariable Long[] machineryTypeIds)
    {
        for (Long typeId:machineryTypeIds
             ) {
            DvMachinery param = new DvMachinery();
            param.setMachineryId(typeId);
            List<DvMachinery> machinerys = dvMachineryService.selectDvMachineryList(param);
            if(CollUtil.isNotEmpty(machinerys)){
                return AjaxResult.error("设备类型下已配置了设备，不能删除！");
            }
        }

        return toAjax(dvMachineryTypeService.deleteDvMachineryTypeByMachineryTypeIds(machineryTypeIds));
    }
}
