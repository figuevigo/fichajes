package com.jcromero.fichajes.fichaje;

import java.time.LocalDate;
import java.time.LocalTime;

class Jornada {
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaInicioDescanso;
    private LocalTime horaFinDescanso;
    private LocalTime horaFinManhana;
    private LocalTime horaInicioTarde;
    private LocalTime horaFin;
    
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    public LocalTime getHoraInicioDescanso() {
        return horaInicioDescanso;
    }
    public void setHoraInicioDescanso(LocalTime horaInicioDescanso) {
        this.horaInicioDescanso = horaInicioDescanso;
    }
    public LocalTime getHoraFinDescanso() {
        return horaFinDescanso;
    }
    public void setHoraFinDescanso(LocalTime horaFinDescanso) {
        this.horaFinDescanso = horaFinDescanso;
    }
    public LocalTime getHoraFinManhana() {
        return horaFinManhana;
    }
    public void setHoraFinManhana(LocalTime horaFinManhana) {
        this.horaFinManhana = horaFinManhana;
    }
    public LocalTime getHoraInicioTarde() {
        return horaInicioTarde;
    }
    public void setHoraInicioTarde(LocalTime horaInicioTarde) {
        this.horaInicioTarde = horaInicioTarde;
    }
    public LocalTime getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
    
    public Boolean isCorrecta() {
        return isCompleta() && isManhanaCorrecta() && isTardeCorrecta() && isDescansoCorrecto(); 
    }
    private boolean isDescansoCorrecto() {
        return isPeriodoVacio(horaInicioDescanso, horaFinDescanso) || (isPeriodoCompleto(horaInicioDescanso, horaFinDescanso) && isPeriodoCorrecto(horaInicioDescanso, horaFinDescanso));
    }
    private boolean isTardeCorrecta() {
        return isPeriodoCorrecto(horaInicioTarde, horaFin);
    }
    private boolean isManhanaCorrecta() {
        return isPeriodoCorrecto(horaInicio, horaFinManhana);
    }
    public Boolean isCompleta() {
        return isManhanaCompleta() && isTardeCompleta() && isDescansoCompleto();
    }
    private boolean isManhanaCompleta() {
        return isPeriodoCompleto(horaInicio,horaFinManhana);
    }
    private boolean isTardeCompleta() {
        return isPeriodoCompleto(horaInicioTarde,horaFin);
    }
    private boolean isDescansoCompleto() {
        return isPeriodoOpcionalCompleto(horaInicioDescanso,horaFinDescanso);
    }
    private boolean isPeriodoOpcionalCompleto(LocalTime inicio, LocalTime fin) {
        return isPeriodoCompleto(inicio,fin) || isPeriodoVacio(inicio, fin);
    }
    private boolean isPeriodoVacio(LocalTime inicio, LocalTime fin) {
        return inicio == null && fin == null;
    } 
    private boolean isPeriodoCompleto(LocalTime inicio, LocalTime fin) {
        return (inicio != null && fin != null);
    }
    private boolean isPeriodoCorrecto(LocalTime inicio, LocalTime fin) {
        return (inicio.isBefore(fin));
    }
}
