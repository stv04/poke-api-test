package edu.test.poke_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.test.poke_api.Dto.PokemonDto;
import edu.test.poke_api.Dto.PokemonQueriIn;
import edu.test.poke_api.entity.PokemonEntity;
import edu.test.poke_api.services.PokeApiService;

@Controller
public class PokemonController {

    @Autowired
    private PokeApiService pokemonService;

    @GetMapping
    public String List(Model model) {
        model.addAttribute("pokemons", pokemonService.getAll(new PokemonQueriIn()));
        return "pokemon-list";
    }

    @GetMapping("new")
    public String createPokemon(Model model) {
        model.addAttribute("pokemon", new PokemonEntity());
        return "pokemon-form";
    }

    @GetMapping("edit/{id}")
    public String updatePokemon(@PathVariable Long id, Model model) {
        PokemonEntity pokemon = this.pokemonService.getOneById(id);
        model.addAttribute("pokemon", pokemon);

        return "pokemon-form";
    }

    @PostMapping("save")
    public String save(@ModelAttribute("pokemon") PokemonEntity pokemon) {
        PokemonDto poke = new PokemonDto(pokemon);
        if(pokemon.id == null) {
            pokemonService.save(poke);
        } else {
            pokemonService.update(pokemon.id, poke);

        }
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        pokemonService.delete(id);
        return "redirect:/";
    }
}
