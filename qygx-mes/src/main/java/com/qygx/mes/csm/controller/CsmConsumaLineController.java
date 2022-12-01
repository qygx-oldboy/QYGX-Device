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
import com.qygx.mes.csm.domain.CsmConsumaLine;
import com.qygx.mes.csm.service.ICsmConsumaLineService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 备件周期项Controller
 *
 * @author qygx
 * @date 2022-11-25
 */
@RestController
@RequestMapping("/mes/csm/line")
public class CsmConsumaLineController extends BaseController
{
    @Autowired
    private ICsmConsumaLineService csmConsumaLineService;

    /**
     * 查询备件周期项列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:line:list')")
    @GetMapping("/list")
    public TableDataInfo list(CsmConsumaLine csmConsumaLine)
    {
        startPage();
        List<CsmConsumaLine> list = csmConsumaLineService.selectCsmConsumaLineList(csmConsumaLine);
        return getDataTable(list);
    }

    /**
     * 导出备件周期项列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:line:export')")
    @Log(title = "备件周期项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CsmConsumaLine csmConsumaLine)
    {
        List<CsmConsumaLine> list = csmConsumaLineService.selectCsmConsumaLineList(csmConsumaLine);
        ExcelUtil<CsmConsumaLine> util = new ExcelUtil<CsmConsumaLine>(CsmConsumaLine.class);
        util.exportExcel(response, list, "备件周期项数据");
    }

    /**
     * 获取备件周期项详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:line:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(csmConsumaLineService.selectCsmConsumaLineByLineId(lineId));
    }

    /**
     * 新增备件周期项
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:line:add')")
    @Log(title = "备件周期项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CsmConsumaLine csmConsumaLine)
    {
        return toAjax(csmConsumaLineService.insertCsmConsumaLine(csmConsumaLine));
    }

    /**
     * 修改备件周期项
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:line:edit')")
    @Log(title = "备件周期项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CsmConsumaLine csmConsumaLine)
    {
        return toAjax(csmConsumaLineService.updateCsmConsumaLine(csmConsumaLine));
    }

    /**
     * 删除备件周期项
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:line:remove')")
    @Log(title = "备件周期项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(csmConsumaLineService.deleteCsmConsumaLineByLineIds(lineIds));
    }
}
