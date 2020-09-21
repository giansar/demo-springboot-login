package id.giansar.demo.repository;

import id.giansar.demo.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByResetPasswordToken(String token);
}
