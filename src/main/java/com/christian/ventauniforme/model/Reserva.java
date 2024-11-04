package com.christian.ventauniforme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "reservas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long idPersona;
    private Long total;
    private Date fechaEntrega;
    private boolean entregado;
    private Date fechaReserva;

    @PrePersist
    private void prePersist(){
        fechaReserva=new Date();
        entregado=false;
    }

}
