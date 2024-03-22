package com.groupchat.websocket.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserSignupDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    private String mobileNo;
    @NotBlank
    @Size(max = 120)
    private String password;
}
