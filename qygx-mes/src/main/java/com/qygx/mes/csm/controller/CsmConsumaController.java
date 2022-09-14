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
import com.qygx.mes.csm.domain.CsmConsuma;
import com.qygx.mes.csm.service.ICsmConsumaService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 备件档案Controller
 *
 * @author qygx
 * @date 2022-09-09
 */
@RestController
@RequestMapping("/mes/csm/consuma")
public class CsmConsumaController extends BaseController
{
    @Autowired
    private ICsmConsumaService csmConsumaService;

    /**
     * 查询备件档案列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consuma:list')")
    @GetMapping("/list")
    public TableDataInfo list(CsmConsuma csmConsuma)
    {
        startPage();
        List<CsmConsuma> list = csmConsumaService.selectCsmConsumaList(csmConsuma);
        return getDataTable(list);
    }

    /**
     * 导出备件档案列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consuma:export')")
    @Log(title = "备件档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CsmConsuma csmConsuma)
    {
        List<CsmConsuma> list = csmConsumaService.selectCsmConsumaList(csmConsuma);
        ExcelUtil<CsmConsuma> util = new ExcelUtil<CsmConsuma>(CsmConsuma.class);
        util.exportExcel(response, list, "备件档案数据");
    }

    /**
     * 获取备件档案详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consuma:query')")
    @GetMapping(value = "/{consumaId}")
    public AjaxResult getInfo(@PathVariable("consumaId") Long consumaId)
    {
        return AjaxResult.success(csmConsumaService.selectCsmConsumaByConsumaId(consumaId));
    }

    /**
     * 新增备件档案
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consuma:add')")
    @Log(title = "备件档案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CsmConsuma csmConsuma)
    {
        return toAjax(csmConsumaService.insertCsmConsuma(csmConsuma));
    }

    /**
     * 修改备件档案
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consuma:edit')")
    @Log(title = "备件档案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CsmConsuma csmConsuma)
    {
        return toAjax(csmConsumaService.updateCsmConsuma(csmConsuma));
    }

    /**
     * 删除备件档案
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consuma:remove')")
    @Log(title = "备件档案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{consumaIds}")
    public AjaxResult remove(@PathVariable Long[] consumaIds)
    {
        return toAjax(csmConsumaService.deleteCsmConsumaByConsumaIds(consumaIds));
    }
}
