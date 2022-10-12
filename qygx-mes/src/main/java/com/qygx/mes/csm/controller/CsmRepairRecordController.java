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
import com.qygx.mes.csm.domain.CsmRepairRecord;
import com.qygx.mes.csm.service.ICsmRepairRecordService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 备件修复记录Controller
 *
 * @author qygx
 * @date 2022-10-10
 */
@RestController
@RequestMapping("/mes/csm/repairRecord")
public class CsmRepairRecordController extends BaseController
{
    @Autowired
    private ICsmRepairRecordService csmRepairRecordService;

    /**
     * 查询备件修复记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repairRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(CsmRepairRecord csmRepairRecord)
    {
        startPage();
        List<CsmRepairRecord> list = csmRepairRecordService.selectCsmRepairRecordList(csmRepairRecord);
        return getDataTable(list);
    }

    /**
     * 导出备件修复记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repairRecord:export')")
    @Log(title = "备件修复记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CsmRepairRecord csmRepairRecord)
    {
        List<CsmRepairRecord> list = csmRepairRecordService.selectCsmRepairRecordList(csmRepairRecord);
        ExcelUtil<CsmRepairRecord> util = new ExcelUtil<CsmRepairRecord>(CsmRepairRecord.class);
        util.exportExcel(response, list, "备件修复记录数据");
    }

    /**
     * 获取备件修复记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repairRecord:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(csmRepairRecordService.selectCsmRepairRecordByRecordId(recordId));
    }

    /**
     * 新增备件修复记录
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repairRecord:add')")
    @Log(title = "备件修复记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CsmRepairRecord csmRepairRecord)
    {
        return toAjax(csmRepairRecordService.insertCsmRepairRecord(csmRepairRecord));
    }

    /**
     * 修改备件修复记录
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repairRecord:edit')")
    @Log(title = "备件修复记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CsmRepairRecord csmRepairRecord)
    {
        return toAjax(csmRepairRecordService.updateCsmRepairRecord(csmRepairRecord));
    }

    /**
     * 删除备件修复记录
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repairRecord:remove')")
    @Log(title = "备件修复记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(csmRepairRecordService.deleteCsmRepairRecordByRecordIds(recordIds));
    }
}
