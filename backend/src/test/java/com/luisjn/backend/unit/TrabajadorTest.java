package com.luisjn.backend.unit;

import com.luisjn.backend.model.DiaDeTrabajo;
import com.luisjn.backend.model.Elemento;
import com.luisjn.backend.model.Trabajador;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrabajadorTest {
    Trabajador trabajador = new Trabajador();

    @Test
    public void obtenerViajesConElementosDePesoMayorAlPesoMinimo() {
        // arrange
        int viajesEsperados = 3;
        List<Elemento> elementos = new ArrayList<>();
        elementos.add(new Elemento(50));
        elementos.add(new Elemento(90));
        elementos.add(new Elemento(71));
        DiaDeTrabajo diaDeTrabajo = new DiaDeTrabajo(3, elementos);

        // act
        int viajes = trabajador.calcularViajesPorDia(diaDeTrabajo);

        // assert
        assertEquals(viajesEsperados, viajes);
    }

    @Test
    public void obtenerViajesConElementosDePesoMenorAlPesoMinimo() {
        // arrange
        int viajesEsperados = 1;
        List<Elemento> elementos = new ArrayList<>();
        elementos.add(new Elemento(20));
        elementos.add(new Elemento(20));
        elementos.add(new Elemento(20));
        DiaDeTrabajo diaDeTrabajo = new DiaDeTrabajo(3, elementos);

        // act
        int viajes = trabajador.calcularViajesPorDia(diaDeTrabajo);

        // assert
        assertEquals(viajesEsperados, viajes);
    }

    @Test
    public void obtenerViajesConElementosDePesoMayorYMenorAlPesoMinimo() {
        // arrange
        int viajesEsperados = 2;
        List<Elemento> elementos = new ArrayList<>();
        elementos.add(new Elemento(91));
        elementos.add(new Elemento(25));
        elementos.add(new Elemento(20));
        DiaDeTrabajo diaDeTrabajo = new DiaDeTrabajo(3, elementos);

        // act
        int viajes = trabajador.calcularViajesPorDia(diaDeTrabajo);

        // assert
        assertEquals(viajesEsperados, viajes);
    }

    @Test
    public void obtenerViajesConElementosConPesosQueNoSumanMasDelPesoMinimo() {
        // arrange
        int viajesEsperados = 0;
        List<Elemento> elementos = new ArrayList<>();
        elementos.add(new Elemento(1));
        elementos.add(new Elemento(2));
        elementos.add(new Elemento(3));
        elementos.add(new Elemento(4));
        elementos.add(new Elemento(5));
        DiaDeTrabajo diaDeTrabajo = new DiaDeTrabajo(5, elementos);

        // act
        int viajes = trabajador.calcularViajesPorDia(diaDeTrabajo);

        // assert
        assertEquals(viajesEsperados, viajes);
    }

    @Test
    public void obtenerViajesConUnElementoConPesoMayorAlMinimoYConElementosConPesosQueNoSumanMasDelPesoMinimo() {
        // arrange
        int viajesEsperados = 1;
        List<Elemento> elementos = new ArrayList<>();
        elementos.add(new Elemento(60));
        elementos.add(new Elemento(10));
        elementos.add(new Elemento(10));
        DiaDeTrabajo diaDeTrabajo = new DiaDeTrabajo(3, elementos);

        // act
        int viajes = trabajador.calcularViajesPorDia(diaDeTrabajo);

        // assert
        assertEquals(viajesEsperados, viajes);
    }

    @Test
    public void obtenerNumeroDeElementosAMoverConUnElementoDePesoMenorAlPesoMinimo() {
        // arrange
        int numeroElementosEsperado = 5;
        int pesoElemento = 10;

        // act
        int numeroElementos = trabajador.obtenerNumeroElementosAMover(pesoElemento);

        // assert
        assertEquals(numeroElementosEsperado, numeroElementos);
    }
}
