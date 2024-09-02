package com.christian.ventauniforme.dto;

import com.christian.ventauniforme.model.Talla;
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
public class ReservaDto {
    private Long idPersona;
    private List<Talla> tallaDtoList;
    private Long total;
    private Date fechaEntrega;
}
