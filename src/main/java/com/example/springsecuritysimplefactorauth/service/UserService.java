package com.example.springsecuritysimplefactorauth.service;

import com.example.springsecuritysimplefactorauth.model.AppUser;
import com.example.springsecuritysimplefactorauth.model.Token;
import com.example.springsecuritysimplefactorauth.repository.AppUserRepo;
import com.example.springsecuritysimplefactorauth.repository.TokenRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private TokenRepo tokenRepo;
    private MailService mailService;
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(TokenRepo tokenRepo, MailService mailService, AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(AppUser appUser){
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        appUserRepo.save(appUser);
        sendToken(appUser);
    }

    private void sendToken(AppUser appUser){
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setAppUser(appUser);
        tokenRepo.save(token);

        String confirmUrl = "http://localhost:8080/token?value=" + tokenValue;

        try {
            mailService.sendMail(appUser.getUsername(), "Potwierdź maila!", confirmUrl, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
