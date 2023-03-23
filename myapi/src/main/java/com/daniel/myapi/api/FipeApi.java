package com.daniel.myapi.api;

import com.daniel.myapi.domain.Marca;
import com.daniel.myapi.domain.Modelo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.math.BigDecimal;

public class FipeApi {

    private final WebClient webClient;

    public FipeApi() {
        this.webClient = WebClient.builder()
                .baseUrl("https://parallelum.com.br/fipe/api/v1")
                .build();
    }

    public Mono<Marca> getMarcaPorId(int marcaId) {
        return webClient.get()
                .uri("/carros/marcas/{id}", marcaId)
                .retrieve()
                .bodyToMono(Marca.class);
    }

    public Mono<Modelo> getModeloPorId(int marcaId, int modeloId) {
        return webClient.get()
                .uri("/carros/marcas/{marcaId}/modelos/{modeloId}", marcaId, modeloId)
                .retrieve()
                .bodyToMono(Modelo.class);
    }

    public Mono<Double> getValorFipe(int marcaId, int modeloId, int ano) {
        return webClient.get()
                .uri("/carros/marcas/{marcaId}/modelos/{modeloId}/anos/{ano}", marcaId, modeloId, ano)
                .retrieve()
                .bodyToMono(FipeResponse.class)
                .map(FipeResponse::getValor);
    }

    public BigDecimal consultarPrecoFipe(Marca marca, Modelo modelo, Integer ano) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://url-da-api-fipe/{marcaId}/{modeloId}/{ano}";

        // Substitua os parâmetros da URL pelos valores fornecidos
        url = url.replace("{marcaId}", marca.getId().toString())
                .replace("{modeloId}", modelo.getId().toString())
                .replace("{ano}", ano.toString());

        // Faça a chamada à API externa e obtenha a resposta
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Verifique se a resposta é bem-sucedida
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            // Converta a resposta em um objeto JSON e extraia o preço da tabela FIPE
            String json = response.getBody();
            BigDecimal precoFipe = extrairPrecoFipeDoJson(json);

            return precoFipe;
        } else {
            throw new RuntimeException("Não foi possível obter o preço da tabela FIPE.");
        }
    }

    private BigDecimal extrairPrecoFipeDoJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);

            // Acesse o campo de preço no objeto JSON (ajuste o nome do campo conforme necessário)
            String precoFipeString = jsonNode.get("preco_fipe").asText();

            // Converta a string do preço em BigDecimal
            BigDecimal precoFipe = new BigDecimal(precoFipeString);

            return precoFipe;
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível extrair o preço da tabela FIPE do JSON.", e);
        }
    }
}

