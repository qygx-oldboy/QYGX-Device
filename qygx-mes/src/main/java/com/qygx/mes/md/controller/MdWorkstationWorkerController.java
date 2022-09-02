package com.qygx.mes.md.controller;

import com.qygx.common.annotation.Log;
import com.qygx.common.constant.UserConstants;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.mes.md.domain.MdWorkstationWorker;
import com.qygx.mes.md.service.IMdWorkstationWorkerService;
import com.qygx.system.domain.SysPost;
import com.qygx.system.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 人力资源Controller
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
@RestController
@RequestMapping("/mes/md/workstationworker")
public class MdWorkstationWorkerController extends BaseController
{
    @Autowired
    private IMdWorkstationWorkerService mdWorkstationWorkerService;

    @Autowired
    private ISysPostService sysPostService;

    /**
     * 查询人力资源列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdWorkstationWorker mdWorkstationWorker)
    {
        startPage();
        List<MdWorkstationWorker> list = mdWorkstationWorkerService.selectMdWorkstationWorkerList(mdWorkstationWorker);
        return getDataTable(list);
    }

    /**
     * 导出人力资源列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:export')")
    @Log(title = "人力资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdWorkstationWorker mdWorkstationWorker)
    {
        List<MdWorkstationWorker> list = mdWorkstationWorkerService.selectMdWorkstationWorkerList(mdWorkstationWorker);
        ExcelUtil<MdWorkstationWorker> util = new ExcelUtil<MdWorkstationWorker>(MdWorkstationWorker.class);
        util.exportExcel(response, list, "人力资源数据");
    }

    /**
     * 获取人力资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(mdWorkstationWorkerService.selectMdWorkstationWorkerByRecordId(recordId));
    }

    /**
     * 新增人力资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:add')")
    @Log(title = "人力资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdWorkstationWorker mdWorkstationWorker)
    {
        SysPost post = sysPostService.selectPostById(mdWorkstationWorker.getPostId());
        mdWorkstationWorker.setPostCode(post.getPostCode());
        mdWorkstationWorker.setPostName(post.getPostName());
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationWorkerService.checkPostExist(mdWorkstationWorker))){
            return AjaxResult.error("此岗位已经添加！");
        }
        return toAjax(mdWorkstationWorkerService.insertMdWorkstationWorker(mdWorkstationWorker));
    }

    /**
     * 修改人力资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:edit')")
    @Log(title = "人力资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdWorkstationWorker mdWorkstationWorker)
    {
        SysPost post = sysPostService.selectPostById(mdWorkstationWorker.getPostId());
        mdWorkstationWorker.setPostCode(post.getPostCode());
        mdWorkstationWorker.setPostName(post.getPostName());
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationWorkerService.checkPostExist(mdWorkstationWorker))){
            return AjaxResult.error("此岗位已经添加！");
        }
        return toAjax(mdWorkstationWorkerService.updateMdWorkstationWorker(mdWorkstationWorker));
    }

    /**
     * 删除人力资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationworker:remove')")
    @Log(title = "人力资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(mdWorkstationWorkerService.deleteMdWorkstationWorkerByRecordIds(recordIds));
    }
}
