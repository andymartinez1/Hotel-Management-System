package com.andymartinez1.Hotel.repository;

import com.andymartinez1.Hotel.entity.User;
import com.andymartinez1.Hotel.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    Optional<User> findByUserRole(UserRole userRole);

}
