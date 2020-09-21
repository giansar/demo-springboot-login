package id.giansar.demo.service;

import id.giansar.demo.dto.NewPasswordDto;
import id.giansar.demo.entity.Member;
import id.giansar.demo.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final Logger logger = LoggerFactory.getLogger(MemberService.class);
    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public Member getMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isEmpty()) {
            return null;
        }
        return member.get();
    }

    public Boolean sendResetPasswordLink(String email) {
        Member member = getMemberByEmail(email);
        if (member == null) {
            return false;
        } else {
            return emailService.sendResetPasswordLink(member);
        }
    }

    public Boolean isValidTokenResetPassword(String token) {
        Optional<Member> member = memberRepository.findByResetPasswordToken(token);
        return member.isPresent();
    }

    public Boolean setNewPassword(NewPasswordDto newPasswordDto) {
        logger.info("newPassword = " + newPasswordDto.getNewPassword());
        logger.info("token = " + newPasswordDto.getToken());
        Optional<Member> member = memberRepository.findByResetPasswordToken(newPasswordDto.getToken());
        if (member.isEmpty()) {
            return false;
        }
        member.get().setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
        memberRepository.save(member.get());
        return true;
    }
}
