package id.giansar.demo.service;

import id.giansar.demo.entity.Member;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailContentBuilderService {
    private final TemplateEngine templateEngine;

    public EmailContentBuilderService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildResetPasswordContent(Member member) {
        Context context = new Context();
        context.setVariable("fullName", member.getFullName());
        context.setVariable("token", member.getResetPasswordToken());
        return templateEngine.process("reset-password-email-content", context);
    }
}
