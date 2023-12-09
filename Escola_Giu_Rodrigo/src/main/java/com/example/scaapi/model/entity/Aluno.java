package com.example.scaapi.model.entity;

import com.example.scaapi.model.entity.interf.IAluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno extends Pessoa implements IAluno {

    private Integer matricula;

    @ManyToOne
    private Curso curso;

    @ManyToMany(mappedBy = "alunos")
    private List<Turma> turmas;

}
