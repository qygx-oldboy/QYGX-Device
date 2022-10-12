package com.qygx.mes.pro.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.qygx.common.core.domain.Timeline;
import com.qygx.mes.cal.service.ICalShiftService;
import com.qygx.system.service.IDvMachineryRunService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import com.qygx.mes.pro.domain.ProTrace;
import com.qygx.mes.pro.service.IProTraceService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 生产追溯Controller
 *
 * @author qygx
 * @date 2022-09-12
 */
@Api("生产追溯")
@RestController
@RequestMapping("/mes/pro/trace")
public class ProTraceController extends BaseController
{
    @Autowired
    private IProTraceService proTraceService;

    @Autowired
    private ICalShiftService calShiftService;

    @Autowired
    private IDvMachineryRunService dvRunService;

    /**
     * 查询生产追溯列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:trace:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProTrace proTrace)
    {
        startPage();
        List<ProTrace> list = proTraceService.selectProTraceList(proTrace);
        return getDataTable(list);
    }

    /**
     * 导出生产追溯列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:trace:export')")
    @Log(title = "生产追溯", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProTrace proTrace)
    {
        List<ProTrace> list = proTraceService.selectProTraceList(proTrace);
        ExcelUtil<ProTrace> util = new ExcelUtil<ProTrace>(ProTrace.class);
        util.exportExcel(response, list, "生产追溯数据");
    }

    /**
     * 获取生产追溯详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:trace:query')")
    @GetMapping(value = "/{traceId}")
    public AjaxResult getInfo(@PathVariable("traceId") Long traceId)
    {
        return AjaxResult.success(proTraceService.selectProTraceByTraceId(traceId));
    }

    /**
     * 新增生产追溯
     */
//    @PreAuthorize("@ss.hasPermi('mes:pro:trace:add')")
   // @Log(title = "生产追溯", businessType = BusinessType.INSERT)
    @ApiOperation("新增生产追溯")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "traceId", value = "追溯id", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "itemCode", value = "产品物料编码", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "itemName", value = "产品物料名称", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "processId", value = "工序id", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "processCode", value = "工序编码", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "processName", value = "工序名称", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "machineryId", value = "设备ID", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "machineryCode", value = "设备编码", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "machineryName", value = "设备名称", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "operator", value = "作业人员", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "startTime", value = "开始生产时间", dataType = "Date", dataTypeClass = Date.class),
            @ApiImplicitParam(name = "endTime", value = "完成生产时间", dataType = "Date", dataTypeClass = Date.class),
            @ApiImplicitParam(name = "duration", value = "生产时长", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "deviceParam", value = "设备运行参数", dataType = "String", dataTypeClass = String.class),
    })
    @PostMapping
    public AjaxResult add(@RequestBody ProTrace proTrace) throws ParseException, CloneNotSupportedException {
        return toAjax(proTraceService.insertProTrace(proTrace));
    }

    /**
     * 修改生产追溯
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:trace:edit')")
    @Log(title = "生产追溯", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProTrace proTrace)
    {
        return toAjax(proTraceService.updateProTrace(proTrace));
    }

    /**
     * 删除生产追溯
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:trace:remove')")
    @Log(title = "生产追溯", businessType = BusinessType.DELETE)
    @DeleteMapping("/{traceIds}")
    public AjaxResult remove(@PathVariable Long[] traceIds)
    {
        return toAjax(proTraceService.deleteProTraceByTraceIds(traceIds));
    }

    /**
     * 查询生产追溯历史
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:trace:history')")
    @GetMapping("/history")
    public AjaxResult history(ProTrace proTrace) {
        List<Timeline> list = proTraceService.getTraceList(proTrace);
        return AjaxResult.success(list);
    }
}
