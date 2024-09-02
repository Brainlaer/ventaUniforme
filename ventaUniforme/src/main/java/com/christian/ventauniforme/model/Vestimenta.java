package com.christian.ventauniforme.model;

import com.christian.ventauniforme.repository.TallaRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vestimentas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vestimenta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String imagen;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false, length = 500)
    private String descripcion;
    @Column(nullable = false)
    private Long costo;
    private int unidades;
    @Column(name = "fecha-insertado")
    private Date fechaInsertado;
    private String categoria;

    @PrePersist
    private void prePersist(){
        fechaInsertado=new Date();
    }

    public int setFixUnidades(int unidades1){
        return unidades=unidades+unidades1;
    }
}
