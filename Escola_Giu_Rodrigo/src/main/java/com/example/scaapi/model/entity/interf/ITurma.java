package com.example.scaapi.model.entity.interf;

import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.model.entity.Disciplina;
import com.example.scaapi.model.entity.Professor;

import java.util.List;

public interface ITurma {
    public List<Aluno> getAlunos();
    public Integer getAno();
    public Integer getSemestre();
    public String getNome();
    public Disciplina getDisciplina();
    public Professor getProfessor();
}
