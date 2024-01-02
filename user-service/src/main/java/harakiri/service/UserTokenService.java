package harakiri.service;

import harakiri.entity.UserTokenEntity;
import harakiri.repository.UserTokenRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserTokenService {
    @PersistenceContext
    final private EntityManager entityManager;

    final private UserTokenRepository repository;

    public UserTokenEntity create(UserTokenEntity entity) {
        return repository.save(entity);
    }

    public void deleteExpiredTokens() {
        repository.deleteAllByExpiredAtBefore(new Date());
    }

    public int deleteByRefreshToken(String refreshToken){
        return repository.deleteByRefreshToken(refreshToken);
    }

    public void deleteByAccessToken(String accessToken){
        repository.deleteByAccessToken(accessToken);
    }
}
