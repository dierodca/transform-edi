package co.com.carvajal.platform.crosscutting.patterns.builders;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Builder para ejecutar restTemplate
 *
 * @author dierodca
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestTemplatePostBuilder {

    private String url;
    private Object data;

    public static RestTemplatePostBuilder builder() {
        return new RestTemplatePostBuilder();
    }

    public RestTemplatePostBuilder withUrl(final String url) {
        this.url = url;
        return this;
    }

    public RestTemplatePostBuilder withData(final Object data) {
        this.data = data;
        return this;
    }

    public <T> T buildPost(final Class<T> responseObject) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<Object> request = new HttpEntity<>(this.data, headers);
        return RestTemplateSingleton.getInstance().postForObject(this.url, request, responseObject);
    }
}
