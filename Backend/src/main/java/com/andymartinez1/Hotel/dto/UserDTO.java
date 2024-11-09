package com.andymartinez1.Hotel.dto;

import com.andymartinez1.Hotel.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;

}
