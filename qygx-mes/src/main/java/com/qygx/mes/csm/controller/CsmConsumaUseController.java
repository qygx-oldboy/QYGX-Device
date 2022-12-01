package com.qygx.mes.csm.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.qygx.common.constant.UserConstants;
import com.qygx.common.core.domain.entity.SysUser;
import com.qygx.common.utils.DateUtils;
import com.qygx.common.utils.SecurityUtils;
import com.qygx.common.utils.StringUtils;
import com.qygx.mes.csm.domain.*;
import com.qygx.mes.csm.service.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 在用备件Controller
 *
 * @author qygx
 * @date 2022-09-07
 */
@RestController
@RequestMapping("/mes/csm/consumause")
public class CsmConsumaUseController extends BaseController
{
    @Autowired
    private ICsmConsumaUseService csmConsumaUseService;

    @Autowired
    private ICsmConsumaService consumaService;

    @Autowired
    private ICsmConsumaLineService consumaLineService;

    @Autowired
    private ICsmReplaceRecordService replaceRecordService;

    @Autowired
    private ICsmRepairRecordService repairRecordService;

    /**
     * 查询在用备件列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:list')")
    @GetMapping("/list")
    public TableDataInfo list(CsmConsumaUse csmConsumaUse)
    {
        startPage();
        List<CsmConsumaUse> list = csmConsumaUseService.selectCsmConsumaUseList(csmConsumaUse);
        return getDataTable(list);
    }

    /**
     * 导出在用备件列表
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:export')")
    @Log(title = "在用备件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CsmConsumaUse csmConsumaUse)
    {
        List<CsmConsumaUse> list = csmConsumaUseService.selectCsmConsumaUseList(csmConsumaUse);
        ExcelUtil<CsmConsumaUse> util = new ExcelUtil<CsmConsumaUse>(CsmConsumaUse.class);
        util.exportExcel(response, list, "在用备件数据");
    }

    /**
     * 获取在用备件详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:query')")
    @GetMapping(value = "/{consumaUseId}")
    public AjaxResult getInfo(@PathVariable("consumaUseId") Long consumaUseId)
    {
        return AjaxResult.success(csmConsumaUseService.selectCsmConsumaUseByConsumaUseId(consumaUseId));
    }

    /**
     * 新增在用备件
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:add')")
    @Log(title = "在用备件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CsmConsumaUse csmConsumaUse)
    {

        //检查该设备是否有该类型的备件在使用，如果有删除掉
        CsmConsumaUse ccu = new CsmConsumaUse();
        ccu.setConsumaId( csmConsumaUse.getConsumaId());
        ccu.setReplaceCycle(csmConsumaUse.getReplaceCycle());
        List<CsmConsumaUse> usedList = csmConsumaUseService.selectCsmConsumaUseList(ccu);
        if(usedList.size()>0){
            List<Long> consumaUseIdList = usedList.stream().map(CsmConsumaUse::getConsumaUseId).collect(Collectors.toList());
            csmConsumaUseService.deleteCsmConsumaUseByConsumaUseIds(consumaUseIdList.toArray(new Long[consumaUseIdList.size()]));
        }
        Date nowDate = DateUtils.getNowDate();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,csmConsumaUse.getReplaceCycle().intValue());
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        csmConsumaUse.setReplaceTime(nowDate);
        csmConsumaUse.setNextReplaceTime(DateUtils.parseDate(time));
        //新增更换记录
        if("UNIQUE".equals(csmConsumaUseService.checkBatchNoUnique(csmConsumaUse.getbatchNo()))){
            CsmReplaceRecord record = new CsmReplaceRecord();
            record.setConsumaCode(csmConsumaUse.getConsumaCode());
            record.setConsumaName(csmConsumaUse.getConsumaName());
            String batchNo = csmConsumaUse.getbatchNo();
           // String realBatchNo=batchNo.substring(batchNo.lastIndexOf(".",batchNo.lastIndexOf(".")-1)+1);
            record.setBatchNo(batchNo);
            record.setdeviceCode(csmConsumaUse.getDeviceCode());
            record.setdeviceName(csmConsumaUse.getDeviceName());
            record.setReplaceTime(csmConsumaUse.getReplaceTime());
            record.setNickName(SecurityUtils.getUsername());
            record.setSpecs(csmConsumaUse.getSpecs());
            replaceRecordService.insertCsmReplaceRecord(record);
        }

        //新增一条数据
        return toAjax(csmConsumaUseService.insertCsmConsumaUse(csmConsumaUse));
    }

    /**
     * 修改在用备件
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:edit')")
    @Log(title = "在用备件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CsmConsumaUse csmConsumaUse)
    {

        csmConsumaUse.setRepairNumber(StringUtils.isNotNull(csmConsumaUse.getRepairNumber()) ? csmConsumaUse.getRepairNumber() + 1 : 1);

        //增加一条修复记录
        CsmRepairRecord record = new CsmRepairRecord();
        record.setConsumaCode(csmConsumaUse.getConsuma().getConsumaCode());
        record.setConsumaName(csmConsumaUse.getConsuma().getConsumaName());
        record.setBatchNo(csmConsumaUse.getbatchNo());
        record.setNickName(SecurityUtils.getUsername());
        record.setSpecs(csmConsumaUse.getConsuma().getSpecs());
        Date nowDate = DateUtils.getNowDate();
        record.setRepairTime(nowDate);
        record.setExpandTime(csmConsumaUse.getExpandTime());
        repairRecordService.insertCsmRepairRecord(record);

        return toAjax(csmConsumaUseService.updateCsmConsumaUse(csmConsumaUse));
    }

    /**
     * 删除在用备件
     */
    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:remove')")
    @Log(title = "在用备件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{consumaUseIds}")
    public AjaxResult remove(@PathVariable Long[] consumaUseIds)
    {
        return toAjax(csmConsumaUseService.deleteCsmConsumaUseByConsumaUseIds(consumaUseIds));
    }

