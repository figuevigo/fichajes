package com.jcromero.fichajes.fichaje;

import java.time.Duration;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
class JornadaMaximaValidationJornadaService implements ValidationJornadaService {
    private static final String CODE = "002";
    private static final Duration JORNADA_MAXIMA = Duration.ofHours(10L); //Sustituir por estrucutura de datos con Jornada máxima por día externalizable

    @Override
    public Optional<Alerta> validar(Jornada jornada) {
        if (JORNADA_MAXIMA.compareTo(jornada.getTiempoTrabajoEfectivo()) > 0) {
            return Optional.empty();
        } else {
            String mensaje = String.format("Jornada %1$s excede joramda máxima.", jornada.getFecha().toString());
            return Optional.of(new Alerta(CODE, mensaje));
        }       
    }

}
