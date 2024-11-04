package com.christian.ventauniforme.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reserva-items")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String titulo;
    private String imagen;
    private String talla;
    private Long Costo;
    private int unidades;

    @ManyToOne
    @JoinColumn(name = "id-reserva")
    private Reserva idReserva;
}