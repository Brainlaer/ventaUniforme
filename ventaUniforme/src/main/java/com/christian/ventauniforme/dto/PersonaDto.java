package com.christian.ventauniforme.dto;

import com.christian.ventauniforme.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonaDto {
    private String nombre;
    private String apellido;
    private Long celular;
    private String correo;
    private String password;
    private Rol rol;
}
