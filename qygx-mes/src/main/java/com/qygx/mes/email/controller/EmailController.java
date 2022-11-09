package com.qygx.mes.email.controller;


import com.qygx.common.annotation.RepeatSubmit;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.mes.email.domail.EmailEntity;
import com.qygx.mes.email.service.EmailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/system/email")
@Api(tags = "邮件服务")
public class EmailController {

    @Autowired
    EmailService emailService;

    @RepeatSubmit()
    @PostMapping("/sendEmail")
    public AjaxResult verifyEmail( @RequestBody EmailEntity emailEntity) {
        return emailService.sendSimpleMail(emailEntity);
    }
}

