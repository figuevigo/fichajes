package com.jcromero.fichajes.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

class FichajesRestClient {
    private String endpoint = "http://localhost:8080/api/fichajes";
    private HttpClient client = HttpClient.newHttpClient();
    
    @Test
    void sendFichajeDesdeCadena() throws IOException, InterruptedException {
        
        String json="["
                        + "{\"businessId\": \"1\", \"date\": \"2018-01-01T08:00:00.000Z\", \"employeeId\": \"222222222\", \"recordType\": \"IN\", \"serviceId\": \"ALBASANZ\",  \"type\": \"WORK\"}"
                        + ",{\"businessId\": \"1\", \"date\": \"2018-01-01T08:00:00.000Z\", \"employeeId\": \"222222222\", \"recordType\": \"IN\", \"serviceId\": \"ALBASANZ\",  \"type\": \"WORK\"}"
                  + "]";
        
        HttpRequest request = buildHttpRequestFromBodyPublishers(HttpRequest.BodyPublishers.ofString(json));
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        assertNotNull(response);
        assertEquals(200, response.statusCode());
    }
    
    @Test
    void sendFichajesDesdeFiles() throws IOException, InterruptedException, URISyntaxException {
        List<String> ficherosPrueba = List.of(new String[]{"fichero_1.json", "fichero_2.json"});
        for(String nombreFichero : ficherosPrueba) {
            var response = sendFichajesDesdeFile(nombreFichero);    
            assertNotNull(response);
            assertEquals(200, response.statusCode());            
        }
    }    
    
    private HttpResponse sendFichajesDesdeFile(String nameResource) throws IOException, InterruptedException, URISyntaxException {
        BodyPublisher bodyPublisher = buildBodyPublisherFromSystemResource(nameResource);
        HttpRequest request = buildHttpRequestFromBodyPublishers(bodyPublisher);
        return client.send(request, HttpResponse.BodyHandlers.ofString());    
    }

    private BodyPublisher buildBodyPublisherFromSystemResource(String nameResource) throws URISyntaxException, FileNotFoundException {
        Path path = Path.of(ClassLoader.getSystemResource(nameResource).toURI());
        return HttpRequest.BodyPublishers.ofFile(path);
    }
    
    private HttpRequest buildHttpRequestFromBodyPublishers(BodyPublisher bodyPublisher) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(bodyPublisher)
                .build();  
        return request;
    }

}
