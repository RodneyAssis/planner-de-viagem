package com.rocketseat33.planner.domain.services;

import com.rocketseat33.planner.domain.models.entities.Convidado;
import com.rocketseat33.planner.domain.models.repositories.IConvidado;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ConvidadoService {
    final IConvidado iConvidado;

    public ConvidadoService(IConvidado iConvidado) {
        this.iConvidado = iConvidado;
    }


    public Optional<Convidado> consultarConvidado(Long id) {
        return iConvidado.findById(id);
    }

    public void atualizarDadosEConfirmar(Convidado convidado, String nomeConvidado) {
        convidado.setConfirmado(true);
        convidado.setNome(nomeConvidado);
        iConvidado.save(convidado);
    }
}
