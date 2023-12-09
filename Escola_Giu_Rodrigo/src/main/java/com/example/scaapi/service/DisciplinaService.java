package com.example.scaapi.service;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.entity.Disciplina;
import com.example.scaapi.model.repository.DisciplinaRepository;
import com.example.scaapi.model.entity.interf.IDisciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;

    public List<Disciplina> getDisciplinas() {
        return repository.findAll();
    }

    public Optional<Disciplina> getDisciplinaById(Long id) {
        return repository.findById(id);
    }

    public List<Disciplina> getDisciplinasByCurso(Optional<Curso> curso) {
        return repository.findByCurso(curso);
    }

    @Transactional
    public Disciplina salvar(Disciplina disciplina) {
        validar(disciplina);
        return repository.save(disciplina);
    }

    @Transactional
    public void excluir(Disciplina disciplina) {
        Objects.requireNonNull(disciplina.getId());
        repository.delete(disciplina);
    }

    public String validar(IDisciplina disciplina) {

        if (disciplina.getNome() == null || disciplina.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (disciplina.getCurso() == null || disciplina.getCurso().getId() == null || disciplina.getCurso().getId() == 0) {
            throw new RegraNegocioException("Curso inválido");
        }
        if (disciplina.getCargaHoraria() > 300 ) {
            throw new RegraNegocioException("Carga horaria acima do permitido");
        }
        if (disciplina.getCargaHoraria() < 15 ) {
            throw new RegraNegocioException("Carga horaria abaixo do minimo");
        }
        return "disciplina valida";
    }
}

