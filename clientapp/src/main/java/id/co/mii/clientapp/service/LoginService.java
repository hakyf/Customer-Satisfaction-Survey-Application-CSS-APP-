package id.co.mii.clientapp.service;

import id.co.mii.clientapp.model.dto.request.LoginRequest;
import id.co.mii.clientapp.model.dto.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/login")
    private String url;

    public boolean login(LoginRequest loginRequest) {
        try {
            ResponseEntity<LoginResponse> res = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity(loginRequest),
                    new ParameterizedTypeReference<LoginResponse>() {
                    });
            if (res.getStatusCode() == HttpStatus.OK) {
                setPrinciple(res.getBody(), loginRequest.getPassword());
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void setPrinciple(LoginResponse response, String pass) {
        List<SimpleGrantedAuthority> authorities = response.getAuthorities()
                .stream().map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                response.getUsername(),
                pass,
                authorities);

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}