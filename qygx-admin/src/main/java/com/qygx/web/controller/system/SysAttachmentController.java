package com.qygx.web.controller.system;

import com.qygx.common.annotation.Log;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.system.domain.SysAttachment;
import com.qygx.system.service.ISysAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 附件Controller
 * 
 * @author yinjinlu
 * @date 2022-07-26
 */
@RestController
@RequestMapping("/system/attachment")
public class SysAttachmentController extends BaseController
{
    @Autowired
    private ISysAttachmentService sysAttachmentService;

    /**
     * 查询附件列表
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysAttachment sysAttachment)
    {
        startPage();
        List<SysAttachment> list = sysAttachmentService.selectSysAttachmentList(sysAttachment);
        return getDataTable(list);
    }

    /**
     * 导出附件列表
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:export')")
    @Log(title = "附件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysAttachment sysAttachment)
    {
        List<SysAttachment> list = sysAttachmentService.selectSysAttachmentList(sysAttachment);
        ExcelUtil<SysAttachment> util = new ExcelUtil<SysAttachment>(SysAttachment.class);
        util.exportExcel(response, list, "附件数据");
    }

    /**
     * 获取附件详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:query')")
    @GetMapping(value = "/{attachmentId}")
    public AjaxResult getInfo(@PathVariable("attachmentId") Long attachmentId)
    {
        return AjaxResult.success(sysAttachmentService.selectSysAttachmentByAttachmentId(attachmentId));
    }

    /**
     * 新增附件
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:add')")
    @Log(title = "附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysAttachment sysAttachment)
    {
        return toAjax(sysAttachmentService.insertSysAttachment(sysAttachment));
    }

    /**
     * 修改附件
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:edit')")
    @Log(title = "附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysAttachment sysAttachment)
    {
        return toAjax(sysAttachmentService.updateSysAttachment(sysAttachment));
    }

    /**
     * 删除附件
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:remove')")
    @Log(title = "附件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attachmentIds}")
    public AjaxResult remove(@PathVariable Long[] attachmentIds)
    {
        return toAjax(sysAttachmentService.deleteSysAttachmentByAttachmentIds(attachmentIds));
    }
}
