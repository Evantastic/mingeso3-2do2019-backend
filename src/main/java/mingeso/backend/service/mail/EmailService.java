package mingeso.backend.service.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Scanner;

@AllArgsConstructor
public class EmailService {

  private final JavaMailSender emailSender;
  private static final String PATH = "";

  public void sendEmailWithTemplate(String to, String reserveId,
                                    String nameClient){
    try (Scanner scanner = new Scanner(new File(PATH))) {
      String htmlScript = scanner.useDelimiter("\\A").next();
      String htmlFinalScript = htmlScript.replace("%{NOMBRE-CLIENTE}%", nameClient);
      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
      helper.setText(htmlFinalScript, true);
      helper.setTo(to);
      helper.setSubject("Habbo Hotel Reserva: " + reserveId);
      emailSender.send(message);
    } catch (Exception ignored) {
      String a = "error";
    }
  }
}
