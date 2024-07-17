package com.rocketseat33.planner.domain.models.repositories;

import com.rocketseat33.planner.domain.models.entities.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAtividade extends JpaRepository<Atividade, Integer> {

}
