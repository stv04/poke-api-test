package edu.test.poke_api.models;

public record PokeRecord(int base_experience, int height, Integer id, boolean is_default, String name, int order, SpritesRecord sprites, int weight) {
}
