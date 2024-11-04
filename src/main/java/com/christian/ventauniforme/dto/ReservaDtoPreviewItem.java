package com.christian.ventauniforme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDtoPreviewItem {
    private String id;
    private String imagen;
    private String titulo;
    private String talla;
    private String unidades;
}
