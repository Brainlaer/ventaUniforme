package com.christian.ventauniforme.repository;

import com.christian.ventauniforme.model.Talla;
import com.christian.ventauniforme.model.Vestimenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TallaRepository extends JpaRepository<Talla,Long> {
    List<Talla> findByVestimentaId(String vestimentaId);
}
