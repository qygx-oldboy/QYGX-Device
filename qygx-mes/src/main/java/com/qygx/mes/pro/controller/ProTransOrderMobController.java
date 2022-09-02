package com.qygx.mes.pro.controller;

import com.qygx.common.core.controller.BaseController;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.mes.pro.domain.ProTransOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户信息管理")
@RestController
@RequestMapping("/mobile/pro/transorder")
public class ProTransOrderMobController extends BaseController {

    @ApiOperation("获取流转单清单")
    @GetMapping("/getList")
    public AjaxResult getList(ProTransOrder proTransOrder){
        return AjaxResult.success();
    }

}
