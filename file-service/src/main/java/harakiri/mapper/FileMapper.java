package harakiri.mapper;

import harakiri.model.FileDB;
import harakiri.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FileMapper {
    private final BasicMapper basicMapper;
    private final FileService fileService;

    public FileDB saveFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(
                Objects.requireNonNull(file.getOriginalFilename()));

        FileDB fileDB = FileDB.builder()
                .name(fileName)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();

        return fileService.save(fileDB);
    }

    public FileDB getFile(String id) {
        return fileService.getFile(id);
    }

    public void delete(String id) {
        fileService.delete(id);
    }
}
