package com.christian.ventauniforme.dto;

import com.christian.ventauniforme.model.Talla;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VestimentaDtoGet {
    private String id;
    private String imagen;
    private String titulo;
    private String descripcion;
    private double costo;
    private int unidades;
    private String categoria;
    private List<TallaDto> tallaList;
}
