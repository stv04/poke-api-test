package edu.test.poke_api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import edu.test.poke_api.Dto.ProgressLoadDto;
import edu.test.poke_api.client.PokeApiClient;
import edu.test.poke_api.entity.PokemonEntity;
import edu.test.poke_api.models.PokeRecord;
import edu.test.poke_api.repository.PokemonRepository;

@Service
public class PokeLoaderService {
    private int maxLoad = 65;
    private int batchUpdate = 10;
    private int savingProgress = 0;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokeApiClient pokeApiClient;

    @Async
    public void loadAllPokemons() {
        System.err.println("Iniciando proceso de carga en segundo plano");

        List<PokemonEntity> pokemonList = new ArrayList<>();

        for ( int i = 1; i <= maxLoad; i++ ) {
            try {
                PokeRecord originalPokemon = pokeApiClient.getOne(i);
                PokemonEntity newPokemon = new PokemonEntity(originalPokemon);
                pokemonList.add(newPokemon);
    
                if( i % batchUpdate == 0 ) {
                    this.pokemonRepository.saveAll(pokemonList);
                    pokemonList.clear();
                    savingProgress = i;
                    System.err.println(String.format("%d records have been saved", i));
                }

            } catch (Exception e) {
                pokemonList.clear();
                System.err.println(String.format("Error Saving Records in ID (%d): %s", i, e.getMessage()));
            }
        }

        pokemonRepository.saveAll(pokemonList);
        pokemonList.clear();
        savingProgress = maxLoad;
        System.err.println("Batch update finished!");

    }

    public ProgressLoadDto loadProgress() {
        return new ProgressLoadDto(maxLoad, savingProgress);
    }
}
