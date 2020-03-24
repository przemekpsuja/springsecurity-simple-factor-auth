package com.example.springsecuritysimplefactorauth.repository;

import com.example.springsecuritysimplefactorauth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

}
