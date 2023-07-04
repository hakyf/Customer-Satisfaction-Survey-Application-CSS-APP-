package id.co.mii.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.co.mii.clientapp.model.Parameter;

@Service
public class ParameterService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/parameter")
    private String url;

    public List<Parameter> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Parameter>>() {
                }).getBody();
    }

    public Parameter getById(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Parameter>() {
                }).getBody();
    }

    public Parameter create(Parameter parameter) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(parameter),
                new ParameterizedTypeReference<Parameter>() {
                }).getBody();
    }

    public Parameter update(Long id, Parameter parameter) {
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.PUT,
                new HttpEntity(parameter),
                new ParameterizedTypeReference<Parameter>() {
                }).getBody();
    }

    public Parameter delete(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Parameter>() {
                }).getBody();
    }
}
