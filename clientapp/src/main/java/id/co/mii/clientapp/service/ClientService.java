package id.co.mii.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.co.mii.clientapp.model.Client;

@Service
public class ClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/client")
    private String url;

    public List<Client> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Client>>() {
                }).getBody();
    }

    public Client getById(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Client>() {
                }).getBody();
    }

    public Client create(Client client) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(client),
                new ParameterizedTypeReference<Client>() {
                }).getBody();
    }

    public Client update(Long id, Client client) {
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.PUT,
                new HttpEntity(client),
                new ParameterizedTypeReference<Client>() {
                }).getBody();
    }

    public Client delete(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Client>() {
                }).getBody();
    }
}
