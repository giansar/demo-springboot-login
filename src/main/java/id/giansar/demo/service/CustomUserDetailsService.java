package id.giansar.demo.service;

import id.giansar.demo.entity.Member;
import id.giansar.demo.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmail(s);
        if (!member.isPresent()) {
            throw new UsernameNotFoundException(s);
        }
        return new User(member.get().getEmail(), member.get().getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
