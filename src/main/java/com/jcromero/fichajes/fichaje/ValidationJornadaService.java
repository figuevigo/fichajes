package com.jcromero.fichajes.fichaje;

import java.util.Optional;

/**
 * Interfaz a implementar por los servicios de validación de lógica de negocio de una Jornada
 * 
 * @author jromero
 *
 */
interface ValidationJornadaService {
    Optional<Alerta> validar(Jornada jornada);
}
