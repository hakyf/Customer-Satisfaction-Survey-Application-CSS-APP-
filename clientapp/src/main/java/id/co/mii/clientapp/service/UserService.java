package id.co.mii.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.co.mii.clientapp.model.User;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/user")
    private String url;

    public List<User> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }).getBody();
    }

    public User getById(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<User>() {
                }).getBody();
    }

    public User create(User user) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(user),
                new ParameterizedTypeReference<User>() {
                }).getBody();
    }

    public User update(Long id, User user) {
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.PUT,
                new HttpEntity(user),
                new ParameterizedTypeReference<User>() {
                }).getBody();
    }

    public User delete(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<User>() {
                }).getBody();
    }
}
