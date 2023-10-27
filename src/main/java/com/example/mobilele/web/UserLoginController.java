package com.example.mobilele.web;


import com.example.mobilele.model.dto.UserLoginDTO;
import com.example.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

//    private final UserService userService;
//
//    public UserLoginController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/users/login")
    public String login() {
       return "auth-login";
    }

    @PostMapping("/users/login-error")
    public String onFailure(
            @ModelAttribute("email") String email,
            Model model) {
        model.addAttribute("email", email);
        model.addAttribute("bad_credentials", "true");
        return "auth-login";
    }


//    @GetMapping("/users/logout")
//    public String logout() {
//
//        userService.logoutUser();
//        return "index";
//    }

//    @PostMapping("/users/login")
//    public String login(UserLoginDTO userLoginDTO) {
//       boolean loginSuccessful = userService.loginUser(userLoginDTO);
//
//       return loginSuccessful ? "index" : "auth-login";
//
//    }
}

