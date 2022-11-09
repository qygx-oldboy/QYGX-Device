package com.qygx.mes.pro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.qygx.system.domain.vo.InspectVo;
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
import com.qygx.mes.pro.domain.ProProgress;
import com.qygx.mes.pro.service.IProProgressService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 生产进度管理Controller
 *
 * @author qygx
 * @date 2022-10-25
 */
@RestController
@RequestMapping("/mes/progress")
public class ProProgressController extends BaseController
{
    @Autowired
    private IProProgressService proProgressService;

    /**
     * 查询生产进度管理列表
     */
    @PreAuthorize("@ss.hasPermi('mes:progress:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProProgress proProgress)
    {
        startPage();
        List<ProProgress> list = proProgressService.selectProProgressList(proProgress);
        return getDataTable(list);
    }

    /**
     * 导出生产进度管理列表
     */
    @PreAuthorize("@ss.hasPermi('mes:progress:export')")
    @Log(title = "生产进度管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProProgress proProgress)
    {
        List<ProProgress> list = proProgressService.selectProProgressList(proProgress);
        ExcelUtil<ProProgress> util = new ExcelUtil<ProProgress>(ProProgress.class);
        util.exportExcel(response, list, "生产进度管理数据");
    }

    /**
     * 获取生产进度管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:progress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(proProgressService.selectProProgressById(id));
    }

    /**
     * 新增生产进度管理
     */
    @PreAuthorize("@ss.hasPermi('mes:progress:add')")
    @Log(title = "生产进度管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProProgress proProgress)
    {
        return toAjax(proProgressService.insertProProgress(proProgress));
    }

    /**
     * 修改生产进度管理
     */
    @PreAuthorize("@ss.hasPermi('mes:progress:edit')")
    @Log(title = "生产进度管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProProgress proProgress)
    {
        return toAjax(proProgressService.updateProProgress(proProgress));
    }

    /**
     * 删除生产进度管理
     */
    @PreAuthorize("@ss.hasPermi('mes:progress:remove')")
    @Log(title = "生产进度管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(proProgressService.deleteProProgressByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('mes:progress:query')")
    @GetMapping("/getBarData")
    public AjaxResult getBarData(ProProgress proProgress){
        HashMap<String, Object> map = new HashMap<>();
        //todo 返回一个map
        ArrayList<String> cols = new ArrayList<>();
        cols.add("batchCode");
        cols.add("materialQuantity");
        cols.add("storageQuantity");
        cols.add("grindQuantity");
        cols.add("polishQuantity");
        cols.add("inspectQuantity");
        map.put("columns",cols);
        List<ProProgress> progressList = proProgressService.selectBarData(proProgress);
        map.put("rows",progressList);
        return AjaxResult.success(map);
    }
}
