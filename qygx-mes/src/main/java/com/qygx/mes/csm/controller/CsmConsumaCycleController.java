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
import com.qygx.mes.csm.domain.CsmConsumaCycle;
import com.qygx.mes.csm.service.ICsmConsumaCycleService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 耗材周期Controller
 *
 * @author qygx
 * @date 2022-09-07
 */
@RestController
@RequestMapping("/mes/csm/consumacycle")
public class CsmConsumaCycleController extends BaseController
{
    @Autowired
    private ICsmConsumaCycleService csmConsumaCycleService;

    /**
     * 查询耗材周期列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumacycle:list')")
    @GetMapping("/list")
    public TableDataInfo list(CsmConsumaCycle csmConsumaCycle)
    {
        startPage();
        List<CsmConsumaCycle> list = csmConsumaCycleService.selectCsmConsumaCycleList(csmConsumaCycle);
        return getDataTable(list);
    }

    /**
     * 导出耗材周期列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumacycle:export')")
    @Log(title = "耗材周期", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CsmConsumaCycle csmConsumaCycle)
    {
        List<CsmConsumaCycle> list = csmConsumaCycleService.selectCsmConsumaCycleList(csmConsumaCycle);
        ExcelUtil<CsmConsumaCycle> util = new ExcelUtil<CsmConsumaCycle>(CsmConsumaCycle.class);
        util.exportExcel(response, list, "耗材周期数据");
    }

    /**
     * 获取耗材周期详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumacycle:query')")
    @GetMapping(value = "/{consumaId}")
    public AjaxResult getInfo(@PathVariable("consumaId") Long consumaId)
    {
        return AjaxResult.success(csmConsumaCycleService.selectCsmConsumaCycleByConsumaId(consumaId));
    }

    /**
     * 新增耗材周期
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumacycle:add')")
    @Log(title = "耗材周期", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CsmConsumaCycle csmConsumaCycle)
    {
        return toAjax(csmConsumaCycleService.insertCsmConsumaCycle(csmConsumaCycle));
    }

    /**
     * 修改耗材周期
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumacycle:edit')")
    @Log(title = "耗材周期", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CsmConsumaCycle csmConsumaCycle)
    {
        return toAjax(csmConsumaCycleService.updateCsmConsumaCycle(csmConsumaCycle));
    }

    /**
     * 删除耗材周期
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumacycle:remove')")
    @Log(title = "耗材周期", businessType = BusinessType.DELETE)
    @DeleteMapping("/{consumaIds}")
    public AjaxResult remove(@PathVariable Long[] consumaIds)
    {
        return toAjax(csmConsumaCycleService.deleteCsmConsumaCycleByConsumaIds(consumaIds));
    }
}
