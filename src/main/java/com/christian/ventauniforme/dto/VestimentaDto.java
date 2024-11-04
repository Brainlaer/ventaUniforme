package com.christian.ventauniforme.dto;

import com.christian.ventauniforme.model.Talla;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VestimentaDto {
    private String imagen;
    private String titulo;
    private String descripcion;
    private Long costo;
    private String categoria;
}
