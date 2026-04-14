package edu.test.poke_api.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    private long savingProgress = 0;
    private int pokemonsPerMinute = 15; // Initial requirement 15 pokemon per minute

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokeApiClient pokeApiClient;

    @Async
    public void loadAllPokemons() {
        System.out.println("Iniciando proceso de carga en segundo plano");
        if( pokemonsPerMinute == 0 ) {
            System.err.println("Hes been decide not load any pokemons per minute.");
            return;
        } 
        ScheduledExecutorService sch = Executors.newSingleThreadScheduledExecutor();


        for ( long i = 1; i <= maxLoad; i++ ) {
            final Long idPokemon = i;
            sch.schedule(() -> {
                try {
                    LocalDateTime time = LocalDateTime.now();
                    
                    PokemonEntity existentPokemon = pokemonRepository.getOneByExternalId(idPokemon);
                    
                    if(existentPokemon == null || Duration.between(existentPokemon.createdAt, time).toHours() > 4) {
                        if(existentPokemon != null )
                            System.out.println("The duration: " + Duration.between(existentPokemon.createdAt, time).toHours());

                        PokeRecord originalPokemon = pokeApiClient.getOne(idPokemon);
                        PokemonEntity newPokemon = new PokemonEntity(originalPokemon);
                        savingProgress = idPokemon;
                        pokemonRepository.save(newPokemon);
                    } else {
                        System.out.println("Pokemon already exists");
                    }

                    if( idPokemon % batchUpdate == 0 ) {
                        System.err.println(String.format("%d records have been processed", idPokemon));
                    }
    
                } catch (Exception e) {
                    System.err.println(String.format("Error Saving Records in ID (%d): %s", idPokemon, e.getMessage()));
                }
            }, i * 60 / pokemonsPerMinute, TimeUnit.SECONDS);
        }

        savingProgress = maxLoad;

    }

    public ProgressLoadDto loadProgress() {
        return new ProgressLoadDto(maxLoad, savingProgress);
    }
}
