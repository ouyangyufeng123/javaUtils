package com.yf.mail;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送类
 * @author ouyangyufeng
 * @date 2019/2/1
 */
public class SendMail {
    /**
     * 发送人邮箱
     * PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码
     * （有的邮箱称为“授权码”）,对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
     */
    public static String myEmailAccount = "ou_1415190020@163.com";
    /**
     * 发送人授权码
     */
    public static String myEmailPassword = "ou1415190020";
    /**
     * 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.163.com
     */
    public static String myEmailSMTPHost = "smtp.163.com";
    /**
     * 收件人邮箱（替换为自己知道的有效邮箱）
     */
    public static String receiveMailAccount = "375755319@qq.com";
    /**
     * 收件人名称
     */
    public static String Addressee = "张三";
    /**
     * 邮件主题
     */
    public static String Subject = "<h2>haha</h2>";
    /**
     * 邮件正文（可以使用html标签）
     */
    public static String Content = "<h2>haha！收到没</h2></br>";

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session：和服务器交互的会话
     * @param sendMail：发件人邮箱
     * @param receiveMail：收件人邮箱
     * @param subject：邮件主题
     * @param content：邮件正文
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String subject, String content) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "YF", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        //ps: 有可能被当成垃圾邮件退回(抄送自己即可解决)
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ou_1415190020@163.com", "YF", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, Addressee, "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("<h2>haha</h2>", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("<h2>haha！收到没</h2></br>", "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }


    public static void main(String[] args) throws Exception {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();

        // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");

        // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);

        // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);

        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, Subject, Content);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }
}
