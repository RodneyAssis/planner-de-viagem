package com.rocketseat33.planner.domain.services;

import com.rocketseat33.planner.domain.dto.ConvidadosDTO;
import com.rocketseat33.planner.domain.dto.ViagemDTO;
import com.rocketseat33.planner.domain.models.entities.Convidado;
import com.rocketseat33.planner.domain.models.entities.Viagem;
import com.rocketseat33.planner.domain.models.repositories.IConvidado;
import com.rocketseat33.planner.domain.models.repositories.IViagem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ViagemService {

    final IViagem iViagem;
    final IConvidado iConvidado;

    public ViagemService(IViagem iViagem, IConvidado iConvidado) {
        this.iViagem = iViagem;
        this.iConvidado = iConvidado;
    }


    public Viagem cadastrarViagem(ViagemDTO viagemDTO) {
        var viagem = new Viagem();
        var convidado = new Convidado();
        var listaDeConvidados = new ArrayList<Convidado>();
        BeanUtils.copyProperties(viagemDTO, viagem);

        for (ConvidadosDTO convidadosDTO : viagemDTO.getConvidados()) {
            BeanUtils.copyProperties(convidadosDTO, convidado);
            listaDeConvidados.add(convidado);
            iConvidado.save(convidado);
        }

        viagem.setConvidados(listaDeConvidados);
        iViagem.save(viagem);

        return viagem;
    }

    public Optional<Viagem> consultar(UUID id) {
        return iViagem.findById(id);
    }

    public Object atualizar(Viagem viagem) {
        return iViagem.save(viagem);
    }

    public void confirmaViagem(Viagem viagem) {
        viagem.setConfirmacao(true);
        iViagem.save(viagem);
    }

    public String vincularConvidadoAViagem(Viagem viagem, Set<ConvidadosDTO> convidados) {
        List<Convidado> listaConvidados = new ArrayList<>();
        for(ConvidadosDTO convidadosDTO: convidados) {
            if (viagem.getConvidados().stream().map(Convidado::getEmail)
                    .anyMatch(email -> email.equals(convidadosDTO.getEmail())))
                continue;
            Convidado convidado = new Convidado();
            BeanUtils.copyProperties(convidadosDTO, convidado);
            convidado.setViagens(Collections.singletonList(viagem));
            listaConvidados.add(convidado);
        }

        if (listaConvidados.isEmpty())
            return "Este(s) e-mail estão invalidos ou já foram enviados para os responsáveis";

        listaConvidados.addAll(viagem.getConvidados());

        iConvidado.saveAll(listaConvidados);
        viagem.setConvidados(listaConvidados);
        if(!viagem.getConvidados().isEmpty()) {
            iViagem.save(viagem);
            return "E-mail(s) redirecionados para os respectivos responsáveis.";
        }
        return "Algo deu errado ao encaminhar o(s) email(s). Por favor entre em " +
                "contato com o suporte informando a situação";
    }

    public List<Convidado> consultarParticipantes(UUID id) {
        return iViagem.findById(id).stream().map(Viagem::getConvidados).toList().get(0);
    }
}
