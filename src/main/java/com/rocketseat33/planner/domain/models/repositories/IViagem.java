package com.rocketseat33.planner.domain.models.repositories;

import com.rocketseat33.planner.domain.models.entities.Convidado;
import com.rocketseat33.planner.domain.models.entities.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface IViagem extends JpaRepository<Viagem, UUID> {

}
