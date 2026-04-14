package edu.test.poke_api.Dto;

public class PokemonQueriIn {
    public Long externalId;
    public String name;
    public Long getExternalId() {
        return externalId;
    }
    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
