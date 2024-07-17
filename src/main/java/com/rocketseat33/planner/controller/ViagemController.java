package com.rocketseat33.planner.controller;

import com.rocketseat33.planner.domain.dto.AtualizarViagemDTO;
import com.rocketseat33.planner.domain.dto.ViagemDTO;
import com.rocketseat33.planner.domain.models.entities.Convidado;
import com.rocketseat33.planner.domain.models.entities.Viagem;
import com.rocketseat33.planner.domain.services.ViagemService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/viagem")
public class ViagemController {

    final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Viagem>> ConsultarViagemPorID(@PathVariable(name = "id") UUID id){
        if (viagemService.consultar(id).isPresent())
            return ResponseEntity.ok().body(viagemService.consultar(id));
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}/convidados")
    public List<Convidado> ConsultarConvidados(@PathVariable(name = "id") UUID id){
        return viagemService.consultarParticipantes(id);
    }

    @GetMapping("{id}/confirmar")
    public ResponseEntity<String> ConfirmacaoUsuario(@PathVariable(name = "id") UUID id){
        var viagem = viagemService.consultar(id);
        if (viagem.isPresent()) {
            viagemService.confirmaViagem(viagem.get());
            return ResponseEntity.ok().body("Viagem confirmada! Será enviado uma mensagem de " +
                    "confirmação para os seus convidados.");
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> CadastrarViagem(@RequestBody ViagemDTO viagemDTO){
        var viagem = viagemService.cadastrarViagem(viagemDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(viagem);
    }

    @PutMapping("/{idViagem}/add-convidado")
    public ResponseEntity<String> AdicionarConvidados(@PathVariable(name = "idViagem") UUID idViagem,
                                                      @RequestBody ViagemDTO viagemDTO){
        var viagem = viagemService.consultar(idViagem);
        if (viagem.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem não registrada ou não localizada.");
        if (!viagem.get().isConfirmacao())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Só poderá confirmar após" +
                    " o responsável da viagem confirmar a solicitação");

        return ResponseEntity.ok(viagemService.vincularConvidadoAViagem(viagem.get(), viagemDTO.getConvidados()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> AtualizarViagem(@PathVariable(name = "id") UUID id,
                                                  @RequestBody AtualizarViagemDTO atualizarViagem){

        var viagem = viagemService.consultar(id);
        if (viagem.isPresent()){
            BeanUtils.copyProperties(atualizarViagem, viagem.get());
            return ResponseEntity.ok(viagemService.atualizar(viagem.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
