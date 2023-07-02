package id.co.mii.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.co.mii.clientapp.model.Section;

@Service
public class SectionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/section")
    private String url;

    public List<Section> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Section>>() {
                }).getBody();
    }

    public Section getById(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Section>() {
                }).getBody();
    }

    public Section create(Section section) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(section),
                new ParameterizedTypeReference<Section>() {
                }).getBody();
    }

    public Section update(Long id, Section section) {
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.PUT,
                new HttpEntity(section),
                new ParameterizedTypeReference<Section>() {
                }).getBody();
    }

    public Section delete(Long id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Section>() {
                }).getBody();
    }
}
