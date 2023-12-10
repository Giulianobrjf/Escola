package com.example.scaapi.service;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Coordenador;
import com.example.scaapi.model.repository.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CoordenadorService {
    @Autowired
    private CoordenadorRepository repository;

    public List<Coordenador> getCoordenadores() {
        return repository.findAll();
    }

    public Optional<Coordenador> getCoordenadorById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Coordenador salvar(Coordenador coordenador) {
        validar(coordenador);
        return repository.save(coordenador);
    }

    @Transactional
    public void excluir(Coordenador coordenador) {
        Objects.requireNonNull(coordenador.getId());

        repository.delete(coordenador);
    }

    public String validar(Coordenador coordenador) {
        if (coordenador.getDataInicio() == null) {
            throw new RegraNegocioException("Data de início inválida");
        }

        if (coordenador.getDataFim() == null) {
            throw new RegraNegocioException("Data de fim inválida");
        }
        return "Coordenador valido";
    }
}
