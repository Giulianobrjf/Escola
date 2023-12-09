package com.example.scaapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
    private Long id;
    private LocalDateTime data;
    private Float valor;
    private Integer peso;

    @ManyToOne
    private Turma turma;
}
