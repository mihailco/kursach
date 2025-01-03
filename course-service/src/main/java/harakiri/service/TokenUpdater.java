package harakiri.service;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TokenUpdater {

    private final TokenService tokenService;

    public TokenUpdater(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostConstruct
    public void init() {
        tokenService.updateToken();
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void scheduledTokenUpdate() {
        tokenService.updateToken();
    }
}
