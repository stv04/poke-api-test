package edu.test.poke_api.entity;

import edu.test.poke_api.Dto.PokemonDto;
import edu.test.poke_api.models.PokeRecord;
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
        sequenceName = "pokemon_sequence"

    )
    public Integer id;

    public Integer external_id;

    public int height;
    public int base_experience;
    public boolean is_default;
    public String name;
    public String imageUrl;
    public int weight;

    public PokemonEntity() {}

    public PokemonEntity(PokemonDto pokemom) {
        external_id = pokemom.id;
        height = pokemom.height;
        base_experience = pokemom.base_experience;
        is_default = pokemom.is_default;
        name = pokemom.name;
        imageUrl = pokemom.imageUrl;
        weight = pokemom.weight;
    }

    public PokemonEntity(PokeRecord pokemom) {
        external_id = pokemom.id();
        height = pokemom.height();
        base_experience = pokemom.base_experience();
        is_default = pokemom.is_default();
        name = pokemom.name();
        imageUrl = pokemom.sprites().front_default();
        weight = pokemom.weight();
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
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
