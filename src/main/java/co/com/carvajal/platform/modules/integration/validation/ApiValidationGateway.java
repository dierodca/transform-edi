package co.com.carvajal.platform.modules.integration.validation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import co.com.carvajal.foundation.framework.stereotypes.Gateway;
import co.com.carvajal.platform.crosscutting.patterns.builders.RestTemplatePostBuilder;
import co.com.carvajal.platform.modules.integration.validation.model.DocumentRequest;

/**
 * Gateway para consimir el api de validaciones
 *
 * @author dierodca
 *
 */
@Gateway
public class ApiValidationGateway {

    @Value("${integration.validation.url}")
    private String url;

    public String validateDocument(final String file, final String documentType) {
        return RestTemplatePostBuilder.builder().withUrl(this.url)
                .withData(DocumentRequest.builder().file(file).setupId(documentType).build())
                .buildPost(String.class);
    }

    public HttpURLConnection getHttpURLConnection() throws IOException {
        final URL urlToValidate = new URL(this.url);
        return (HttpURLConnection) urlToValidate.openConnection();
    }
}
