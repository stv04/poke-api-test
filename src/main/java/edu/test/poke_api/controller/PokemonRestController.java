package edu.test.poke_api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.test.poke_api.Dto.PokemonDto;
import edu.test.poke_api.Dto.PokemonQueriIn;
import edu.test.poke_api.Dto.ProgressLoadDto;
import edu.test.poke_api.entity.PokemonEntity;
import edu.test.poke_api.services.PokeApiService;
import edu.test.poke_api.services.PokeLoaderService;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonRestController {

    @Autowired
    private PokeApiService pokemonService;

    @Autowired
    private PokeLoaderService dataLoader;
    
    @GetMapping
    public ResponseEntity<List<PokemonEntity>> getAll(PokemonQueriIn filters) {
        return ResponseEntity.ok(pokemonService.getAll(filters));
    }

    @GetMapping("/{Id}")
    public PokemonEntity getOne(@PathVariable("Id") Long Id) {
        
        return pokemonService.getOneById(Id);
        
    }

    @PostMapping
    public PokemonEntity post(@RequestBody PokemonDto pokemon) {
        return pokemonService.save(pokemon);
    }

    @PatchMapping("/{Id}")
    public PokemonEntity update(@PathVariable("Id") Long Id, @RequestBody PokemonDto pokemon) {
        return pokemonService.update(Id, pokemon);
    }

    @GetMapping("/progressLoad")
    public ProgressLoadDto getProgressLoad() {
        return dataLoader.loadProgress();
    }
}
