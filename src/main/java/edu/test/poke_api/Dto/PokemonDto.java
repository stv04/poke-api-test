package edu.test.poke_api.Dto;

import edu.test.poke_api.entity.PokemonEntity;
import edu.test.poke_api.models.PokeRecord;

public class PokemonDto {
    public Long id;
    public Long externalId;
    public int height;
    public int baseExperience;
    public boolean isDefault;
    public String name;
    public int order;
    public String imageUrl;
    public int weight;

    PokemonDto() {}

    PokemonDto(PokeRecord record) {
        this.externalId = record.id();
        this.height = record.height();
        this.baseExperience = record.base_experience();
        this.baseExperience = record.base_experience();
        this.isDefault = record.is_default();
        this.name = record.name();
        this.order = record.order();
        this.imageUrl = record.sprites().front_default();
        this.weight = record.weight();
    }

    public PokemonDto(PokemonEntity pokemom) {
        id = pokemom.id;
        externalId = pokemom.externalId;
        height = pokemom.height;
        baseExperience = pokemom.baseExperience;
        isDefault = pokemom.isDefault;
        name = pokemom.name;
        imageUrl = pokemom.imageUrl;
        weight = pokemom.weight;
    }
}
