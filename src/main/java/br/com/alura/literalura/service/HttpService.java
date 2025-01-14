package br.com.alura.literalura.service;

import br.com.alura.literalura.exception.HttpRequestException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {

    private final HttpClient client;

    public HttpService() {
        this.client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    public String fetchData(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new HttpRequestException("Failed to fetch data. HTTP status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new HttpRequestException("Error occurred while fetching data: " + e.getMessage(), e);
        }
    }
}
