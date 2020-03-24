package com.example.springsecuritysimplefactorauth.controller;

import com.example.springsecuritysimplefactorauth.model.AppUser;
import com.example.springsecuritysimplefactorauth.model.Token;
import com.example.springsecuritysimplefactorauth.repository.AppUserRepo;
import com.example.springsecuritysimplefactorauth.repository.TokenRepo;
import com.example.springsecuritysimplefactorauth.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collection;

@Controller
public class UserController {

    private UserService userService;
    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;

    public UserController(UserService userService, AppUserRepo appUserRepo, TokenRepo tokenRepo) {
        this.userService = userService;
        this.appUserRepo = appUserRepo;
        this.tokenRepo = tokenRepo;
    }

    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities", authorities);
        model.addAttribute("details", details);
        return "hello";
    }

    @GetMapping("/sign-up")
    public String signup(Model model) {
        model.addAttribute("user", new AppUser());
        return "sign-up";
    }

    @PostMapping("/register")
    public String register(AppUser appUser){
        userService.addUser(appUser);
        return "sign-up";
    }

    @GetMapping("/token")
    public String signup(@RequestParam String value){
        Token byValue = tokenRepo.findByValue(value);
        AppUser appUser = byValue.getAppUser();
        appUser.setEnable(true);
        appUserRepo.save(appUser);
        return "hello";
    }


}
