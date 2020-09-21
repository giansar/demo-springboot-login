package id.giansar.demo.controller;

import id.giansar.demo.dto.EmailDto;
import id.giansar.demo.dto.NewPasswordDto;
import id.giansar.demo.dto.SignInDto;
import id.giansar.demo.entity.Member;
import id.giansar.demo.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class DemoWebController {
    private final MemberService memberService;

    public DemoWebController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Member member = memberService.getMemberByEmail(user.getUsername());
        model.addAttribute("name", member.getFullName());
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        SignInDto signInDto = new SignInDto();
        model.addAttribute("signIn", signInDto);
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationForm() {
        return "registration";
    }

    @GetMapping("/support/forgot-password")
    public String getForgotPasswordForm(Model model) {
        model.addAttribute("myEmail", new EmailDto());
        return "forgot-password";
    }

    @PostMapping("/support/forgot-password")
    public String requestResetPasswordLink(Model model, @ModelAttribute("myEmail") EmailDto emailDto) {
        model.addAttribute("isSent", memberService.sendResetPasswordLink(emailDto.getEmail()));
        return "forgot-password-after-submitted";
    }

    @GetMapping("/support/new-password/{token}")
    public String getNewPasswordForm(@PathVariable("token") String token, Model model) {
        if (memberService.isValidTokenResetPassword(token)) {
            NewPasswordDto newPasswordDto = new NewPasswordDto();
            newPasswordDto.setToken(token);
            model.addAttribute("myModel", newPasswordDto);
            return "new-password";
        }
        return "redirect:/support/forgot-password";
    }

    @PostMapping("/support/new-password")
    public String setNewPassword(Model model, @ModelAttribute("myModel") NewPasswordDto newPasswordDto) {
        if (!memberService.setNewPassword(newPasswordDto)) {
            model.addAttribute("myEmail", new EmailDto());
            return "redirect:/support/forgot-password";
        }
        return "redirect:/login";
    }

}
