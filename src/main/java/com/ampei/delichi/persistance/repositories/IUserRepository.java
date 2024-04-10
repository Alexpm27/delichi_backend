package com.ampei.delichi.persistance.repositories;

import com.ampei.delichi.persistance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrPhoneNumber(String email, Long phoneNumber);

}