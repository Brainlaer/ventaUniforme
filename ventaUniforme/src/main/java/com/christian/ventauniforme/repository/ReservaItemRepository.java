package com.christian.ventauniforme.repository;


import com.christian.ventauniforme.model.Reserva;
import com.christian.ventauniforme.model.ReservaItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaItemRepository extends JpaRepository<ReservaItem,String> {
    List<ReservaItem> findByidReserva(Reserva reserva);
}
