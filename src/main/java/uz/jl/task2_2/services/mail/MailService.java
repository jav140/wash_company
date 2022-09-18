package uz.jl.task2_2.services.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.jl.task2_2.dtos.MessageDTO;
import uz.jl.task2_2.dtos.auth.AuthUserDTO;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;



@Service
@RequiredArgsConstructor
public class MailService {


    private final Configuration configuration;
    private final JavaMailSender javaMailSender;


    @Async
    public void sendEmail(AuthUserDTO user, String activationLink) throws MessagingException, TemplateException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Activation link for Dummies.uz");
        helper.setTo(user.getEmail());
        Map<String, Object> model = Map.of(
                "username", user.getUsername(),
                "activation_link", activationLink
        );
        String emailContent = getEmailContent(model, "activation.ftlh");
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    private String getEmailContent(Map<String, Object> model, String templateName) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Template template = configuration.getTemplate(templateName);
        template.process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }


    @Async
    public void sendMessage(MessageDTO dto) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setSubject("Message for particular email");
        mimeMessageHelper.setText(dto.getMessage());
        mimeMessageHelper.setTo(dto.getToEmail());
        mimeMessageHelper.setFrom(dto.getContact());

        javaMailSender.send(mimeMessage);

    }
}

