package com.example.scaapi.model.entity;

import com.example.scaapi.model.entity.interf.ICurso;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso implements ICurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "curso")
    private List<Disciplina> disciplinas;

    @ManyToOne
    @JoinColumn(name = "coordenador_matricula") // Use the actual column name in your database
    private Coordenador coordenador;
}
