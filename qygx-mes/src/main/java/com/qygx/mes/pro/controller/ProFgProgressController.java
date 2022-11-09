package com.qygx.mes.pro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.qygx.mes.pro.domain.ProProgress;
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
import com.qygx.mes.pro.domain.ProFgProgress;
import com.qygx.mes.pro.service.IProFgProgressService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 返工进度Controller
 *
 * @author qygx
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/mes/pro/fgProgress")
public class ProFgProgressController extends BaseController
{
    @Autowired
    private IProFgProgressService proFgProgressService;

    /**
     * 查询返工进度列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:fgProgress:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProFgProgress proFgProgress)
    {
        startPage();
        List<ProFgProgress> list = proFgProgressService.selectProFgProgressList(proFgProgress);
        return getDataTable(list);
    }

    /**
     * 导出返工进度列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:fgProgress:export')")
    @Log(title = "返工进度", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProFgProgress proFgProgress)
    {
        List<ProFgProgress> list = proFgProgressService.selectProFgProgressList(proFgProgress);
        ExcelUtil<ProFgProgress> util = new ExcelUtil<ProFgProgress>(ProFgProgress.class);
        util.exportExcel(response, list, "返工进度数据");
    }

    /**
     * 获取返工进度详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:fgProgress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(proFgProgressService.selectProFgProgressById(id));
    }

    /**
     * 新增返工进度
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:fgProgress:add')")
    @Log(title = "返工进度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProFgProgress proFgProgress)
    {
        return toAjax(proFgProgressService.insertProFgProgress(proFgProgress));
    }

    /**
     * 修改返工进度
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:fgProgress:edit')")
    @Log(title = "返工进度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProFgProgress proFgProgress)
    {
        return toAjax(proFgProgressService.updateProFgProgress(proFgProgress));
    }

    /**
     * 删除返工进度
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:fgProgress:remove')")
    @Log(title = "返工进度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(proFgProgressService.deleteProFgProgressByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('mes:pro:fgProgress:query')")
    @GetMapping("/getBarData")
    public AjaxResult getBarData(ProFgProgress proFgProgress){
        HashMap<String, Object> map = new HashMap<>();
        //todo 返回一个map
        ArrayList<String> cols = new ArrayList<>();
        cols.add("batchCode");
        cols.add("reworkQuantity");
        cols.add("polishQuantity");
        cols.add("inspectQuantity");
        map.put("columns",cols);
        List<ProFgProgress> progressList = proFgProgressService.selectProFgProgressList(proFgProgress);
        map.put("rows",progressList);
        return AjaxResult.success(map);
    }
}
