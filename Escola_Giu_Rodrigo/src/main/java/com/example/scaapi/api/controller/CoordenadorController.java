package com.example.scaapi.api.controller;

import com.example.scaapi.api.dto.CoordenadorDTO;
import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Coordenador;
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
@RequestMapping("/api/v1/coordenadores")
@RequiredArgsConstructor
public class CoordenadorController {

    private final CoordenadorService service;
    private final CursoService cursoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Coordenador> coordenadores = service.getCoordenadores();
        return ResponseEntity.ok(coordenadores.stream().map(CoordenadorDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Coordenador> coordenador = service.getCoordenadorById(id);
        if (!coordenador.isPresent()) {
            return new ResponseEntity("Coordenador não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(coordenador.map(CoordenadorDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CoordenadorDTO dto) {
        try {
            Coordenador coordenador = converter(dto);
            coordenador = service.salvar(coordenador);
            return new ResponseEntity(coordenador, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CoordenadorDTO dto) {
        if (!service.getCoordenadorById(id).isPresent()) {
            return new ResponseEntity("Coordenador não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Coordenador coordenador = converter(dto);
            coordenador.setId(id);
            service.salvar(coordenador);
            return ResponseEntity.ok(coordenador);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Coordenador> coordenador = service.getCoordenadorById(id);
        if (!coordenador.isPresent()) {
            return new ResponseEntity("Professor não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(coordenador.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Coordenador converter(CoordenadorDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Coordenador coordenador = modelMapper.map(dto, Coordenador.class);

        return coordenador;
    }
}