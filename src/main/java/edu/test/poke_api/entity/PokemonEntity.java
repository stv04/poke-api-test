package edu.test.poke_api.entity;

import java.time.LocalDateTime;

import edu.test.poke_api.Dto.PokemonDto;
import edu.test.poke_api.models.PokeRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class PokemonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_seq")
    @SequenceGenerator(
        name = "pokemon_seq",
        sequenceName = "pokemon_sequence",
        initialValue = 1351
    )
    public Long id;

    @Column(unique = true)
    public Long externalId;

    public int height;
    public int baseExperience;
    public boolean isDefault;
    public String name;
    public String imageUrl;
    public int weight;
    public LocalDateTime createdAt = LocalDateTime.now();

    public PokemonEntity() {}

    public PokemonEntity(PokemonDto pokemom) {
        id = pokemom.id;
        externalId = pokemom.externalId;
        height = pokemom.height;
        baseExperience = pokemom.baseExperience;
        isDefault = pokemom.isDefault;
        name = pokemom.name;
        imageUrl = pokemom.imageUrl;
        weight = pokemom.weight;
    }

    public PokemonEntity(PokeRecord pokemom) {
        externalId = pokemom.id();
        height = pokemom.height();
        baseExperience = pokemom.base_experience();
        isDefault = pokemom.is_default();
        name = pokemom.name();
        imageUrl = pokemom.sprites().front_default();
        weight = pokemom.weight();
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public boolean getIsDefault() {
        return this.isDefault;
    }

    public void isDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
