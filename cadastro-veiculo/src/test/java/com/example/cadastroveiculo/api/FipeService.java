package com.example.cadastroveiculo.api;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
@Configuration
public class FipeService {

    private static final String BASE_URL = "https://veiculos.fipe.org.br/";

//    public JSONObject consultarPrecoFipe(String tipoVeiculo, String marca, String modelo, String ano) throws Exception {
//        URI uri = new URIBuilder(BASE_URL + "api/veiculos/ConsultarValorComTodosParametros").data("codigoTabelaReferencia", "0")
//                .data("codigoTipoVeiculo", tipoVeiculo)
//                .data("codigoMarca", marca)
//                .data("codigoModelo", modelo)
//                .data("anoModelo", ano)
//                .data("codigoTipoCombustivel", "1")
//                .data("tipoVeiculo", "carro")
//                .data("tipoConsulta", "tradicional").build();
//        HttpGet request = new HttpGet(uri);
//        String response = HttpClientBuilder.create().build().execute(request, httpResponse -> {
//            int status = httpResponse.getStatusLine().getStatusCode();
//            if (status >= 200 && status < 300) {
//                return EntityUtils.toString(httpResponse.getEntity());
//            } else {
//                throw new Exception("Erro ao realizar chamada à tabela FIPE: " + status);
//            }
//        });
//        return new JSONObject(response);
//    }
}
