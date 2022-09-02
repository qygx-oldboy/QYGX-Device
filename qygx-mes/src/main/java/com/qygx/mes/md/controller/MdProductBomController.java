package com.qygx.mes.md.controller;

import com.qygx.common.annotation.Log;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.mes.md.domain.MdProductBom;
import com.qygx.mes.md.service.IMdProductBomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 产品BOM关系Controller
 * 
 * @author yinjinlu
 * @date 2022-05-09
 */
@RestController
@RequestMapping("/mes/md/bom")
public class MdProductBomController extends BaseController
{
    @Autowired
    private IMdProductBomService mdProductBomService;

    /**
     * 查询产品BOM关系列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdProductBom mdProductBom)
    {
        startPage();
        List<MdProductBom> list = mdProductBomService.selectMdProductBomList(mdProductBom);
        return getDataTable(list);
    }

    /**
     * 导出产品BOM关系列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:export')")
    @Log(title = "产品BOM关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdProductBom mdProductBom)
    {
        List<MdProductBom> list = mdProductBomService.selectMdProductBomList(mdProductBom);
        ExcelUtil<MdProductBom> util = new ExcelUtil<MdProductBom>(MdProductBom.class);
        util.exportExcel(response, list, "产品BOM关系数据");
    }

    /**
     * 获取产品BOM关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:query')")
    @GetMapping(value = "/{bomId}")
    public AjaxResult getInfo(@PathVariable("bomId") Long bomId)
    {
        return AjaxResult.success(mdProductBomService.selectMdProductBomByBomId(bomId));
    }

    /**
     * 新增产品BOM关系
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:add')")
    @Log(title = "产品BOM关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdProductBom mdProductBom)
    {
        return toAjax(mdProductBomService.insertMdProductBom(mdProductBom));
    }

    /**
     * 修改产品BOM关系
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:edit')")
    @Log(title = "产品BOM关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdProductBom mdProductBom)
    {
        return toAjax(mdProductBomService.updateMdProductBom(mdProductBom));
    }

    /**
     * 删除产品BOM关系
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:remove')")
    @Log(title = "产品BOM关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bomIds}")
    public AjaxResult remove(@PathVariable Long[] bomIds)
    {
        return toAjax(mdProductBomService.deleteMdProductBomByBomIds(bomIds));
    }
}
