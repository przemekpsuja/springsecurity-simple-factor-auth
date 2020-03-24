package com.example.springsecuritysimplefactorauth;

import com.example.springsecuritysimplefactorauth.model.AppUser;
import com.example.springsecuritysimplefactorauth.repository.AppUserRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    public Start(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        AppUser userJan = new AppUser();
        userJan.setUsername("Jan");
        userJan.setPassword(passwordEncoder.encode("Jan123"));
        userJan.setRole("ROLE_ADMIN");
        userJan.setEnable(true);

        AppUser userAndrew = new AppUser();
        userAndrew.setUsername("Andrew");
        userAndrew.setPassword(passwordEncoder.encode("Andrew123"));
        userAndrew.setRole("ROLE_USER");
        userAndrew.setEnable(true);

        appUserRepo.save(userJan);
        appUserRepo.save(userAndrew);
    }
}
