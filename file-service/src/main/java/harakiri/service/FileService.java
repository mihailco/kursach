package harakiri.service;

import harakiri.model.FileDB;
import harakiri.repository.FileDBrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileDBrepository repository;

    public FileDB getFile(String id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public FileDB save(FileDB fileDB) {
        return repository.save(fileDB);
    }
}
