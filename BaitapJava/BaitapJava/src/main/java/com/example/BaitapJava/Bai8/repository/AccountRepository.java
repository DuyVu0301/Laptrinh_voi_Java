package com.example.BaitapJava.Bai8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.BaitapJava.Bai8.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM Account a WHERE a.login_name = :login_name")
    Optional<Account> findByLoginName(String login_name);
}