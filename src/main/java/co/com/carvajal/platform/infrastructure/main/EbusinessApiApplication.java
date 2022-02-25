package co.com.carvajal.platform.infrastructure.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application initializer
 *
 * @author Carvajal
 * @version 1.0
 * @since 2020-04-13
 */
@SpringBootApplication(scanBasePackages = {"co.com.carvajal.platform.infrastructure",
        "co.com.carvajal.platform.modules", "co.com.carvajal.platform.crosscutting"})
public class EbusinessApiApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EbusinessApiApplication.class, args);
    }

}
