package edu.test.poke_api.repository;

import org.springframework.data.repository.CrudRepository;

import edu.test.poke_api.entity.PokemonEntity;

public interface PokemonRepository extends CrudRepository<PokemonEntity, Integer> {

}
