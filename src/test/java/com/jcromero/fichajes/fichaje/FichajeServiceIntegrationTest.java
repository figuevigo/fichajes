package com.jcromero.fichajes.fichaje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class FichajeServiceIntegrationTest {
    
    @Autowired
    private FichajeService service;
    
    @Autowired
    private FichajeRepository repository;    
    
    @Test
    void addFichajeValido() throws FichajeException {
        assertNotNull(service);
        repository.deleteAll();
        Long id = addFichajePruebaForUsuario("02_000064");
        assertNotNull(id);
    }

    private Long addFichajePruebaForUsuario(String usuario) throws FichajeException {
        FichajeDTO fichaje = new FichajeDTO();
        fichaje.setBusinessId(1L);
        fichaje.setDate(Instant.now());
        fichaje.setEmployeeId(usuario);
        fichaje.setRecordType(RecordType.IN);
        fichaje.setServiceId("ATALAYAS");
        fichaje.setType(FichajeType.REST);
        Long id = service.addFichaje(fichaje);
        return id;
    }
    
    @Test
    void recuperaTodosFichajes() throws FichajeException {
        repository.deleteAll();
        List<FichajeDTO> fichajes = service.getAllFichajes();
        assertNotNull(fichajes);
        assertEquals(0, fichajes.size());   
        
        addFichajePruebaForUsuario("02_000064");
        addFichajePruebaForUsuario("03_000065");
        
        fichajes = service.getAllFichajes();
        assertNotNull(fichajes);
        assertEquals(2, fichajes.size());        
    }
    
    @Test
    void recuperaTodosFichajesEmpleado() throws FichajeException {
        repository.deleteAll();  
        
        addFichajePruebaForUsuario("02_000064");
        addFichajePruebaForUsuario("03_000065");
        
        List<FichajeDTO> fichajes = service.getAllFichajesEmpleado("02_000064");
        assertNotNull(fichajes);
        assertEquals(1, fichajes.size());        
    }    
    
    @Test
    @Sql({"/fichajes_semana_entera.sql"})    
    void getFichajesSemanaPruebaEnteraEmpleado() throws FichajeException {
        String empleado = "222222222";
        LocalDate miercolesSemanaPrueba = LocalDate.of(2023, 1, 11);
        
        Semana semana = service.getFichajesSemanaEmpleado(empleado, miercolesSemanaPrueba);
        
        assertNotNull(semana);
        assertNotNull(semana.getTiempoTrabajoEfectivoSemanal());
        assertTrue(semana.getTiempoTrabajoEfectivoSemanal().toHours() >= 40L);
        assertNotNull(semana.getAlertas());
        assertTrue(semana.getAlertas().size()>0);
    }

}
