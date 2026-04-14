package edu.test.poke_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import edu.test.poke_api.Dto.PokemonDto;
import edu.test.poke_api.Dto.PokemonQueriIn;
import edu.test.poke_api.client.PokeApiClient;
import edu.test.poke_api.entity.PokemonEntity;
import edu.test.poke_api.interceptors.ResponseHttpException;
import edu.test.poke_api.models.PokeRecord;
import edu.test.poke_api.repository.PokemonRepository;
import jakarta.transaction.Transactional;

@Service
public class PokeApiService {
    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokeApiClient external;

    @Value("${app.pokemon.max_limit_external}")
    private int maxExternalPokeapiId;

    public List<PokemonEntity> getAll(PokemonQueriIn filter) {
        
        if(filter.externalId != null) {
            return List.of(this.getOneByExternalId(filter.externalId));
        } else if ( filter.name != null ) {
            return pokemonRepository.findByNameContaining(filter.name);
        }        

        return this.pokemonRepository.findAll();
    }

    public PokemonEntity getOneById(Long id) {
        Optional<PokemonEntity> pokemonEntity = this.pokemonRepository.findById(id);
        PokemonEntity pokemon = pokemonEntity.orElse(null);

        if(pokemon == null) {
            throw new ResponseHttpException(String.format("The pokemon with the principal id (%d) doesn't exists.", id), HttpStatus.NOT_FOUND);
        }

        return pokemon;
    }

    public PokemonEntity getOneByExternalId(Long externalId) {
        if(externalId > this.maxExternalPokeapiId) 
            throw new ResponseHttpException(String.format("The external id that you're trying to search (%d) is higer than the maximun allowed (%d)", externalId, maxExternalPokeapiId), HttpStatus.CONFLICT);
        
        PokemonEntity pokemon = this.pokemonRepository.getOneByExternalId(externalId);

        if(pokemon == null) {
            System.err.println("Extracting from the poke api");
            PokeRecord originalPoke = external.getOne(externalId);
            pokemon = new PokemonEntity(originalPoke);
            pokemonRepository.save(pokemon);
        }

        return pokemon;
    }

    @Transactional
    public PokemonEntity save(PokemonDto pokemon) {
        if(pokemon.externalId != null && pokemonRepository.existsByExternalId(pokemon.externalId)) 
            throw new ResponseHttpException(String.format("The pokemon with the external id %d already exists", pokemon.externalId), HttpStatus.NOT_ACCEPTABLE);

        PokemonEntity entity = pokemonRepository.save(new PokemonEntity(pokemon));

        if (entity.getExternalId() == null) {
            entity.setExternalId(entity.getId());
            return pokemonRepository.save(entity); 
        }
        
        return entity;
    }

    public PokemonEntity update(Long id, PokemonDto pokemon) {
        PokemonEntity existentPokemon = pokemonRepository.findById(id).orElse(null);

        if(existentPokemon == null) 
            throw new ResponseHttpException(String.format("The pokemon with the id: %d doesn't exists", id), HttpStatus.NOT_FOUND);

        System.out.println(String.format("External id existent: %d ------ External Id created: %d", existentPokemon.externalId, pokemon.externalId));
        if(existentPokemon.externalId != pokemon.externalId)
            throw new ResponseHttpException(String.format("The pokemon with id %d can't change it's external id, try to create a new pokemon", pokemon.externalId), HttpStatus.CONFLICT);

        PokemonEntity pokemonToSave = new PokemonEntity(pokemon);

        return pokemonRepository.save(pokemonToSave);
    }

    public void delete(Long id) {
        pokemonRepository.deleteById(id);
    }
}
