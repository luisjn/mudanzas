package com.luisjn.backend.service;

import com.luisjn.backend.model.Mudanza;
import com.luisjn.backend.model.Trabajador;
import com.luisjn.backend.repository.TrazaRepository;
import com.luisjn.backend.repository.jpa.TrazaRepositoryJpa;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MudanzaServiceImpl implements MudanzaService {
    private final TrazaRepository trazaRepository;
    private Trabajador trabajador = new Trabajador();

    public MudanzaServiceImpl(TrazaRepository trazaRepository) {
        this.trazaRepository = trazaRepository;
    }

    @Override
    public List<String> realizarMudanza(Mudanza mudanza) {
        List<String> viajes = new ArrayList<>();
        AtomicInteger caso = new AtomicInteger(1);

        mudanza.getDiasDeTrabajo().stream().forEach(diaDeTrabajo -> {
            int viajesPorDia = trabajador.calcularViajesPorDia(diaDeTrabajo);
            viajes.add("Case #" + caso.get() + ": " + viajesPorDia);
            caso.getAndIncrement();
        });

        trazaRepository.guardarTraza(mudanza.getCedulaParticipante(), LocalDateTime.now());

        return viajes;
    }
}
