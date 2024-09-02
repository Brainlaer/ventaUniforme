package com.christian.ventauniforme.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String correo;
    private String password;
}
