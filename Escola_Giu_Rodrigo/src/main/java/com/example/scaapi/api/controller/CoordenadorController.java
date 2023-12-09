package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.CoordenadorDTO;
import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Coordernador;
import com.example.scaapi.service.CoordenadorService;
import com.example.scaapi.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/professores")
@RequiredArgsConstructor
public class CoordenadorController {

    private final CoordenadorService service;
    private final CursoService cursoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Coordernador> coordernadores = service.getCoordenadores();
        return ResponseEntity.ok(coordernadores.stream().map(CoordenadorDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Coordernador> coordernador = service.getCoordenadorById(id);
        if (!coordernador.isPresent()) {
            return new ResponseEntity("Coordenador não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(coordernador.map(CoordenadorDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(CoordenadorDTO dto) {
        try {
            Coordernador coordernador = converter(dto);
            coordernador = service.salvar(coordernador);
            return new ResponseEntity(coordernador, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, CoordenadorDTO dto) {
        if (!service.getCoordenadorById(id).isPresent()) {
            return new ResponseEntity("Coordenador não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Coordernador coordernador = converter(dto);
            coordernador.setId(id);
            service.salvar(coordernador);
            return ResponseEntity.ok(coordernador);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Coordernador> coordernador = service.getCoordenadorById(id);
        if (!coordernador.isPresent()) {
            return new ResponseEntity("Professor não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(coordernador.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Coordernador converter(CoordenadorDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Coordernador coordernador = modelMapper.map(dto, Coordernador.class);

        return coordernador;
    }
}