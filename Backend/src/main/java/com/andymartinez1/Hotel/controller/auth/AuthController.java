package com.andymartinez1.Hotel.controller.auth;

import com.andymartinez1.Hotel.dto.SignupRequestDTO;
import com.andymartinez1.Hotel.dto.UserDTO;
import com.andymartinez1.Hotel.services.auth.AuthService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequestDTO signupRequestDTO){
        try {
            UserDTO createdUser = authService.createUser(signupRequestDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (EntityExistsException entityExistsException){
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e){
            return new ResponseEntity<>("User not created. Try again later", HttpStatus.BAD_REQUEST);
        }
    }

}
