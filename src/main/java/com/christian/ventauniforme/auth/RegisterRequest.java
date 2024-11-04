package com.christian.ventauniforme.auth;

import com.christian.ventauniforme.model.Rol;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Long id;
    private String nombre;
    private String apellido;
    private Long celular;
    private String correo;
    private String password;
    private Rol rol;

}
