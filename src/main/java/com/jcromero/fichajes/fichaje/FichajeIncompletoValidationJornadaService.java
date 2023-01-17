package com.jcromero.fichajes.fichaje;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
class FichajeIncompletoValidationJornadaService implements ValidationJornadaService {
    private static final String CODE = "001";

    @Override
    public Optional<Alerta> validar(Jornada jornada) {
        if (jornada.isCompleta()) {
            return Optional.empty();
        } else {
            String mensaje = String.format("Jornada %1$s incompleta.", jornada.getFecha().toString());
            return Optional.of(new Alerta(CODE, mensaje));
        }       
    }

}
