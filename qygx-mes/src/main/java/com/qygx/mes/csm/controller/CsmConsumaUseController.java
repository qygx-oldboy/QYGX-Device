package com.qygx.mes.csm.controller;

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
import com.qygx.mes.csm.domain.CsmConsumaUse;
import com.qygx.mes.csm.service.ICsmConsumaUseService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 在用备件Controller
 *
 * @author qygx
 * @date 2022-09-07
 */
@RestController
@RequestMapping("/mes/csm/consumause")
public class CsmConsumaUseController extends BaseController
{
    @Autowired
    private ICsmConsumaUseService csmConsumaUseService;

    /**
     * 查询在用备件列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:list')")
    @GetMapping("/list")
    public TableDataInfo list(CsmConsumaUse csmConsumaUse)
    {
        startPage();
        List<CsmConsumaUse> list = csmConsumaUseService.selectCsmConsumaUseList(csmConsumaUse);
        return getDataTable(list);
    }

    /**
     * 导出在用备件列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:export')")
    @Log(title = "在用备件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CsmConsumaUse csmConsumaUse)
    {
        List<CsmConsumaUse> list = csmConsumaUseService.selectCsmConsumaUseList(csmConsumaUse);
        ExcelUtil<CsmConsumaUse> util = new ExcelUtil<CsmConsumaUse>(CsmConsumaUse.class);
        util.exportExcel(response, list, "在用备件数据");
    }

    /**
     * 获取在用备件详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:query')")
    @GetMapping(value = "/{consumaUseId}")
    public AjaxResult getInfo(@PathVariable("consumaUseId") Long consumaUseId)
    {
        return AjaxResult.success(csmConsumaUseService.selectCsmConsumaUseByConsumaUseId(consumaUseId));
    }

    /**
     * 新增在用备件
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:add')")
    @Log(title = "在用备件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CsmConsumaUse csmConsumaUse)
    {
        return toAjax(csmConsumaUseService.insertCsmConsumaUse(csmConsumaUse));
    }

    /**
     * 修改在用备件
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:edit')")
    @Log(title = "在用备件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CsmConsumaUse csmConsumaUse)
    {
        return toAjax(csmConsumaUseService.updateCsmConsumaUse(csmConsumaUse));
    }

    /**
     * 删除在用备件
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:remove')")
    @Log(title = "在用备件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{consumaUseIds}")
    public AjaxResult remove(@PathVariable Long[] consumaUseIds)
    {
        return toAjax(csmConsumaUseService.deleteCsmConsumaUseByConsumaUseIds(consumaUseIds));
    }
}
