package com.creativeminds.app.repositories;

import com.creativeminds.app.model.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoDinero, Integer> {
}
