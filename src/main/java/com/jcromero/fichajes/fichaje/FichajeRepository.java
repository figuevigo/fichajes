package com.jcromero.fichajes.fichaje;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


interface FichajeRepository extends JpaRepository<Fichaje, Long>  {

    @Query("select f from Fichaje f where f.employeeId = :empleado and f.date between :fechaJornadaInicio and :fechaJornadaFin order by f.date ASC")
    List<Fichaje> findByEmployeeIdAndDate(String empleado, Instant fechaJornadaInicio, Instant fechaJornadaFin);

}