    /**
     * 解析备件批次号
     *
     * 示例 M11X.0003.CH-001.220725.001
     */

    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:query')")
    @GetMapping(value = "/resolving/{batchNo}")
    public AjaxResult resolving(@PathVariable("batchNo") String batchNo)
    {
        String consumaCode = batchNo.substring(0,batchNo.indexOf(".",batchNo.indexOf(".")+1)); //获取第二个，前面所有的字符 M11X.0003


         // 获取第三个，后面所有的字符 220725.001
       // String realBatchNo=batchNo.substring(batchNo.lastIndexOf(".",batchNo.lastIndexOf(".")-1)+1);

        if("NOT_UNIQUE".equals(csmConsumaUseService.checkBatchNoUnique(batchNo))){
            return AjaxResult.error("该备件已被使用");
        }

        CsmConsuma csmConsuma = consumaService.selectConsumaByCode(consumaCode);
        if(StringUtils.isNull(csmConsuma)){
            return AjaxResult.error("备件档案未维护");
        }
        List<CsmConsumaLine> consumaLineList = consumaLineService.selectLineByConsumaId(csmConsuma.getConsumaId());
        Map<String, Object> result = new HashMap<>(3);
        result.put("consuma", csmConsuma);
        result.put("consumaLine", consumaLineList);
       // 返回备件信息
        return AjaxResult.success(result);
    }


    /**
     * 获取备件信息
     *
     *
     */

    @PreAuthorize("@ss.hasPermi('mes:csm:consumause:query')")
    @GetMapping(value = "/getConsumauseByBatchNo/{batchNo}")
    public AjaxResult getConsumauseByBatchNo(@PathVariable("batchNo") String batchNo)
    {
        String consumaCode = batchNo.substring(0,batchNo.indexOf(".",batchNo.indexOf(".")+1)); //获取第二个，前面所有的字符 M11X.0003
        CsmConsuma csmConsuma = consumaService.selectConsumaByCode(consumaCode);
        if(StringUtils.isNull(csmConsuma)){
            return AjaxResult.error("备件档案未维护");
        }

        CsmConsumaUse csmConsumaUse = csmConsumaUseService.selectConsumaUseByBatchNo(batchNo);
        if(StringUtils.isNull(csmConsumaUse)){
            return AjaxResult.error("该备件未被使用");
        }
        // 返回备件信息
        return AjaxResult.success(csmConsumaUse);
    }
}
