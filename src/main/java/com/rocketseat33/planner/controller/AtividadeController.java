package com.rocketseat33.planner.controller;

import com.rocketseat33.planner.domain.dto.AtividadesDTO;
import com.rocketseat33.planner.domain.services.AtividadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

    final AtividadeService atividadeService;

    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

}
