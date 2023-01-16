package com.jcromero.fichajes.fichaje;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class FichajeController {
    
    @Autowired
    private FichajeService service;
    
    @PostMapping("/fichajes")
    ResponseEntity<String>  addFichaje(@RequestBody List<FichajeDTO> fichajes) {
        Long oks = 0L;
        Long errors = 0L;
        for (FichajeDTO fichaje : fichajes) {
            try {
                service.addFichaje(fichaje);
                oks++;
            } catch (FichajeException e) {
                errors++;
            }
        }
        String mensaje = String.format("Procesados %1$d fichajes. %2$d Ok. %3$d con error.", fichajes.size(), oks, errors);
        return ResponseEntity.ok(mensaje);
    }    
    
    @GetMapping("/fichajes")
    public List<FichajeDTO> getAllFichajes() {
        return service.getAllFichajes();
    }   
    
    @GetMapping("/fichajes/{empleado}/semana")
    public Semana getFichajesSemanaActualEmpelado(@PathVariable String empleado) {
        return service.getFichajesSemanaEnCursoEmpleado(empleado); 
    }       
    
    @GetMapping("/fichajes/{empleado}/semana/{fecha}")
    public Semana getFichajesSemanaEmpleado(@PathVariable String empleado, @PathVariable String fecha) {
        return service.getFichajesSemanaEmpleado(empleado, LocalDate.parse(fecha)); 
    }           
}
