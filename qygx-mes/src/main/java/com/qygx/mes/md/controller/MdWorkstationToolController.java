package com.qygx.mes.md.controller;

import com.qygx.common.annotation.Log;
import com.qygx.common.constant.UserConstants;
import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.core.page.TableDataInfo;
import com.qygx.common.enums.BusinessType;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.mes.md.domain.MdWorkstationTool;
import com.qygx.mes.md.service.IMdWorkstationToolService;
import com.qygx.mes.tm.domain.TmToolType;
import com.qygx.mes.tm.service.ITmToolTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 工装夹具资源Controller
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
@RestController
@RequestMapping("/mes/md/workstationtool")
public class MdWorkstationToolController extends BaseController
{
    @Autowired
    private IMdWorkstationToolService mdWorkstationToolService;

    @Autowired
    private ITmToolTypeService toolTypeService;

    /**
     * 查询工装夹具资源列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdWorkstationTool mdWorkstationTool)
    {
        startPage();
        List<MdWorkstationTool> list = mdWorkstationToolService.selectMdWorkstationToolList(mdWorkstationTool);
        return getDataTable(list);
    }

    /**
     * 导出工装夹具资源列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:export')")
    @Log(title = "工装夹具资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdWorkstationTool mdWorkstationTool)
    {
        List<MdWorkstationTool> list = mdWorkstationToolService.selectMdWorkstationToolList(mdWorkstationTool);
        ExcelUtil<MdWorkstationTool> util = new ExcelUtil<MdWorkstationTool>(MdWorkstationTool.class);
        util.exportExcel(response, list, "工装夹具资源数据");
    }

    /**
     * 获取工装夹具资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(mdWorkstationToolService.selectMdWorkstationToolByRecordId(recordId));
    }

    /**
     * 新增工装夹具资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:add')")
    @Log(title = "工装夹具资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdWorkstationTool mdWorkstationTool)
    {
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationToolService.checkToolTypeExists(mdWorkstationTool))){
            return AjaxResult.error("此工装夹具类型已添加！");
        }
        TmToolType type = toolTypeService.selectTmToolTypeByToolTypeId(mdWorkstationTool.getToolTypeId());
        mdWorkstationTool.setToolTypeCode(type.getToolTypeCode());
        mdWorkstationTool.setToolTypeName(type.getToolTypeName());
        return toAjax(mdWorkstationToolService.insertMdWorkstationTool(mdWorkstationTool));
    }

    /**
     * 修改工装夹具资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:edit')")
    @Log(title = "工装夹具资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdWorkstationTool mdWorkstationTool)
    {
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationToolService.checkToolTypeExists(mdWorkstationTool))){
            return AjaxResult.error("此工装夹具类型已添加！");
        }
        TmToolType type = toolTypeService.selectTmToolTypeByToolTypeId(mdWorkstationTool.getToolTypeId());
        mdWorkstationTool.setToolTypeCode(type.getToolTypeCode());
        mdWorkstationTool.setToolTypeName(type.getToolTypeName());
        return toAjax(mdWorkstationToolService.updateMdWorkstationTool(mdWorkstationTool));
    }

    /**
     * 删除工装夹具资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:remove')")
    @Log(title = "工装夹具资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(mdWorkstationToolService.deleteMdWorkstationToolByRecordIds(recordIds));
    }
}
