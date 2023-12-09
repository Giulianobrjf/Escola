package com.example.scaapi.service;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Turma;
import com.example.scaapi.model.repository.TurmaRepository;
import com.example.scaapi.model.entity.interf.ITurma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository repository;

    public List<Turma> getTurmas() {return repository.findAll();
    }

    public Optional<Turma> getTurmaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Turma salvar(Turma turma) {
        validar(turma);
        return repository.save(turma);
    }

    @Transactional
    public void excluir(Turma turma) {
        Objects.requireNonNull(turma.getId());
        repository.delete(turma);
    }

    public String validar(ITurma turma) {
        if (turma.getAno() == null || turma.getAno() == 0) {
            throw new RegraNegocioException("Ano inválido");
        }
        if (turma.getSemestre() == null || turma.getSemestre() == 0) {
            throw new RegraNegocioException("Semestre inválido");
        }
        if (turma.getNome() == null || turma.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (turma.getDisciplina() == null || turma.getDisciplina().getId() == null || turma.getDisciplina().getId() == 0) {
            throw new RegraNegocioException("Disciplina inválida");
        }
        if (turma.getAlunos().size() > 15) {
            throw new RegraNegocioException("maximo de alunos na turma atingido");
        }
        if (turma.getProfessor() == null || turma.getProfessor().getId() == null || turma.getProfessor().getId() == 0) {
            throw new RegraNegocioException("turma deve haver um professor");
        }
        return "turma valida";
    }
}
