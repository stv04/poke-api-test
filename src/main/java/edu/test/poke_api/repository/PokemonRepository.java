package edu.test.poke_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.test.poke_api.entity.PokemonEntity;

public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {
    boolean existsByExternalId(Long externalId);
    PokemonEntity getOneByExternalId(Long externalId);

    List<PokemonEntity> findByNameContaining(String name);
}
