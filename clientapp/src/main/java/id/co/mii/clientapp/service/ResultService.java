package id.co.mii.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.co.mii.clientapp.model.Result;

@Service
public class ResultService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/result")
    private String url;

    public List<Result> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Result>>() {
                }).getBody();
    }

    public Result getById(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Result>() {
                }).getBody();
    }

    public Result create(Result result) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(result),
                new ParameterizedTypeReference<Result>() {
                }).getBody();
    }

    public Result update(Long id, Result result) {
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.PUT,
                new HttpEntity(result),
                new ParameterizedTypeReference<Result>() {
                }).getBody();
    }

    public Result delete(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Result>() {
                }).getBody();
    }
}
