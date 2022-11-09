package com.qygx.mes.quato.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.qygx.common.core.domain.entity.SysUser;
import com.qygx.framework.emq.EmqClient;
import org.eclipse.paho.mqttv5.common.MqttException;
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
import com.qygx.mes.quato.domain.TbQuato;
import com.qygx.mes.quato.service.ITbQuatoService;
import com.qygx.common.utils.poi.ExcelUtil;
import com.qygx.common.core.page.TableDataInfo;

/**
 * 指标管理Controller
 *
 * @author qygx
 * @date 2022-10-18
 */
@RestController
@RequestMapping("/mes/quato/quato")
public class TbQuatoController extends BaseController
{
    @Autowired
    private ITbQuatoService tbQuatoService;


    @Autowired
    private EmqClient emqClient;

    /**
     * 查询指标管理列表
     */
    @PreAuthorize("@ss.hasPermi('mes:quato:quato:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbQuato tbQuato)
    {
        startPage();
        List<TbQuato> list = tbQuatoService.selectTbQuatoList(tbQuato);
        return getDataTable(list);
    }

    /**
     * 导出指标管理列表
     */
    @PreAuthorize("@ss.hasPermi('mes:quato:quato:export')")
    @Log(title = "指标管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbQuato tbQuato)
    {
        List<TbQuato> list = tbQuatoService.selectTbQuatoList(tbQuato);
        ExcelUtil<TbQuato> util = new ExcelUtil<TbQuato>(TbQuato.class);
        util.exportExcel(response, list, "指标管理数据");
    }

    /**
     * 获取指标管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:quato:quato:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tbQuatoService.selectTbQuatoById(id));
    }

    /**
     * 新增指标管理
     */
    @PreAuthorize("@ss.hasPermi('mes:quato:quato:add')")
    @Log(title = "指标管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbQuato tbQuato)
    {
        //订阅主题
        try {
            emqClient.subscribe("$queue/"+tbQuato.getSubject(),1);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return toAjax(tbQuatoService.insertTbQuato(tbQuato));
    }

    /**
     * 修改指标管理
     */
    @PreAuthorize("@ss.hasPermi('mes:quato:quato:edit')")
    @Log(title = "指标管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbQuato tbQuato)
    {
        return toAjax(tbQuatoService.updateTbQuato(tbQuato));
    }

    /**
     * 删除指标管理
     */
    @PreAuthorize("@ss.hasPermi('mes:quato:quato:remove')")
    @Log(title = "指标管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbQuatoService.deleteTbQuatoByIds(ids));
    }


    /**
     * 获取指标选项
     */
    @PreAuthorize("@ss.hasPermi('mes:quato:quato:query')")
    @GetMapping("/getQuatoOptions")
    public AjaxResult getQuatoOptions()
    {
        AjaxResult ajax = AjaxResult.success();
        TbQuato tbQuato = new TbQuato();
        List<TbQuato> quatoList = tbQuatoService.selectTbQuatoList(tbQuato);
        ajax.put("data",quatoList);
        return ajax;
    }
}
