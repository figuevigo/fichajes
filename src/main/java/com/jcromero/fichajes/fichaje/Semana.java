package com.jcromero.fichajes.fichaje;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Semana {
    private static final TemporalAdjuster TEMPORAL_AJUSTER_lUNES = TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY);
    private static final TemporalAdjuster TEMPORAL_AJUSTER_VIERNES = TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY);
    private static final TemporalAdjuster DIA_SIGUIENTE = TemporalAdjusters.ofDateAdjuster(date -> date.plusDays(1));

    static Semana buildFor(LocalDate diaSemana) {
        return new Semana(diaSemana);
    }    
    
    private Map<LocalDate, Jornada> jornadasSemana = new HashMap<>();
    private Instant fechaInicio;
    private Instant fechaFin;
    private Duration tiempoTrabajoEfectivoSemanal = Duration.ZERO;
    private List<Alerta> alertas = new ArrayList<>();
    
    protected Semana(LocalDate diaSemana) {
        LocalDate localDateLunes = diaSemana.with(TEMPORAL_AJUSTER_lUNES);
        fechaInicio = Instant.parse(localDateLunes.toString() + "T00:00:00Z");
        
        LocalDate localDateViernes = diaSemana.with(TEMPORAL_AJUSTER_VIERNES);
        fechaFin = Instant.parse(localDateViernes.toString() + "T23:59:59.999Z");
        
        LocalDate dia = LocalDate.from(localDateLunes);
        while(dia.compareTo(localDateViernes) <= 0) {
                Jornada jornada = new Jornada();
                LocalDate diaJornada = LocalDate.from(dia);
                jornada.setFecha(diaJornada);
                jornadasSemana.put(diaJornada, jornada);
                dia = dia.with(DIA_SIGUIENTE);
        }
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }
    
    public Instant getFechaFin() {
        return fechaFin;
    }
    
    public Map<LocalDate, Jornada> getJornadasSemana() {
        if (jornadasSemana.isEmpty()) throw new IllegalStateException("Semana vacía");
        return jornadasSemana;
    }    
    
    public Duration getTiempoTrabajoEfectivoSemanal() {
        return tiempoTrabajoEfectivoSemanal;
    }

    public void fillSemanaWithFichajes(List<Fichaje> fichajesSemanalesEmpleado) {
        for(Fichaje fichaje : fichajesSemanalesEmpleado) {
                Jornada jornada = jornadasSemana.get(LocalDate.ofInstant(fichaje.getDate(), ZoneId.of("UTC")));
                LocalTime hora = LocalTime.ofInstant(fichaje.getDate(), ZoneId.of("UTC"));
                if (RecordType.IN.equals(fichaje.getRecordType())) {
                        if (FichajeType.WORK.equals(fichaje.getType())) {
                                if (jornada.getHoraInicio() == null) {
                                        jornada.setHoraInicio(hora);
                                } else {
                                        jornada.setHoraInicioTarde(hora);
                                }
                        } else {
                                jornada.setHoraInicioDescanso(hora);
                        }
                } else {
                        if (FichajeType.WORK.equals(fichaje.getType())) {
                                if (jornada.getHoraFinManhana() == null) {
                                        jornada.setHoraFinManhana(hora);
                                } else {
                                        jornada.setHoraFin(hora);
                                }
                        } else {
                                jornada.setHoraFinDescanso(hora);
                        }                
                }
        }
        calculaHorasEfectivasTrabajo();
    }

    private void calculaHorasEfectivasTrabajo() {
        Duration duracion = Duration.ZERO;
        for (Jornada jornada : jornadasSemana.values()) {
            duracion = duracion.plus(jornada.getTiempoTrabajoEfectivo());
        }
        tiempoTrabajoEfectivoSemanal = duracion;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void addAlerta(Alerta alerta) {
        alertas.add(alerta);        
    }
}
