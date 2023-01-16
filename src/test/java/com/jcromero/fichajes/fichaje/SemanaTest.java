package com.jcromero.fichajes.fichaje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class SemanaTest {
    private static final LocalDate DIA_SEMANA = LocalDate.of(2023, 1, 9);
    private static final String EMPLEADO = "222222222";
    private static final String SERVICE = "ALBASANZ";
    
    @Test
    void buildSemanaParaDiaSemana() {
        Semana semana = Semana.buildFor(DIA_SEMANA);
        assertNotNull(semana);
        assertEquals("2023-01-09T00:00:00Z", semana.getFechaInicio().toString());
        assertEquals("2023-01-13T23:59:59.999Z", semana.getFechaFin().toString());
        Map<LocalDate, Jornada> jornadas = semana.getJornadasSemana();
        assertNotNull(jornadas);
        assertEquals(5, jornadas.size());
        Jornada lunes = jornadas.get(LocalDate.of(2023, 1, 9));
        assertNotNull(lunes);
        assertEquals("2023-01-09", lunes.getFecha().toString());
        Jornada viernes = jornadas.get(LocalDate.of(2023, 1, 13));
        assertNotNull(viernes);
        assertEquals("2023-01-13", viernes.getFecha().toString());
    }
    
    @Test
    void fillSemana() {
        Semana semana = Semana.buildFor(DIA_SEMANA);
        List<Fichaje> fichajes = getFichajesForTest(semana.getFechaInicio(), semana.getFechaFin());
        semana.fillSemanaWithFichajes(fichajes);
        
        Map<LocalDate, Jornada> jornadas = semana.getJornadasSemana();
        assertNotNull(jornadas);
        assertEquals(5, jornadas.size());
        Jornada lunes = jornadas.get(LocalDate.of(2023, 1, 9));
        assertTrue(lunes.isCompleta());
    }

    private List<Fichaje> getFichajesForTest(Instant fechaInicio, Instant fechaFin) {
        List<Fichaje> fichajes = new ArrayList<>();
        
        Instant lunesALasOcho = Instant.parse(LocalDate.ofInstant(fechaInicio, ZoneId.of("UTC")).toString() + "T08:00:00Z");
        Fichaje inicioManhana = buildFichajeDefault(lunesALasOcho);
        fichajes.add(inicioManhana);
        
        Instant lunesALasOnce = Instant.parse(LocalDate.ofInstant(fechaInicio, ZoneId.of("UTC")).toString() + "T11:00:00Z");
        Fichaje inicioDescanso = buildFichajeDefault(lunesALasOnce);
        inicioDescanso.setType(FichajeType.REST);     
        fichajes.add(inicioDescanso);
        
        Instant lunesALasOnceYMedia = Instant.parse(LocalDate.ofInstant(fechaInicio, ZoneId.of("UTC")).toString() + "T11:30:00Z");
        Fichaje finDescanso = buildFichajeDefault(lunesALasOnceYMedia);
        finDescanso.setRecordType(RecordType.OUT);
        finDescanso.setType(FichajeType.REST);  
        fichajes.add(finDescanso);           
        
        Instant lunesALaUna = Instant.parse(LocalDate.ofInstant(fechaInicio, ZoneId.of("UTC")).toString() + "T13:00:00Z");
        Fichaje finManhana = buildFichajeDefault(lunesALaUna);
        finManhana.setRecordType(RecordType.OUT);
        fichajes.add(finManhana);
        
        Instant lunesALasTres = Instant.parse(LocalDate.ofInstant(fechaInicio, ZoneId.of("UTC")).toString() + "T15:00:00Z");
        Fichaje inicioTarde = buildFichajeDefault(lunesALasTres);
        fichajes.add(inicioTarde);
        
        Instant lunesALasSeis = Instant.parse(LocalDate.ofInstant(fechaInicio, ZoneId.of("UTC")).toString() + "T18:00:00Z");        
        Fichaje finTarde = buildFichajeDefault(lunesALasSeis);
        finTarde.setRecordType(RecordType.OUT);
        fichajes.add(finTarde);
        
        return fichajes;
    }

    private Fichaje buildFichajeDefault(Instant fecha) {
        Fichaje inicioManhana = new Fichaje();
        inicioManhana.setBusinessId(1L);
        inicioManhana.setDate(fecha);
        inicioManhana.setEmployeeId(EMPLEADO);
        inicioManhana.setRecordType(RecordType.IN);
        inicioManhana.setServiceId(SERVICE);
        inicioManhana.setType(FichajeType.WORK);
        return inicioManhana;
    }

}
