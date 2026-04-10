package edu.test.poke_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import edu.test.poke_api.repository.PokemonRepository;
import edu.test.poke_api.services.PokeLoaderService;

@Configuration
@EnableAsync
public class PokemonDataLoader {
    
    @Bean
    CommandLineRunner initializeData(PokeLoaderService loaderService, PokemonRepository repository) {
        return args -> {
            loaderService.loadAllPokemons();
            System.err.println("Loading all pokemons, you can work in other process");
        };
    }
}
