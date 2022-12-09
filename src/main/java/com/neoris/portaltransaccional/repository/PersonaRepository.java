package com.neoris.portaltransaccional.repository;

import com.neoris.portaltransaccional.model.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonaRepository extends CrudRepository<Persona, Integer> {
}
