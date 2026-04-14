package edu.test.poke_api.models;

public record PokeRecord(int base_experience, int height, Long id, boolean is_default, String name, int order, SpritesRecord sprites, int weight) {
}
