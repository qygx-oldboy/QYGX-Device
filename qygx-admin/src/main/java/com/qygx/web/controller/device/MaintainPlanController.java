package com.qygx.web.controller.device;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.qygx.common.constant.DeviceConstants;
import com.qygx.common.constant.UserConstants;
import com.qygx.common.core.domain.entity.SysDept;
import com.qygx.common.core.domain.entity.SysUser;
import com.qygx.system.domain.DeviceArchives;
import com.qygx.system.service.IDeviceArchivesService;
import com.qygx.system.service.ISysUserService;
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
import com.qygx.system.domain.MaintainPlan;
import com.qygx.system.service.IMaintainPlanService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 保养计划Controller
 * 
 * @author qygx
 * @date 2022-08-12
 */
@RestController
@RequestMapping("/device/plan")
public class MaintainPlanController extends BaseController
{
    @Autowired
    private IMaintainPlanService maintainPlanService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IDeviceArchivesService deviceArchivesService;

    /**
     * 查询保养计划列表
     */
    @PreAuthorize("@ss.hasPermi('device:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaintainPlan maintainPlan)
    {
        startPage();
        List<MaintainPlan> list = maintainPlanService.selectMaintainPlanList(maintainPlan);
        return getDataTable(list);
    }

    /**
     * 导出保养计划列表
     */
    @PreAuthorize("@ss.hasPermi('device:plan:export')")
    @Log(title = "保养计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaintainPlan maintainPlan)
    {
        List<MaintainPlan> list = maintainPlanService.selectMaintainPlanList(maintainPlan);
        ExcelUtil<MaintainPlan> util = new ExcelUtil<MaintainPlan>(MaintainPlan.class);
        util.exportExcel(response, list, "保养计划数据");
    }

    /**
     * 获取保养计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:plan:query')")
    @GetMapping(value = "/{planId}")
    public AjaxResult getInfo(@PathVariable("planId") Long planId)
    {
//        AjaxResult ajax = AjaxResult.success();
//        SysUser user = new SysUser();
//        List<SysUser> sysUsers = sysUserService.selectUserList(user);
//        ajax.put("users", sysUsers);
        return AjaxResult.success(maintainPlanService.selectMaintainPlanByPlanId(planId));
    }

    /**
     * 新增保养计划
     */
    @PreAuthorize("@ss.hasPermi('device:plan:add')")
    @Log(title = "保养计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaintainPlan maintainPlan)
    {
        return toAjax(maintainPlanService.insertMaintainPlan(maintainPlan));
    }

    /**
     * 修改保养计划
     */
    @PreAuthorize("@ss.hasPermi('device:plan:edit')")
    @Log(title = "保养计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaintainPlan maintainPlan)
    {
        return toAjax(maintainPlanService.updateMaintainPlan(maintainPlan));
    }

    /**
     * 删除保养计划
     */
    @PreAuthorize("@ss.hasPermi('device:plan:remove')")
    @Log(title = "保养计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{planIds}")
    public AjaxResult remove(@PathVariable Long[] planIds)
    {
        return toAjax(maintainPlanService.deleteMaintainPlanByPlanIds(planIds));
    }


    /**
     * 获取保养人/稽核人下拉框
     */
    @PreAuthorize("@ss.hasPermi('device:plan:query')")
    @GetMapping("/getUserOptions")
    public AjaxResult getUserOptions()
    {
        AjaxResult ajax = AjaxResult.success();
        SysUser user = new SysUser();
        List<SysUser> sysUsers = sysUserService.selectUserList(user);
        ajax.put("data", sysUsers.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));   //移除admin账户
        return ajax;
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('device:plan:edit')")
    @Log(title = "保养计划", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody MaintainPlan maintainPlan)
    {
        //userService.checkUserAllowed(user); 检查用户是否有权操作
       // userService.checkUserDataScope(user.getUserId());
       // user.setUpdateBy(getUsername());

        //todo 查询是否有 相同设备类型,并且保养等级一样的 已经启用
        if (maintainPlan.getUseState().equals("0") && DeviceConstants.NOT_UNIQUE.equals(maintainPlanService.checkPlanUnique(maintainPlan)))
        {
            return AjaxResult.error("启动失败，已有相同计划处于启动中，请检查设备类型和保养等级是否重复");
        }

        return toAjax(maintainPlanService.updatePlanStatus(maintainPlan));
    }

    /**
     * 获取计划对应设备树选项
     */
//    @PreAuthorize("@ss.hasPermi('device:plan:query')")
//    @GetMapping("/getDeviceOptions")
//    public AjaxResult getDeviceOptions()
//    {
//        AjaxResult ajax = AjaxResult.success();
//        List<DeviceArchives> deviceArchives = deviceArchivesService.selectDeviceArchivesList(new DeviceArchives());
//        //todo 移除已经被选择的设备
//        //ajax.put("data", sysUsers.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
//        ajax.put("data", deviceArchives);
//        return ajax;
//    }

    /**
     * 加载计划对应设备树
     */
//    @GetMapping(value = "/planDeviceTreeselect/{planId}")
//    public AjaxResult planDeviceTreeselect(@PathVariable("planId") Long planId)
//    {
//        List<DeviceArchives> deviceArchives = deviceArchivesService.selectDeviceArchivesList(new DeviceArchives());
//        AjaxResult ajax = AjaxResult.success();
//
//        //todo 查询计划对应设备列表
//       // ajax.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
//       // ajax.put("devices", deptService.buildDeptTreeSelect(depts));
//        return ajax;
//    }


}
