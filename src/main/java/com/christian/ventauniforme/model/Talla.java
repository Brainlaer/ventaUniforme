package com.christian.ventauniforme.model;

import com.christian.ventauniforme.dto.VestimentaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tallas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Talla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String talla;
    private int unidades;
    private String vestimentaId;
}
