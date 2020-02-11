package com.luisjn.backend.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Table(name = "traza")
@Entity
public class TrazaEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @NotNull
    @Column(name = "dni_del_ejecutor")
    private String dniDelEjecutor;
    @NotNull
    @Column(name = "fecha_ejecucion")
    private LocalDateTime fechaEjecucion;

    public TrazaEntity(@NotNull String dniDelEjecutor, @NotNull LocalDateTime fechaEjecucion) {
        this.dniDelEjecutor = dniDelEjecutor;
        this.fechaEjecucion = fechaEjecucion;
    }
}
