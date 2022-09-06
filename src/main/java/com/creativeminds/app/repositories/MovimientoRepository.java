package com.creativeminds.app.repositories;

import com.creativeminds.app.model.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoDinero, Integer> {
    @Query(value = "select* from movimientos where empleado_id= ?1 ", nativeQuery = true")
    public abstract ArrayList<MovimientoDinero> findByEmpleado(Integer id);

    @Query(value = "select* from movimientos where empleado_id in(select id from empleado where empresa_id=?1)", nativeQuery = true")
    public abstract ArrayList<MovimientoDinero> findByEmprea(Integer id);
}
