package com.jcromero.fichajes.fichaje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql({"/fichajes_pruebas.sql"})
class FichajeRepositoryTest {
    
    private static final String EMPLEADO = "222222222";   
    private static final Instant FECHA_JORNADA_INICIO = Instant.parse("2023-01-09T00:00:00Z");    
    private static final Instant FECHA_JORNADA_FIN = Instant.parse("2023-01-09T23:59:59.999Z");
    
    @Autowired
    FichajeRepository repository;
    
    @Test
    void dadaUnaFechaYUnEmpleadoDameFichajes() {
        assertNotNull(repository);
        Optional<Fichaje> fichaje = repository.findById(1L);
        assertTrue(fichaje.isPresent());
        assertEquals(RecordType.IN, fichaje.get().getRecordType());
        assertEquals(FichajeType.WORK, fichaje.get().getType());
        List<Fichaje> fichajes = repository.findByEmployeeIdAndDate(EMPLEADO, FECHA_JORNADA_INICIO, FECHA_JORNADA_FIN );
        assertNotNull(fichajes);
        assertTrue(fichajes.size() > 0);
        Fichaje firstFichaje = fichajes.get(0);
        assertNotNull(firstFichaje);
        assertEquals(EMPLEADO, firstFichaje.getEmployeeId());
        assertEquals(LocalDate.ofInstant(FECHA_JORNADA_INICIO, ZoneId.of("UTC")), LocalDate.ofInstant(firstFichaje.getDate(), ZoneId.of("UTC")));
    }
    
    @Test
    void dadaUnaFechaYUnEmpleadoDameFichajesSemanales() {
        assertNotNull(repository);
        
        Instant instanteEnMitadDeSemana = Instant.parse("2023-01-11T14:00:00Z");
        TemporalAdjuster temporalAjusterLunes = TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY);
        LocalDate localDateLunes = LocalDate.ofInstant(instanteEnMitadDeSemana, ZoneId.of("UTC")).with(temporalAjusterLunes);
        assertEquals(LocalDate.of(2023,1,9) , localDateLunes);
        
        TemporalAdjuster temporalAjusterViernes = TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY);
        LocalDate localDateViernes = LocalDate.ofInstant(instanteEnMitadDeSemana, ZoneId.of("UTC")).with(temporalAjusterViernes);
        assertEquals(LocalDate.of(2023,1,13) , localDateViernes);
        
        Instant inicioSemana = Instant.parse(localDateLunes.toString() + "T00:00:00Z");
        Instant finSemana = Instant.parse(localDateViernes.toString() + "T23:59:59.999Z");
        
        assertEquals(Instant.parse("2023-01-09T00:00:00Z") , inicioSemana);
        assertEquals(Instant.parse("2023-01-13T23:59:59.999Z") , finSemana);
        
        List<Fichaje> fichajesSemanales = repository.findByEmployeeIdAndDate(EMPLEADO, inicioSemana, finSemana);        
        assertNotNull(fichajesSemanales);
        assertEquals(4, fichajesSemanales.size());
        assertEquals(EMPLEADO, fichajesSemanales.get(0).getEmployeeId());        
        assertTrue(inicioSemana.compareTo(fichajesSemanales.get(0).getDate()) <= 0);
        assertTrue(finSemana.compareTo(fichajesSemanales.get(0).getDate()) >= 0);        
    }    
}
