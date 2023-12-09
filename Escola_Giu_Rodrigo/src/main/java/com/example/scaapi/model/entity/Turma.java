package com.example.scaapi.model.entity;

import com.example.scaapi.model.entity.interf.ITurma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turma implements ITurma{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Integer ano;
        private Integer semestre;
        private String nome;

        @ManyToOne
        private Disciplina disciplina;

        @ManyToOne
        private Professor professor;

        @ManyToMany
        @JoinTable(name = "turma_aluno",
                joinColumns = @JoinColumn(name = "turma_id"),
                inverseJoinColumns = @JoinColumn(name = "aluno_id"))
        private List<Aluno> alunos = new ArrayList<>();
}
