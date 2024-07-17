package com.rocketseat33.planner.domain.models.repositories;

import com.rocketseat33.planner.domain.models.entities.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IConvidado extends JpaRepository<Convidado, Long> {

    Optional<Convidado> findByEmail(String email);
}
