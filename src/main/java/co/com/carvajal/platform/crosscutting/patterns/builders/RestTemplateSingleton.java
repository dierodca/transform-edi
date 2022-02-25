package co.com.carvajal.platform.crosscutting.patterns.builders;

import org.springframework.web.client.RestTemplate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Singleton RestTemplate
 *
 * @author dierodca
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestTemplateSingleton {

    private static RestTemplate instance;

    public static RestTemplate getInstance() {
        if (instance == null) {
            instance = new RestTemplate();
        }
        return instance;
    }
}
