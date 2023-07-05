package id.co.mii.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.co.mii.clientapp.model.Answer;

@Service
public class AnswerService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/answer")
    private String url;

    public List<Answer> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Answer>>() {
                }).getBody();
    }

    public Answer getById(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Answer>() {
                }).getBody();
    }

    public Answer create(Answer answer) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(answer),
                new ParameterizedTypeReference<Answer>() {
                }).getBody();
    }

    public Answer update(Long id, Answer answer) {
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.PUT,
                new HttpEntity(answer),
                new ParameterizedTypeReference<Answer>() {
                }).getBody();
    }

    public Answer delete(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Answer>() {
                }).getBody();
    }
}
