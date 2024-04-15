package harakiri.repository;

import harakiri.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
//    @Query("SELECT DISTINCT m FROM MessageEntity m " +
//            "LEFT JOIN FETCH m.comments c " +
//            "LEFT JOIN FETCH m.likesDislikes l")
//    Iterable<MessageEntity> findAllWithCommentsAndLikes();

    @Override
    Iterable<MessageEntity> findAll();
}
