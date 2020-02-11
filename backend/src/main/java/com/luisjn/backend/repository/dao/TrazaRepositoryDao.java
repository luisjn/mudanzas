package com.luisjn.backend.repository.dao;

import com.luisjn.backend.repository.TrazaRepository;
import com.luisjn.backend.repository.jpa.TrazaRepositoryJpa;
import com.luisjn.backend.repository.entity.TrazaEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class TrazaRepositoryDao implements TrazaRepository {
    private final TrazaRepositoryJpa trazaRepositoryJpa;

    public TrazaRepositoryDao(TrazaRepositoryJpa trazaRepositoryJpa) {
        this.trazaRepositoryJpa = trazaRepositoryJpa;
    }

    @Override
    public void guardarTraza(String dni, LocalDateTime fechaEjecucion) {
        trazaRepositoryJpa.save(new TrazaEntity(dni, fechaEjecucion));
    }
}
