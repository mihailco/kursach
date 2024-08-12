package harakiri.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Getter
@Component
//@ConfigurationProperties(prefix = "convertapi")
public class ConverterProperties {

//    @Value("${CLIENT_SECRET}")
    private String clientSecret="";

//    @Value("${CLIENT_TOKEN}")
    private String clientToken="";

}
