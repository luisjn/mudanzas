package com.luisjn.backend.repository;

import java.time.LocalDateTime;

public interface TrazaRepository {
    void guardarTraza(String dni, LocalDateTime fechaEjecucion);
}
