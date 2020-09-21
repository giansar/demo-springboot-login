package id.giansar.demo.entity;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    private Boolean isAgreeTerms;

    @Column(unique = true)
    private String resetPasswordToken;

    public Member() {
    }

    public Member(String fullName, String email, String password, Boolean isAgreeTerms) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.isAgreeTerms = isAgreeTerms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAgreeTerms() {
        return isAgreeTerms;
    }

    public void setAgreeTerms(Boolean agreeTerm) {
        isAgreeTerms = agreeTerm;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }
}
