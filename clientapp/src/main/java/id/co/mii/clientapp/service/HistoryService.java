package id.co.mii.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.co.mii.clientapp.model.History;

@Service
public class HistoryService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/history")
    private String url;

    public List<History> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<History>>() {
                }).getBody();
    }

    public History getById(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<History>() {
                }).getBody();
    }

    public History create(History history) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(history),
                new ParameterizedTypeReference<History>() {
                }).getBody();
    }

    public History update(Long id, History history) {
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.PUT,
                new HttpEntity(history),
                new ParameterizedTypeReference<History>() {
                }).getBody();
    }

    public History delete(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<History>() {
                }).getBody();
    }
}
