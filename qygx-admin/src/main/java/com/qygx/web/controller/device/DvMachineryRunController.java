package com.qygx.web.controller.device;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.qygx.system.domain.ProInspect;
import com.qygx.system.domain.dto.DvRunDto;
import com.qygx.system.domain.vo.InspectVo;
import io.swagger.annotations.ApiOperation;
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
import com.qygx.system.domain.DvMachineryRun;
import com.qygx.system.service.IDvMachineryRunService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 运行记录Controller
 *
 * @author qygx
 * @date 2022-09-20
 */
@RestController
@RequestMapping("/device/run")
public class DvMachineryRunController extends BaseController
{
    @Autowired
    private IDvMachineryRunService dvMachineryRunService;

    /**
     * 查询运行记录列表
     */
    @PreAuthorize("@ss.hasPermi('device:run:list')")
    @GetMapping("/list")
    public TableDataInfo list(DvMachineryRun dvMachineryRun)
    {
        startPage();
        List<DvMachineryRun> list = dvMachineryRunService.selectDvMachineryRunList(dvMachineryRun);
        return getDataTable(list);
    }

    /**
     * 导出运行记录列表
     */
    @PreAuthorize("@ss.hasPermi('device:run:export')")
    @Log(title = "运行记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DvMachineryRun dvMachineryRun)
    {
        List<DvMachineryRun> list = dvMachineryRunService.selectDvMachineryRunList(dvMachineryRun);
        ExcelUtil<DvMachineryRun> util = new ExcelUtil<DvMachineryRun>(DvMachineryRun.class);
        util.exportExcel(response, list, "运行记录数据");
    }

    /**
     * 获取运行记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:run:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(dvMachineryRunService.selectDvMachineryRunByRecordId(recordId));
    }

    /**
     * 新增运行记录
     */
    @PreAuthorize("@ss.hasPermi('device:run:add')")
    @Log(title = "运行记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DvMachineryRun dvMachineryRun)
    {
        return toAjax(dvMachineryRunService.insertDvMachineryRun(dvMachineryRun));
    }

    /**
     * 修改运行记录
     */
    @PreAuthorize("@ss.hasPermi('device:run:edit')")
    @Log(title = "运行记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DvMachineryRun dvMachineryRun)
    {
        return toAjax(dvMachineryRunService.updateDvMachineryRun(dvMachineryRun));
    }

    /**
     * 删除运行记录
     */
    @PreAuthorize("@ss.hasPermi('device:run:remove')")
    @Log(title = "运行记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(dvMachineryRunService.deleteDvMachineryRunByRecordIds(recordIds));
    }


    /**
     * 获取稼动率图表
     */
    @ApiOperation("获取稼动率图表")
    @PreAuthorize("@ss.hasPermi('device:run:query')")
    @GetMapping("/getLineData")
    public AjaxResult getLineData(DvMachineryRun dvMachineryRun) throws ParseException {

        HashMap<String, Object> map = new HashMap<>();
        //todo 返回一个map
        ArrayList<String> cols = new ArrayList<>();
        cols.add("recordDate");
        cols.add("cuPao");
        cols.add("cuMo");
        cols.add("shuiPao");
        cols.add("jingPao");
        map.put("columns",cols);
        List<DvRunDto> runList = dvMachineryRunService.selectDailyRunTime();
        map.put("rows",runList);
        return AjaxResult.success(map);
    }
}
