package com.qygx.mes.md.controller;

import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.mes.md.domain.MdWorkstation;
import com.qygx.mes.md.service.IMdWorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mobile/md/workstation")
public class MdWorkstationMobController extends BaseController {

    @Autowired
    private IMdWorkstationService mdWorkstationService;

    @GetMapping("/getWorkstationList")
    public AjaxResult getWorkstationList(MdWorkstation mdWorkstation){
        List<MdWorkstation> list = mdWorkstationService.selectMdWorkstationList(mdWorkstation);
        return AjaxResult.success(list);
    }

}
