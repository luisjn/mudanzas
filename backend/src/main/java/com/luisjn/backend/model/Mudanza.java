package com.luisjn.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class Mudanza {
    private final int diasATrabajar;
    private final String cedulaParticipante;
    private final List<DiaDeTrabajo> diasDeTrabajo;
}
