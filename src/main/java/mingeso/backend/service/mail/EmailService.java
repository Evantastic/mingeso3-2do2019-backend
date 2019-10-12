package mingeso.backend.service.mail;

import com.sun.tools.jdeprscan.scan.Scan;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.*;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class EmailService {

  private final JavaMailSender emailSender;

  public void sendEmailWithTemplate(String to, String reserveId,
                                    String nameClient) throws FileNotFoundException, MessagingException {
    Scanner scanner = null;
    try {
      String path = "src/main/resources/index.html";
      scanner = new Scanner(new File(path));
      String htmlScript = scanner.useDelimiter("\\A").next();
      String htmlFinalScript = htmlScript.replace("%{NOMBRE-CLIENTE}%", nameClient);
      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
      helper.setText(htmlFinalScript, true);
      helper.setTo(to);
      helper.setSubject("Habbo Hotel Reserva: " + reserveId);
      emailSender.send(message);
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
  }
}
