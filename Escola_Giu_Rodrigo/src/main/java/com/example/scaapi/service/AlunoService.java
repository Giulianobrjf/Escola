package com.example.scaapi.service;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.*;
import com.example.scaapi.model.entity.interf.IAluno;
import com.example.scaapi.model.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;


    public List<Aluno> getAlunos() {
        return repository.findAll();
    }

    public Optional<Aluno> getAlunoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Aluno salvar(Aluno aluno) {
        validar(aluno);
        return repository.save(aluno);
    }

    @Transactional
    public void excluir(Aluno aluno) {
        Objects.requireNonNull(aluno.getId());
        repository.delete(aluno);
    }

    public String validar(IAluno aluno) {
        if (aluno.getMatricula() == null || aluno.getMatricula() == 0) {
            throw new RegraNegocioException("Matrícula inválida");
        }
        if (aluno.getNome() == null || aluno.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (aluno.getCurso() == null || aluno.getCurso().getId() == null || aluno.getCurso().getId() == 0) {
            throw new RegraNegocioException("Curso inválido");
        }
        return "Aluno valido";
    }
}
