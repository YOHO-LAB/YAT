package cn.yat.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class JavaMailUtil {
    @Value("${mail.transport.protocol}")
    private String mail_protocol;
    @Value("${mail.smtp.host}")
    private String mail_host;
    @Value("${mail.smtp.port}")
    private String mail_port;
    @Value("${mail.smtp.auth}")
    private String mail_auth;
    @Value("${mail.smtp.timeout}")
    private String mail_timeout;
    @Value("${mail.sender.username}")
    private String mail_sender_username;
    @Value("${mail.sender.password}")
    private String mail_sender_password;
    @Value("${mail.receiver.username.to}")
    private String mail_receiver_username_to;
    @Value("${mail.receiver.username.cc}")
    private String mail_receiver_username_cc;

    public void sendCiMail(String emailMsg , String subject, String debugEmail) throws Exception{
//        String emailMsg = buildMsg(runId,passed,skipped,failed,total,teamSummaryArr);
        if(debugEmail == null){
            sendCiMailHtml(mail_receiver_username_to,mail_receiver_username_cc,subject,emailMsg);
        }else{
            sendCiMailHtml(debugEmail,null,subject,emailMsg);
        }

    }

    public void sendCiMailHtml(String mail_receiver_username_to ,String mail_receiver_username_cc, String subject ,String emailMsg) throws Exception{

        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", mail_protocol);   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", mail_host);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", mail_auth);            // 需要请求认证

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。

        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        props.setProperty("mail.smtp.port", mail_port);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", mail_port);


        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
//        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

        // 3. 创建一封邮件
        // 3.1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 3.2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(mail_sender_username, "YAT自动化测试平台", "UTF-8"));
        // 3.3. To: 收件人（可以增加多个收件人、抄送、密送）
        if(mail_receiver_username_to != null){
            mail_receiver_username_to = mail_receiver_username_to.trim();
            for(String s : mail_receiver_username_to.split(",")){
                s = s.trim();
                if(!s.equals("")){
                    message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(s));
                }
            }
        }
        if(mail_receiver_username_cc != null){
            mail_receiver_username_cc = mail_receiver_username_cc.trim();
            if(!mail_receiver_username_cc.equals("")){
                for(String s : mail_receiver_username_cc.split(",")){
                    s = s.trim();
                    if(!s.equals("")){
                        message.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(s));
                    }
                }
            }
        }
//        //    To: 增加收件人（可选）
//        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
//        //    Cc: 抄送（可选）
//        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
//        //    Bcc: 密送（可选）
//        message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));
        // 3.4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(subject, "UTF-8");
        // 邮件内容框架
        // 3.5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(emailMsg, "text/html;charset=UTF-8");
        // 3.6. 设置发件时间
//        message.setSentDate(new Date());
        // 3.7. 保存设置
        message.saveChanges();

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //
        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
        transport.connect(mail_sender_username, mail_sender_password);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }
}
