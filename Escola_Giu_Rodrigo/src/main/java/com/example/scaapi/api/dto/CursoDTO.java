package com.example.scaapi.api.dto;

import com.example.scaapi.model.entity.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class CursoDTO {
    private Long id;
    private String nome;
    private Long idCoordenador;

    public static CursoDTO create(Curso curso) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(curso, CursoDTO.class);
    }

}