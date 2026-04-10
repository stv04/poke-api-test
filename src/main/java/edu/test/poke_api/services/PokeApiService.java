package edu.test.poke_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.test.poke_api.Dto.PokemonDto;
import edu.test.poke_api.client.PokeApiClient;
import edu.test.poke_api.entity.PokemonEntity;
import edu.test.poke_api.models.PokeRecord;
import edu.test.poke_api.repository.PokemonRepository;

@Service
public class PokeApiService {
    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokeApiClient external;

    public List<PokemonEntity> getAll() {
        List<PokemonEntity> result = new ArrayList<>();
        this.pokemonRepository.findAll().forEach(p -> result.add(p));

        return result;
    }

    public PokemonEntity getOneById(Integer id) {
        Optional<PokemonEntity> pokemonEntity = this.pokemonRepository.findById(id);
        PokemonEntity pokemon = pokemonEntity.orElse(null);

        if(pokemon == null) {
            System.err.println("Extracting from the poke api");
            PokeRecord originalPoke = external.getOne(id);
            pokemon = new PokemonEntity(originalPoke);
            pokemonRepository.save(pokemon);
        }

        return pokemon;
    }

    public PokemonEntity save(PokemonDto pokemon) {
        return pokemonRepository.save(new PokemonEntity(pokemon));
    }

    public PokemonEntity update(Integer id, PokemonDto pokemon) {
        PokemonEntity existentPokemon = new PokemonEntity(pokemon);
        existentPokemon.id = id;

        return pokemonRepository.save(existentPokemon);
    }

    public void delete(Integer id) {
        pokemonRepository.deleteById(id);
    }
}
