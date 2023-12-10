package com.example.scaapi.api.dto;

import com.example.scaapi.model.entity.Avaliacao;
import com.example.scaapi.model.entity.Turma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {

    private Long id;
    private LocalDateTime data;
    private Float valor;
    private Integer peso;
    private Long idTurma;

    public static AvaliacaoDTO create(Avaliacao avaliacao) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(avaliacao, AvaliacaoDTO.class);
    }
}

