package harakiri.security.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Component
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String accessSecret = "qweqweerkbbloug";

    private final int accessTtl = 60000000;

    private final String refreshSecret = "huiwiwjiwdjiwdhuiwg";

    private final int refreshTtl = 1728000000;
}
