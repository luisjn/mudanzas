package com.luisjn.backend.model;

import com.luisjn.backend.exception.BolsaNoTienePesoMinimoException;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
public class Trabajador {
    private static final int PESO_MINIMO_DEL_ELEMENTO_A_CARGAR = 50;
    private static final String BOLSA_NO_TIENE_PESO_MINIMO = "El peso total de los elementos que carga el trabajador no es de 50 libras";

    public int calcularViajesPorDia(DiaDeTrabajo diaDeTrabajo) {
        List<Elemento> elementosOrdenados;
        AtomicInteger viajesPorDia = new AtomicInteger();

        elementosOrdenados = diaDeTrabajo.getElementosACargar().stream()
                .sorted(Comparator.comparingInt(Elemento::getPeso).reversed())
                .collect(Collectors.toList());

        elementosOrdenados.stream().sorted(Comparator.comparingInt(Elemento::getPeso).reversed()).forEach(item -> {
            if (elementosOrdenados.size() <= 1 && item.getPeso() < PESO_MINIMO_DEL_ELEMENTO_A_CARGAR) return;
            if (item.getPeso() >= PESO_MINIMO_DEL_ELEMENTO_A_CARGAR){
                elementosOrdenados.remove(item);
                viajesPorDia.getAndIncrement();
            } else {
                int numeroItemsAMover = obtenerNumeroElementosAMover(item.getPeso());
                if (!Supervisor.tienePesoMinimo(item, numeroItemsAMover)) {
                    throw new BolsaNoTienePesoMinimoException(BOLSA_NO_TIENE_PESO_MINIMO);
                }
                if (numeroItemsAMover <= elementosOrdenados.size()) {
                    elementosOrdenados.remove(item);
                    for (int i = 1; i < numeroItemsAMover; i++) {
                        elementosOrdenados.remove(elementosOrdenados.size() - 1);
                    }
                    viajesPorDia.getAndIncrement();
                }
            }
        });

        return viajesPorDia.get();
    }

    public int obtenerNumeroElementosAMover(int pesoElemento) {
        return (int) Math.ceil((double) PESO_MINIMO_DEL_ELEMENTO_A_CARGAR / pesoElemento);
    }
}
