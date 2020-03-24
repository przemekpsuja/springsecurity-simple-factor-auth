package com.example.springsecuritysimplefactorauth.repository;

import com.example.springsecuritysimplefactorauth.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

    Token findByValue(String value);

}
