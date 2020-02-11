package com.luisjn.backend.model;

import com.luisjn.backend.exception.BolsaNoTienePesoMinimoException;

public class Supervisor {
    private static final int pesoMinimoBolsa = 50;

    public static boolean tienePesoMinimo(Elemento elementoEnLaParteSuperior, int numeroDeElementos) {
        int pesoTotalBolsa = numeroDeElementos * elementoEnLaParteSuperior.getPeso();

        if (pesoTotalBolsa < pesoMinimoBolsa) {
            return false;
        }
        return true;
    }
}
