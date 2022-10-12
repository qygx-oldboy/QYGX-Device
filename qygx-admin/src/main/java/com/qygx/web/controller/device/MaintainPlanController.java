package com.qygx.web.controller.device;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.qygx.common.constant.DeviceConstants;
import com.qygx.common.core.domain.entity.SysUser;
import com.qygx.common.utils.StringUtils;
import com.qygx.mes.dv.domain.DvCheckMachinery;
import com.qygx.mes.dv.service.IDvCheckMachineryService;
import com.qygx.system.domain.MaintainPlanDetail;
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
    private IDvCheckMachineryService dvCheckService;

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
        MaintainPlan maintainPlan = maintainPlanService.selectMaintainPlanByPlanId(planId);
        List<MaintainPlanDetail> maintainPlanDetails = maintainPlanService.selectDetailByPlanId(maintainPlan.getPlanId());
        maintainPlan.setMaintainPlanDetailList(maintainPlanDetails);
        return AjaxResult.success(maintainPlan);
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
        int i = maintainPlanService.deleteMaintainPlanByPlanIds(planIds);
        for (long plan:
        planIds) {
            DvCheckMachinery dvCheck = new DvCheckMachinery();
            dvCheck.setPlanId(plan);
            List<DvCheckMachinery> dvCheckList = dvCheckService.selectDvCheckMachineryList(dvCheck);
            if(dvCheckList.size() > 0 ){
                List<Long> list = dvCheckList.stream().map(DvCheckMachinery::getRecordId).collect(Collectors.toList());
                Long[] recordIds = list.stream().toArray(Long[]::new);
                dvCheckService.deleteDvCheckMachineryByRecordIds(recordIds);
            }
        }

        return toAjax( i );
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

        //todo 查询是否添加了设备，并设置了计划时间
        DvCheckMachinery dvCheck = new DvCheckMachinery();
        dvCheck.setPlanId(maintainPlan.getPlanId());
        List<DvCheckMachinery> dvCheckList = dvCheckService.selectDvCheckMachineryList(dvCheck);
        if(dvCheckList.size() == 0){
            return AjaxResult.error("启动失败，该计划未添加保养设备");
        }else{
            for (DvCheckMachinery check:
                dvCheckList) {
                if(StringUtils.isNull(check.getFirstTime())){
                    return AjaxResult.error("启动失败，设备未设置保养计划时间");
                }
            }
        }

        return toAjax(maintainPlanService.updatePlanStatus(maintainPlan));
    }



}
