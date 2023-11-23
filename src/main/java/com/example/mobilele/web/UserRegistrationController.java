package com.example.mobilele.web;

import com.example.mobilele.model.dto.ReCaptchaResponseDTO;
import com.example.mobilele.model.dto.UserRegistrationDTO;
import com.example.mobilele.service.ReCaptchaService;
import com.example.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@Controller
public class UserRegistrationController {
    private final UserService userService;
    private final ReCaptchaService reCaptchaService;

    public UserRegistrationController(UserService userService, ReCaptchaService reCaptchaService) {
        this.userService = userService;
        this.reCaptchaService = reCaptchaService;
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(UserRegistrationDTO userRegistrationDTO,
                           @RequestParam("g-recaptcha-response") String reCaptchaResponse) {


        boolean isBot = !reCaptchaService
                .verify(reCaptchaResponse)
                        .map(ReCaptchaResponseDTO::isSuccess)
                                .orElse(false);

        if(isBot) {
            return "redirect:/";
        }
        userService.registerUser(userRegistrationDTO);
        return "redirect:/";
    }



}
