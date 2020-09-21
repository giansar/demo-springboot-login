package id.giansar.demo.dto;

public class MemberDto {
    private String fullName;
    private String email;
    private String password;
    private Boolean isAgreeTerms;

    public MemberDto() {
    }

    public MemberDto(String fullName, String email, String password, Boolean isAgreeTerms) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.isAgreeTerms = isAgreeTerms;
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
}
