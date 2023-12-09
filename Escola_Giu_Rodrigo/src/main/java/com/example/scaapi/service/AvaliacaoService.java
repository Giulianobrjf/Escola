package com.example.scaapi.service;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Avaliacao;
import com.example.scaapi.model.entity.Turma;
import com.example.scaapi.model.entity.interf.ITurma;
import com.example.scaapi.model.repository.AvaliacaoRepository;
import com.example.scaapi.model.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository repository;

    public List<Avaliacao> getAvaliacoes() {return repository.findAll();
    }

    public Optional<Avaliacao> getAvaliacaoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Avaliacao salvar(Avaliacao avaliacao) {
        validar(avaliacao);
        return repository.save(avaliacao);
    }

    @Transactional
    public void excluir(Avaliacao avaliacao) {
        Objects.requireNonNull(avaliacao.getId());
        repository.delete(avaliacao);
    }

    public String validar(Avaliacao avaliacao) {
        if (avaliacao.getData() == null) {
            throw new RegraNegocioException("Data inválido");
        }
        if (avaliacao.getValor() == null || avaliacao.getValor() == 0.0f) {
            throw new RegraNegocioException("Valor inválido");
        }
        if (avaliacao.getPeso() == null || avaliacao.getPeso() == 0) {
            throw new RegraNegocioException("Peso inválido");
        }
        if (avaliacao.getTurma() == null || avaliacao.getTurma().getId() == null || avaliacao.getTurma().getId() == 0) {
            throw new RegraNegocioException("Turma inválida");
        }
        return "Avaliação valida";
    }
}
