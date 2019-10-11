package mingeso.backend.service.mail;


import com.google.common.io.Files;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


@Service
public class EmailService {


    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) throws FileNotFoundException, MessagingException {

        String path ="/home/gabriel/Escritorio/Back-Mingeso/mingeso3-2do2019-backend/src/main/java/mingeso/backend/service/mail/htmlMailResponse/index.html";
        String htmlScript = new Scanner(new File(path)).useDelimiter("\\A").next();

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");
        helper.setText(htmlScript,true);
        helper.setTo(to);
        helper.setSubject(subject);
        emailSender.send(message);
    }
}
