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
import com.qygx.mes.csm.domain.CsmReplaceRecord;
import com.qygx.mes.csm.service.ICsmReplaceRecordService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 备件更换记录Controller
 *
 * @author qygx
 * @date 2022-09-22
 */
@RestController
@RequestMapping("/mes/csm/repalceRecord")
public class CsmReplaceRecordController extends BaseController
{
    @Autowired
    private ICsmReplaceRecordService csmReplaceRecordService;

    /**
     * 查询备件更换记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repalceRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(CsmReplaceRecord csmReplaceRecord)
    {
        startPage();
        List<CsmReplaceRecord> list = csmReplaceRecordService.selectCsmReplaceRecordList(csmReplaceRecord);
        return getDataTable(list);
    }

    /**
     * 导出备件更换记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repalceRecord:export')")
    @Log(title = "备件更换记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CsmReplaceRecord csmReplaceRecord)
    {
        List<CsmReplaceRecord> list = csmReplaceRecordService.selectCsmReplaceRecordList(csmReplaceRecord);
        ExcelUtil<CsmReplaceRecord> util = new ExcelUtil<CsmReplaceRecord>(CsmReplaceRecord.class);
        util.exportExcel(response, list, "备件更换记录数据");
    }

    /**
     * 获取备件更换记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repalceRecord:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(csmReplaceRecordService.selectCsmReplaceRecordByRecordId(recordId));
    }

    /**
     * 新增备件更换记录
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repalceRecord:add')")
    @Log(title = "备件更换记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CsmReplaceRecord csmReplaceRecord)
    {
        return toAjax(csmReplaceRecordService.insertCsmReplaceRecord(csmReplaceRecord));
    }

    /**
     * 修改备件更换记录
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repalceRecord:edit')")
    @Log(title = "备件更换记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CsmReplaceRecord csmReplaceRecord)
    {
        return toAjax(csmReplaceRecordService.updateCsmReplaceRecord(csmReplaceRecord));
    }

    /**
     * 删除备件更换记录
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:repalceRecord:remove')")
    @Log(title = "备件更换记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(csmReplaceRecordService.deleteCsmReplaceRecordByRecordIds(recordIds));
    }
}
