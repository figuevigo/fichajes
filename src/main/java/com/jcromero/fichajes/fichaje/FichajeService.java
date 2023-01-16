package com.jcromero.fichajes.fichaje;

import java.time.LocalDate;
import java.util.List;

/**
 * Servicio de gestión de fichajes
 * @author jromero
 *
 */
public interface FichajeService {
    
    /**
     * Añade un nuevo fichaje
     * @param fichaje Datos del fichaje
     * @return Id del nuevo fichaje
     */
    Long addFichaje(FichajeDTO fichaje) throws FichajeException ;

    /**
     * Devuelve todos los fichajes sin ningún tipo de filtro por empleado o servicio
     * @return
     */
    List<FichajeDTO> getAllFichajes();

    /**
     * Devuelve todos los fichajes de un empleado dado
     * @param empleado
     * @return
     */
    List<FichajeDTO> getAllFichajesEmpleado(String empleado);
    
    /**
     * Devuelve todos los fichajes de la semana en curso de un empleado dado
     * @param empleado
     * @return
     */
    Semana getFichajesSemanaEnCursoEmpleado(String empleado);    
    
    /**
     * Devuelve todos los fichajes de una semana de un empleado dado
     * @param empleado Id. empleado para el que queremos consultar los fichajes de la semana
     * @param diaSemana Fecha perteneciente a la semana que queremos consultar
     * @return Semana que contiene todos los fichajes de la misma de un empleaod organizadas por Jornadas
     */
    Semana getFichajesSemanaEmpleado(String empleado, LocalDate diaSemana);       
}
