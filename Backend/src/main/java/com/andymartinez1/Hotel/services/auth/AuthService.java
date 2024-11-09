package com.andymartinez1.Hotel.services.auth;

import com.andymartinez1.Hotel.dto.SignupRequestDTO;
import com.andymartinez1.Hotel.dto.UserDTO;

public interface AuthService {

    UserDTO createUser(SignupRequestDTO signupRequestDTO);

}
