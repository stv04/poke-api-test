package edu.test.poke_api.Dto;

import edu.test.poke_api.entity.PokemonEntity;
import edu.test.poke_api.models.PokeRecord;

public class PokemonDto {
    public Integer id;
    public int height;
    public int base_experience;
    public boolean is_default;
    public String name;
    public int order;
    public String imageUrl;
    public int weight;

    PokemonDto() {}

    PokemonDto(PokeRecord record) {
        this.id = record.id();
        this.height = record.height();
        this.base_experience = record.base_experience();
        this.base_experience = record.base_experience();
        this.is_default = record.is_default();
        this.name = record.name();
        this.order = record.order();
        this.imageUrl = record.sprites().front_default();
        this.weight = record.weight();
    }

    public PokemonDto(PokemonEntity pokemom) {
        id = pokemom.id;
        height = pokemom.height;
        base_experience = pokemom.base_experience;
        is_default = pokemom.is_default;
        name = pokemom.name;
        imageUrl = pokemom.imageUrl;
        weight = pokemom.weight;
    }
}
