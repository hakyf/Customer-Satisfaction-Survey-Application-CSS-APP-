package id.co.mii.clientapp.util;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution)
            throws IOException {

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (!request.getURI().getPath().equals("/v1/login")) {
            request
                    .getHeaders()
                    .add(
                            "Authorization",
                            "Basic " +
                                    BasicHeader.createBasicToken(
                                            auth.getName(),
                                            auth.getCredentials()
                                                    .toString()));
        }

        ClientHttpResponse response = execution.execute(request, body);

        return response;
    }

}
