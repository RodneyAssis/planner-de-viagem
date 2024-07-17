package com.rocketseat33.planner.domain.services;

import com.rocketseat33.planner.domain.models.repositories.IAtividade;
import org.springframework.stereotype.Service;

@Service
public class AtividadeService {

    final IAtividade iAtividade;

    public AtividadeService(IAtividade iAtividade) {
        this.iAtividade = iAtividade;
    }


}
