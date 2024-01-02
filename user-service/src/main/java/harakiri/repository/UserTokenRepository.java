package harakiri.repository;


import harakiri.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Integer> {
    void deleteAllByExpiredAtBefore(Date date);
    @Transactional
    int deleteByRefreshToken(String refresh);
    @Transactional
    void deleteByAccessToken(String accessToken);
}
