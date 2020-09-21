package id.giansar.demo.controller;

import id.giansar.demo.dto.MemberDto;
import id.giansar.demo.entity.Member;
import id.giansar.demo.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public DemoRestController(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/register")
    public Member registerNewMember(@RequestBody MemberDto memberDto) {
        Member member = new Member();
        member.setFullName(memberDto.getFullName());
        member.setEmail(memberDto.getEmail());
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        member.setAgreeTerms(memberDto.getAgreeTerms());
        memberRepository.save(member);
        return member;
    }
}
