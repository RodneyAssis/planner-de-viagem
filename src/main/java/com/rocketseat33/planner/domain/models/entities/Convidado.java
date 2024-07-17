package com.rocketseat33.planner.domain.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Convidado implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private boolean isConfirmado;
    @ManyToMany(mappedBy = "convidados")
    @JsonBackReference
    private List<Viagem> viagens;

    public Convidado() {
        this.isConfirmado = false;
    }
}
