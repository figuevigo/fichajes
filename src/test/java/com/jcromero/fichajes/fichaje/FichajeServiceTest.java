package com.jcromero.fichajes.fichaje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FichajeServiceTest {
    
    @Autowired
    private FichajeService service;
    
    @Autowired
    private FichajeRepository repository;    
    
    @Test
    void addFichajeValido() throws FichajeException {
        assertNotNull(service);
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

}
