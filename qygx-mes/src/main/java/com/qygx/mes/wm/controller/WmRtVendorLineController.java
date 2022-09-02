package com.qygx.mes.wm.controller;

import com.qygx.common.annotation.Log;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.mes.wm.domain.WmRtVendorLine;
import com.qygx.mes.wm.service.IWmRtVendorLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 供应商退货行Controller
 * 
 * @author yinjinlu
 * @date 2022-06-13
 */
@RestController
@RequestMapping("/mes/wm/rtvendorline")
public class WmRtVendorLineController extends BaseController
{
    @Autowired
    private IWmRtVendorLineService wmRtVendorLineService;

    /**
     * 查询供应商退货行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmRtVendorLine wmRtVendorLine)
    {
        startPage();
        List<WmRtVendorLine> list = wmRtVendorLineService.selectWmRtVendorLineList(wmRtVendorLine);
        return getDataTable(list);
    }

    /**
     * 导出供应商退货行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:export')")
    @Log(title = "供应商退货行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmRtVendorLine wmRtVendorLine)
    {
        List<WmRtVendorLine> list = wmRtVendorLineService.selectWmRtVendorLineList(wmRtVendorLine);
        ExcelUtil<WmRtVendorLine> util = new ExcelUtil<WmRtVendorLine>(WmRtVendorLine.class);
        util.exportExcel(response, list, "供应商退货行数据");
    }

    /**
     * 获取供应商退货行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmRtVendorLineService.selectWmRtVendorLineByLineId(lineId));
    }

    /**
     * 新增供应商退货行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:add')")
    @Log(title = "供应商退货行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmRtVendorLine wmRtVendorLine)
    {
        return toAjax(wmRtVendorLineService.insertWmRtVendorLine(wmRtVendorLine));
    }

    /**
     * 修改供应商退货行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:edit')")
    @Log(title = "供应商退货行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmRtVendorLine wmRtVendorLine)
    {
        return toAjax(wmRtVendorLineService.updateWmRtVendorLine(wmRtVendorLine));
    }

    /**
     * 删除供应商退货行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:remove')")
    @Log(title = "供应商退货行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmRtVendorLineService.deleteWmRtVendorLineByLineIds(lineIds));
    }
}
