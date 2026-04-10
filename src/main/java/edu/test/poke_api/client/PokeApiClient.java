package edu.test.poke_api.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import edu.test.poke_api.models.PokeRecord;

@Service
public class PokeApiClient {
    public PokeRecord getOne(Integer id) {
        RestClient restClient = RestClient.create();
        String uri = String.format("https://pokeapi.co/api/v2/pokemon/%d", id);
        System.err.println(String.format("THE FINAL URL: %s", uri));

        PokeRecord resultApi = restClient.get()
        .uri(uri)
        .retrieve()
        .body(PokeRecord.class);

        return resultApi;
    }
}
