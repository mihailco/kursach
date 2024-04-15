package harakiri.configuration;

import feign.Logger;
import me.bvn13.openfeign.logger.normalized.NormalizedFeignLogger;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public Logger logger() {
        return new NormalizedFeignLogger();
    }

    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.FULL;
    }
}
