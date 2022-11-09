package com.qygx.mes.email.service;

import com.qygx.common.constant.Constants;
import com.qygx.common.core.domain.AjaxResult;
import com.qygx.common.utils.StringUtils;
import com.qygx.mes.email.domail.EmailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    JavaMailSender mailSender;



    public AjaxResult sendSimpleMail(EmailEntity emailEntity) {

        try {
            MimeMessage mailMessage=mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mailMessage,true, Constants.UTF8);
            //设置寄件人
            helper.setFrom(username);
            //设置收件人
            helper.setTo(emailEntity.getSendTo());
            // 设置抄送
            //helper.setBcc(new InternetAddress("...@163.com","接收人","utf-8"));

            //设置邮件标题
            helper.setSubject(emailEntity.getTitle());
            //设置邮件内容
            helper.setText(emailEntity.getDetail());
            //有附件就上传附件
            if (!StringUtils.isEmpty(emailEntity.getFileList())){
                String[] fileList = emailEntity.getFileList().split(",");
                for (String filePath:fileList) {
                    URL url = new URL(filePath);
                    DataSource dataSource=new URLDataSource(url);
                    //获取文件名，我上传到阿里云的文件名格式是这样的
                    // https://yourAliYunOssBucket.oss-cn-		beijing.aliyuncs.com/2022/03/16/91801c74d697456c8cbd701fadf0e615.jpg
                    String fileName=filePath.substring(filePath.lastIndexOf("/")+1);
                    helper.addAttachment(fileName,dataSource);
                }
            }
            //发送邮件
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.success("邮件发送成功");
    }


}

