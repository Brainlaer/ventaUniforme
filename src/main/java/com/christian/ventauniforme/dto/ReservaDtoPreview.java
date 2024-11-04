package com.christian.ventauniforme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDtoPreview {
    private String id;
    private Long idPersona;
    private String correoPersona;
    private List<ReservaDtoPreviewItem> reservaDtoPreviewItems;
    private Date fechaEntrega;
    private Date fechaReserva;
    private boolean entregado;

}
