package com.luisjn.backend.unit;

import com.luisjn.backend.model.Elemento;
import com.luisjn.backend.model.Supervisor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SupervisorTest {
    @Test
    public void bolsaNoTienePesoMinimoTest() {
        // arrange
        Elemento elemento = new Elemento(10);
        int numeroElementos = 3;

        // act
        boolean tieneElPesoMinimo = Supervisor.tienePesoMinimo(elemento, numeroElementos);

        // assert
        assertFalse(tieneElPesoMinimo);
    }

    @Test
    public void bolsaTienePesoMinimoTest() {
        // arrange
        Elemento elemento = new Elemento(25);
        int numeroElementos = 2;

        // act
        boolean tieneElPesoMinimo = Supervisor.tienePesoMinimo(elemento, numeroElementos);

        // assert
        assertTrue(tieneElPesoMinimo);
    }
}
