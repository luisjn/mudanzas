package com.luisjn.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class DiaDeTrabajo {
    private final int numeroDeElementos;
    private final List<Elemento> elementosACargar;
}
