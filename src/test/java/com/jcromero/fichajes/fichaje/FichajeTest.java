package com.jcromero.fichajes.fichaje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class FichajeTest {
    
    @Test
    void buildFichaje() {
        Fichaje fichaje = new Fichaje();
        assertNotNull(fichaje);
        assertNull(fichaje.getId());
        fichaje.setRecordType(RecordType.IN);
        assertEquals(RecordType.IN, fichaje.getRecordType());
    }

}
