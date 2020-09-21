package id.giansar.demo.service;

import id.giansar.demo.entity.Member;
import id.giansar.demo.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final EmailContentBuilderService contentBuilder;
    private final MemberRepository memberRepository;
    private final HelperService helperService;

    public EmailService(JavaMailSender mailSender, EmailContentBuilderService contentBuilder, MemberRepository memberRepository, HelperService helperService) {
        this.mailSender = mailSender;
        this.contentBuilder = contentBuilder;
        this.memberRepository = memberRepository;
        this.helperService = helperService;
    }

    public Boolean sendResetPasswordLink(Member member) {
        member.setResetPasswordToken(helperService.generateRandomString32());
        memberRepository.save(member);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");
            messageHelper.setFrom("giansar.software.demo@gmail.com", "Giansar Software Demo");
            messageHelper.setTo(member.getEmail());
            messageHelper.setSubject("Demo Spring Boot: Your Reset Password Link");
            messageHelper.setText(contentBuilder.buildResetPasswordContent(member), true);
            mailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
