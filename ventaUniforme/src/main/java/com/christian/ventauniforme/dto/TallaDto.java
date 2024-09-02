package com.christian.ventauniforme.dto;

import com.christian.ventauniforme.model.Vestimenta;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TallaDto {
    private Long id;
    private String talla;
    private int unidades;
}
