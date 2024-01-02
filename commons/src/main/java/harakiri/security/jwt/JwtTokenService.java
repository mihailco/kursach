package harakiri.security.jwt;

public interface JwtTokenService {
    String generateJwtToken(String username, long id, int expirationMs, String secret);

    String getUserNameFromJwtToken(String token);

    long getUserIdFromJwtToken(String token);

    boolean validateJwtToken(String authToken, String secret);
}
