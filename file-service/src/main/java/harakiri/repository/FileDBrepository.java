package harakiri.repository;

import harakiri.model.FileDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBrepository extends MongoRepository<FileDB, String> {

}
