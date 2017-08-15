package com.pm.framework.util;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.pm.framework.bean.MailConfig;
import com.pm.framework.bean.MailLog;
import com.pm.framework.bean.UploadFile;

/**
 * 发送邮件工具类
 * 
 * @author FYL
 */

public class MailUtil {
    private static Logger LOGGER = Logger.getLogger(MailUtil.class);
    private final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static Executor executor = Executors.newFixedThreadPool(10);
    private static Properties mailProp = new Properties();

    /**
     * 发送邮件
     * 
     * @param mailConfig 邮件配置
     * @param mailLog 邮件内容
     * @return true or false
     */
    public static void sendMail(final MailConfig mailConfig, final MailLog mailLog) {
        if (mailConfig != null && mailLog != null) {
            mailProp.clear();
            sendMailPop(mailConfig, mailLog);
        }
    }

    /**
     * 发送邮件类型为POP3
     * 
     * @param mailConfig
     * @param mailLog
     */
    public static void sendMailPop(final MailConfig mailConfig, final MailLog mailLog) {
        if (mailConfig.getMailSsl()) {
            mailProp.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            mailProp.setProperty("mail.smtp.socketFactory.fallback", "false");
            mailProp.setProperty("mail.smtp.socketFactory.port", mailConfig.getMailPort() + "");
        }
        mailProp.setProperty("mail.smtp.host", mailConfig.getMailSmtp());
        mailProp.setProperty("mail.smtp.port", mailConfig.getMailPort());
        if (mailConfig.getMailAuth()) {
            mailProp.setProperty("mail.smtp.auth", "true");
        }
        mailProp.setProperty("mail.smtp.user", mailConfig.getMailName());
        mailProp.setProperty("mail.smtp.password", mailConfig.getMailPwd());
        mailProp.setProperty("mail.stmp.timeout", mailConfig.getMailTimeout());
        Runnable task = new Runnable() {
            public void run() {
                Session session = Session.getDefaultInstance(mailProp, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailConfig.getMailName(), mailConfig.getMailPwd());
                    }
                });
                try {
                    MimeMessage msg = new MimeMessage(session);
                    msg.setFrom(new InternetAddress(mailConfig.getMailName()));
                    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailLog.getMailToId()));
                    msg.setSubject(mailLog.getMailSubject());
                    Multipart mp = new MimeMultipart();
                    MimeBodyPart mbp1 = new MimeBodyPart();
                    mbp1.setContent(mailLog.getMailBody(), "text/html;charset=gbk");
                    mp.addBodyPart(mbp1);
                    if (mailLog.getMailAttFiles() != null && mailLog.getMailAttFiles().size() > 0) {
                        MimeBodyPart attach = null;
                        for (UploadFile uploadFile : mailLog.getMailAttFiles()) {
                            attach = new MimeBodyPart();
                            try {
                                attach.attachFile(uploadFile.getFilePathName());
                                mp.addBodyPart(attach);
                            } catch (Exception e) {
                                LOGGER.error(e, e);
                            }
                        }
                    }
                    msg.setContent(mp);
                    msg.setSentDate(new Date());
                    Transport.send(msg);
                } catch (Exception e) {
                    LOGGER.error(e, e);
                }
            }
        };
        executor.execute(task);
    }
}
