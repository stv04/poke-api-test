package edu.test.poke_api.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import edu.test.poke_api.interceptors.ResponseHttpException;
import edu.test.poke_api.models.PokeRecord;

@Service
public class PokeApiClient {
    public PokeRecord getOne(Long id) {
        RestClient restClient = RestClient.create();
        String uri = String.format("https://pokeapi.co/api/v2/pokemon/%d", id);
        System.err.println(String.format("THE FINAL URL: %s", uri));

        PokeRecord resultApi = restClient.get()
        .uri(uri)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, (request, res) -> {
            throw new ResponseHttpException(String.format("Error 4xx detectado al obtener pokemon del pokeapi (%s): %s", uri, res.getStatusCode()), HttpStatus.CONFLICT);
        })
        .onStatus(HttpStatusCode::is5xxServerError, (request, res) -> {
            throw new ResponseHttpException(String.format("Error 5xx detectado al obtener pokemon del pokeapi (%s): %s", uri, res.getStatusCode()), HttpStatus.CONFLICT);
        })
        .body(PokeRecord.class);

        return resultApi;
    }
}
