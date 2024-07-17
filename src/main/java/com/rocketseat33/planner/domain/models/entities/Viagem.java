package com.rocketseat33.planner.domain.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rocketseat33.planner.domain.dto.AtualizarViagemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "viagem")
@Getter
@Setter
public class Viagem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    @Column(nullable = false)
    private String destino;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:MM:ss")
    private Date dataInicio;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:MM:ss")
    private Date dataFim;
    @Column(nullable = false)
    private boolean confirmacao;
    @Column(nullable = false)
    private String nomeProprietario;
    @Column(nullable = false)
    private String emailProprietario;
    @ManyToMany
    @JoinTable(name = "viagens_convidados", joinColumns = {
            @JoinColumn(name = "fk_viagem")},
            inverseJoinColumns = {@JoinColumn(name = "fk_convidado")})
    private List<Convidado> convidados;

    @OneToMany(mappedBy = "viagem")
    private List<Atividade> atividades;

    public Viagem() {
        this.confirmacao = false;
    }
}
