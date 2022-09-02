package com.qygx.mes.pro.controller;

import com.qygx.common.annotation.Log;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.mes.pro.domain.ProWorkorderBom;
import com.qygx.mes.pro.service.IProWorkorderBomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 生产工单BOM组成Controller
 * 
 * @author yinjinlu
 * @date 2022-05-09
 */
@RestController
@RequestMapping("/mes/pro/workorderbom")
public class ProWorkorderBomController extends BaseController
{
    @Autowired
    private IProWorkorderBomService proWorkorderBomService;

    /**
     * 查询生产工单BOM组成列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorderbom:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProWorkorderBom proWorkorderBom)
    {
        startPage();
        List<ProWorkorderBom> list = proWorkorderBomService.selectProWorkorderBomList(proWorkorderBom);
        return getDataTable(list);
    }

    /**
     * 导出生产工单BOM组成列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorderbom:export')")
    @Log(title = "生产工单BOM组成", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProWorkorderBom proWorkorderBom)
    {
        List<ProWorkorderBom> list = proWorkorderBomService.selectProWorkorderBomList(proWorkorderBom);
        ExcelUtil<ProWorkorderBom> util = new ExcelUtil<ProWorkorderBom>(ProWorkorderBom.class);
        util.exportExcel(response, list, "生产工单BOM组成数据");
    }

    /**
     * 获取生产工单BOM组成详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorderbom:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(proWorkorderBomService.selectProWorkorderBomByLineId(lineId));
    }

    /**
     * 新增生产工单BOM组成
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorderbom:add')")
    @Log(title = "生产工单BOM组成", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProWorkorderBom proWorkorderBom)
    {
        return toAjax(proWorkorderBomService.insertProWorkorderBom(proWorkorderBom));
    }

    /**
     * 修改生产工单BOM组成
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorderbom:edit')")
    @Log(title = "生产工单BOM组成", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProWorkorderBom proWorkorderBom)
    {
        return toAjax(proWorkorderBomService.updateProWorkorderBom(proWorkorderBom));
    }

    /**
     * 删除生产工单BOM组成
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorderbom:remove')")
    @Log(title = "生产工单BOM组成", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(proWorkorderBomService.deleteProWorkorderBomByLineIds(lineIds));
    }
}
