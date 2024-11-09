package com.andymartinez1.Hotel.services.auth;

import com.andymartinez1.Hotel.dto.SignupRequestDTO;
import com.andymartinez1.Hotel.dto.UserDTO;
import com.andymartinez1.Hotel.entity.User;
import com.andymartinez1.Hotel.enums.UserRole;
import com.andymartinez1.Hotel.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);

        if (adminAccount.isEmpty()) {
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin account created successfully");
        } else {
            System.out.println("Admin account already exists");
        }
    }

    public UserDTO createUser(SignupRequestDTO signupRequestDTO){
        if(userRepository.findFirstByEmail(signupRequestDTO.getEmail()).isPresent()){
            throw new EntityExistsException("User Already Present With Email: " + signupRequestDTO.getEmail());
        }

        User user = new User();
        user.setEmail(signupRequestDTO.getEmail());
        user.setName(signupRequestDTO.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser.getUserDTO();
    }

}
