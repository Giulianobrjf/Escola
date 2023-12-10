package com.example.scaapi.service;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.entity.interf.ICurso;
import com.example.scaapi.model.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public List<Curso> getCursos() {
        return repository.findAll();
    }

    public Optional<Curso> getCursoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Curso salvar(Curso curso) {
        validar(curso);
        return repository.save(curso);
    }

    @Transactional
    public void excluir(Curso curso) {
        Objects.requireNonNull(curso.getId());
        repository.delete(curso);
    }

    public String validar(ICurso curso) {
        if (curso.getNome() == null || curso.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }

//        if (curso.getCoordenador() == null || curso.getCoordenador().getId() == null || curso.getCoordenador().getId() == 0) {
//            throw new RegraNegocioException("Coordenador inválido");
//        }
        return "Curso valida";
    }
}
