package com.jcromero.fichajes.fichaje;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
class HoraEntradaMinimaValidationJornadaService implements ValidationJornadaService {
    private static final String CODE = "003";
    private static final LocalTime HORA_ENTRADA_MINIMA = LocalTime.of(8, 0);
    private static final LocalTime HORA_ENTRADA_MINIMA_VIERNES = LocalTime.of(7, 0);

    @Override
    public Optional<Alerta> validar(Jornada jornada) {
        LocalTime horaMinima = HORA_ENTRADA_MINIMA;
        if (jornada.getFecha().getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            horaMinima = HORA_ENTRADA_MINIMA_VIERNES;
        }
        if ( (jornada.getHoraInicio() == null) || (horaMinima.compareTo(jornada.getHoraInicio()) <= 0)) {
            return Optional.empty();
        } else {
            String mensaje = String.format("La hora de inicio de la Jornada %1$s es anterior a la hora mínima de fichaje.", jornada.getFecha().toString());
            return Optional.of(new Alerta(CODE, mensaje));
        }       
    }

}
