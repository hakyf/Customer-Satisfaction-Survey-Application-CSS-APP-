package id.co.mii.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.co.mii.clientapp.model.Question;

@Service
public class QuestionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/question")
    private String url;

    public List<Question> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Question>>() {
                }).getBody();
    }

    public Question getById(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Question>() {
                }).getBody();
    }

    public Question create(Question question) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(question),
                new ParameterizedTypeReference<Question>() {
                }).getBody();
    }

    public Question update(Long id, Question question) {
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.PUT,
                new HttpEntity(question),
                new ParameterizedTypeReference<Question>() {
                }).getBody();
    }

    public Question delete(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Question>() {
                }).getBody();
    }
}
