package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.AlunoDTO;
import com.example.scaapi.api.dto.AvaliacaoDTO;
import com.example.scaapi.api.dto.TurmaDTO;
import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Avaliacao;
import com.example.scaapi.model.entity.Disciplina;
import com.example.scaapi.model.entity.Professor;
import com.example.scaapi.model.entity.Turma;
import com.example.scaapi.service.AvaliacaoService;
import com.example.scaapi.service.DisciplinaService;
import com.example.scaapi.service.ProfessorService;
import com.example.scaapi.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService service;
    private final TurmaService turmaService;

    @GetMapping()
    public ResponseEntity get() {
        List<Avaliacao> avaliacoes = service.getAvaliacoes();
        return ResponseEntity.ok(avaliacoes.stream().map(AvaliacaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Avaliacao> avaliacao = service.getAvaliacaoById(id);
        if (!avaliacao.isPresent()) {
            return new ResponseEntity("Avaliação não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(avaliacao.map(AvaliacaoDTO::create));
    }

    @GetMapping("{id}/turma")
    public ResponseEntity getDisciplinas(@PathVariable("id") Long id) {
        Optional<Avaliacao> avaliacao = service.getAvaliacaoById(id);
        if (!avaliacao.isPresent()) {
            return new ResponseEntity("Avaliação não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(avaliacao.get().getTurma());
    }

    @PostMapping()
    public ResponseEntity post(AvaliacaoDTO dto) {
        try {
            Avaliacao avaliacao = converter(dto);
            avaliacao = service.salvar(avaliacao);
            return new ResponseEntity(avaliacao, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, AvaliacaoDTO dto) {
        if (!service.getAvaliacaoById(id).isPresent()) {
            return new ResponseEntity("Avaliação não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Avaliacao avaliacao = converter(dto);
            service.salvar(avaliacao);
            return ResponseEntity.ok(avaliacao);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Avaliacao> avaliacao = service.getAvaliacaoById(id);
        if (!avaliacao.isPresent()) {
            return new ResponseEntity("Avaliação não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(avaliacao.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Avaliacao converter(AvaliacaoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Avaliacao avaliacao = modelMapper.map(dto, Avaliacao.class);
        if (dto.getIdTurma() != null) {
            Optional<Turma> turma = turmaService.getTurmaById(dto.getIdTurma());
            if (!turma.isPresent()) {
                avaliacao.setTurma(null);
            } else {
                avaliacao.setTurma(turma.get());
            }
        }
        return avaliacao;
    }
}


