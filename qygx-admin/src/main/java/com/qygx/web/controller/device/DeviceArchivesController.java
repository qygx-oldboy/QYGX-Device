package com.qygx.web.controller.device;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.qygx.common.constant.DeviceConstants;
import com.qygx.common.constant.UserConstants;
import com.qygx.mes.pro.domain.ProProcess;
import com.qygx.system.domain.ProInspect;
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
import com.qygx.system.domain.DeviceArchives;
import com.qygx.system.service.IDeviceArchivesService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * archivesController
 *
 * @author qygx
 * @date 2022-08-12
 */
@RestController
@RequestMapping("/device/archives")
public class DeviceArchivesController extends BaseController
{
    @Autowired
    private IDeviceArchivesService deviceArchivesService;

    /**
     * 查询archives列表
     */
    @PreAuthorize("@ss.hasPermi('device:archives:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceArchives deviceArchives)
    {
        startPage();
        List<DeviceArchives> list = deviceArchivesService.selectDeviceArchivesList(deviceArchives);
        return getDataTable(list);
    }


    /**
     *  查询所有 使用的设备
     * @return
     */
    @PreAuthorize("@ss.hasPermi('device:archives:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        DeviceArchives deviceArchives = new DeviceArchives();
        List<DeviceArchives> list = deviceArchivesService.selectDeviceArchivesList(deviceArchives);
        return AjaxResult.success(list);
    }

    /**
     * 导入archives列表
     */
    @PreAuthorize("@ss.hasPermi('device:archives:import')")
    @Log(title = "archives", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<DeviceArchives> util = new ExcelUtil<DeviceArchives>(DeviceArchives.class);
        List<DeviceArchives> deviceList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = deviceArchivesService.importDevice(deviceList, updateSupport, operName);
        return AjaxResult.success(message);
    }


    /**
     * 下载导入模块
     * @param response
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<DeviceArchives> util = new ExcelUtil<DeviceArchives>(DeviceArchives.class);
        util.importTemplateExcel(response, "设备数据");
    }

    /**
     * 导出archives列表
     */
    @PreAuthorize("@ss.hasPermi('device:archives:export')")
    @Log(title = "archives", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceArchives deviceArchives)
    {
        List<DeviceArchives> list = deviceArchivesService.selectDeviceArchivesList(deviceArchives);
        ExcelUtil<DeviceArchives> util = new ExcelUtil<DeviceArchives>(DeviceArchives.class);
        util.exportExcel(response, list, "archives数据");
    }

    /**
     * 获取archives详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:archives:query')")
    @GetMapping(value = "/{deviceId}")
    public AjaxResult getInfo(@PathVariable("deviceId") Long deviceId)
    {
        return AjaxResult.success(deviceArchivesService.selectDeviceArchivesByDeviceId(deviceId));
    }

    /**
     * 新增archives
     */
    @PreAuthorize("@ss.hasPermi('device:archives:add')")
    @Log(title = "archives", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceArchives deviceArchives)
    {

        if (DeviceConstants.NOT_UNIQUE.equals(deviceArchivesService.checkDeviceNameUnique(deviceArchives.getName())))
        {
            return AjaxResult.error("新增设备'" + deviceArchives.getName() + "'失败，设备名称已存在");
        }

        return toAjax(deviceArchivesService.insertDeviceArchives(deviceArchives));
    }

    /**
     * 修改archives
     */
    @PreAuthorize("@ss.hasPermi('device:archives:edit')")
    @Log(title = "archives", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceArchives deviceArchives)
    {
        return toAjax(deviceArchivesService.updateDeviceArchives(deviceArchives));
    }

    /**
     * 删除archives
     */
    @PreAuthorize("@ss.hasPermi('device:archives:remove')")
    @Log(title = "archives", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deviceIds}")
    public AjaxResult remove(@PathVariable Long[] deviceIds)
    {
        return toAjax(deviceArchivesService.deleteDeviceArchivesByDeviceIds(deviceIds));
    }
}
