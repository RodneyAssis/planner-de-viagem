package com.rocketseat33.planner.controller;

import com.rocketseat33.planner.domain.dto.ConvidadosDTO;
import com.rocketseat33.planner.domain.services.ConvidadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/convidado")
public class ConvidadoController {

    final ConvidadoService convidadoService;

    public ConvidadoController(ConvidadoService convidadoService) {
        this.convidadoService = convidadoService;
    }

    @GetMapping("/{id}/confirmar")
    public ResponseEntity<String> ConfirmarViagem(@PathVariable("id") Long id,
                                                  @RequestBody ConvidadosDTO convidadosDTO){
        var convidado = convidadoService.consultarConvidado(id);
        if (convidado.isPresent()){
            convidadoService.atualizarDadosEConfirmar(convidado.get(), convidadosDTO.getNome());
            return ResponseEntity.ok().body("Viagem confirmada!");
        }

        return ResponseEntity.notFound().build();
    }

}
