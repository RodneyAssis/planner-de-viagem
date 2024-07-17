package com.rocketseat33.planner.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Getter
public class ViagemDTO {

    private String destino;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:MM:ss")
    private Date dataInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:MM:ss")
    private Date dataFim;
    private String nomeProprietario;
    private String emailProprietario;
    private Set<ConvidadosDTO> convidados;
}
