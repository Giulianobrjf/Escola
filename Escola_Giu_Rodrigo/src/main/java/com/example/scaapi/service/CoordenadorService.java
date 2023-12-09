package com.example.scaapi.service;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Coordernador;
import com.example.scaapi.model.entity.Professor;
import com.example.scaapi.model.entity.Turma;
import com.example.scaapi.model.entity.interf.IProfessor;
import com.example.scaapi.model.repository.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CoordenadorService {
    @Autowired
    private CoordenadorRepository repository;

    public List<Coordernador> getCoordenadores() {
        return repository.findAll();
    }

    public Optional<Coordernador> getCoordenadorById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Coordernador salvar(Coordernador coordernador) {
        validar(coordernador);
        return repository.save(coordernador);
    }

    @Transactional
    public void excluir(Coordernador coordernador) {
        Objects.requireNonNull(coordernador.getId());

        repository.delete(coordernador);
    }

    public String validar(Coordernador coordernador) {
        if (coordernador.getDataInicio() == null) {
            throw new RegraNegocioException("Data de início inválida");
        }

        if (coordernador.getDataFim() == null) {
            throw new RegraNegocioException("Data de fim inválida");
        }
        return "Coordenador valido";
    }
}
