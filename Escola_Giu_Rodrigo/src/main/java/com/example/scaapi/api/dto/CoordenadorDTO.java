package com.example.scaapi.api.dto;

import com.example.scaapi.model.entity.Coordernador;
import com.example.scaapi.model.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordenadorDTO {
    private Long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public static CoordenadorDTO create(Coordernador coordernador) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(coordernador, CoordenadorDTO.class);
    }
}
