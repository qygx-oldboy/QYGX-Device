package com.qygx.mes.md.controller;

import com.qygx.common.annotation.Log;
import com.qygx.common.constant.UserConstants;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.mes.md.domain.MdVendor;
import com.qygx.mes.md.service.IMdVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 供应商Controller
 * 
 * @author yinjinlu
 * @date 2022-05-06
 */
@RestController
@RequestMapping("/mes/md/vendor")
public class MdVendorController extends BaseController
{
    @Autowired
    private IMdVendorService mdVendorService;

    /**
     * 查询供应商列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:vendor:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdVendor mdVendor)
    {
        startPage();
        List<MdVendor> list = mdVendorService.selectMdVendorList(mdVendor);
        return getDataTable(list);
    }

    /**
     * 导出供应商列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:vendor:export')")
    @Log(title = "供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdVendor mdVendor)
    {
        List<MdVendor> list = mdVendorService.selectMdVendorList(mdVendor);
        ExcelUtil<MdVendor> util = new ExcelUtil<MdVendor>(MdVendor.class);
        util.exportExcel(response, list, "供应商数据");
    }

    /**
     * 获取供应商详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:vendor:query')")
    @GetMapping(value = "/{vendorId}")
    public AjaxResult getInfo(@PathVariable("vendorId") Long vendorId)
    {
        return AjaxResult.success(mdVendorService.selectMdVendorByVendorId(vendorId));
    }

    /**
     * 新增供应商
     */
    @PreAuthorize("@ss.hasPermi('mes:md:vendor:add')")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdVendor mdVendor)
    {
        if(UserConstants.NOT_UNIQUE.equals(mdVendorService.checkVendorCodeUnique(mdVendor))){
            return AjaxResult.error("供应商编码已存在！");
        }
        if(UserConstants.NOT_UNIQUE.equals(mdVendorService.checkVendorNameUnique(mdVendor))){
            return AjaxResult.error("供应商名称已存在！");
        }
        if(UserConstants.NOT_UNIQUE.equals(mdVendorService.checkVendorNickUnique(mdVendor))){
            return AjaxResult.error("供应商简称已存在！");
        }

        return toAjax(mdVendorService.insertMdVendor(mdVendor));
    }

    /**
     * 修改供应商
     */
    @PreAuthorize("@ss.hasPermi('mes:md:vendor:edit')")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdVendor mdVendor)
    {
        if(UserConstants.NOT_UNIQUE.equals(mdVendorService.checkVendorCodeUnique(mdVendor))){
            return AjaxResult.error("供应商编码已存在！");
        }
        if(UserConstants.NOT_UNIQUE.equals(mdVendorService.checkVendorNameUnique(mdVendor))){
            return AjaxResult.error("供应商名称已存在！");
        }
        if(UserConstants.NOT_UNIQUE.equals(mdVendorService.checkVendorNickUnique(mdVendor))){
            return AjaxResult.error("供应商简称已存在！");
        }
        return toAjax(mdVendorService.updateMdVendor(mdVendor));
    }

    /**
     * 删除供应商
     */
    @PreAuthorize("@ss.hasPermi('mes:md:vendor:remove')")
    @Log(title = "供应商", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vendorIds}")
    public AjaxResult remove(@PathVariable Long[] vendorIds)
    {
        return toAjax(mdVendorService.deleteMdVendorByVendorIds(vendorIds));
    }
}
