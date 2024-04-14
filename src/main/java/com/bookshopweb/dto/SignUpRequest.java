package com.bookshopweb.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String gender;
    private String address;
    private String role;
    private String policy;
}
