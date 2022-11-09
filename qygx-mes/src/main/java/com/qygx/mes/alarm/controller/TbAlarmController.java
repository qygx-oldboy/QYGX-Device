package com.qygx.mes.alarm.controller;

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
import com.qygx.mes.alarm.domain.TbAlarm;
import com.qygx.mes.alarm.service.ITbAlarmService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 告警管理Controller
 *
 * @author qygx
 * @date 2022-10-19
 */
@RestController
@RequestMapping("/mes/alarm/alarm")
public class TbAlarmController extends BaseController
{
    @Autowired
    private ITbAlarmService tbAlarmService;

    /**
     * 查询告警管理列表
     */
    @PreAuthorize("@ss.hasPermi('mes:alarm:alarm:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbAlarm tbAlarm)
    {
        startPage();
        List<TbAlarm> list = tbAlarmService.selectTbAlarmList(tbAlarm);
        return getDataTable(list);
    }

    /**
     * 导出告警管理列表
     */
    @PreAuthorize("@ss.hasPermi('mes:alarm:alarm:export')")
    @Log(title = "告警管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbAlarm tbAlarm)
    {
        List<TbAlarm> list = tbAlarmService.selectTbAlarmList(tbAlarm);
        ExcelUtil<TbAlarm> util = new ExcelUtil<TbAlarm>(TbAlarm.class);
        util.exportExcel(response, list, "告警管理数据");
    }

    /**
     * 获取告警管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:alarm:alarm:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tbAlarmService.selectTbAlarmById(id));
    }

    /**
     * 新增告警管理
     */
    @PreAuthorize("@ss.hasPermi('mes:alarm:alarm:add')")
    @Log(title = "告警管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbAlarm tbAlarm)
    {
        return toAjax(tbAlarmService.insertTbAlarm(tbAlarm));
    }

    /**
     * 修改告警管理
     */
    @PreAuthorize("@ss.hasPermi('mes:alarm:alarm:edit')")
    @Log(title = "告警管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbAlarm tbAlarm)
    {
        return toAjax(tbAlarmService.updateTbAlarm(tbAlarm));
    }

    /**
     * 删除告警管理
     */
    @PreAuthorize("@ss.hasPermi('mes:alarm:alarm:remove')")
    @Log(title = "告警管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbAlarmService.deleteTbAlarmByIds(ids));
    }
}
