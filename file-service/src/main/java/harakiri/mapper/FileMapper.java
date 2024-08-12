package harakiri.mapper;

import harakiri.dto.api.respose.ConvertedFileResponse;
import harakiri.dto.api.respose.File;
import harakiri.dto.response.SaveFileResponse;
import harakiri.entity.FileDB;
import harakiri.security.filter.UserContextHolder;
import harakiri.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FileMapper {
    private final BasicMapper basicMapper;
    private final FileService fileService;

    public SaveFileResponse saveFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(
                Objects.requireNonNull(file.getOriginalFilename()));

        FileDB fileDB = FileDB.builder()
                .name(fileName)
                .userId(String.valueOf(UserContextHolder.getId()))
                .type(file.getContentType())
                .data(Arrays.toString(file.getBytes()))
                .build();

        return SaveFileResponse.builder()
                .fileId(fileDB.getId())
                .fileName(fileDB.getName())
                .build();
    }

    public SaveFileResponse saveFile(ConvertedFileResponse file) {

        File t = file.getFiles().get(0);


        FileDB fileDB = FileDB.builder()
                .name(t.getFileName())
                .userId(String.valueOf(UserContextHolder.getId()))
                .type(t.getFileExt())
                .data(t.getFileData())
                .build();

        fileDB = fileService.save(fileDB);

        return SaveFileResponse.builder()
                .fileId(fileDB.getId())
                .fileName(fileDB.getName())
                .build();
    }

    public FileDB getFile(String id) {
        return fileService.getFile(id);
    }

    public void delete(String id) {
        fileService.delete(id);
    }


    public SaveFileResponse saveFile(ConvertedFileResponse file, String courseId) {
        File t = file.getFiles().get(0);

        FileDB fileDB = FileDB.builder()
                .courseId(courseId)
                .name(t.getFileName())
                .userId(String.valueOf(UserContextHolder.getId()))
                .type(t.getFileExt())
                .data(t.getFileData())
                .build();

        fileDB = fileService.save(fileDB);

        return SaveFileResponse.builder()
                .fileId(fileDB.getId())
                .fileName(fileDB.getName())
                .build();
    }


    public SaveFileResponse saveFile(FileDB file) {
      var  fileDB = fileService.save(file);

        return SaveFileResponse.builder()
                .fileId(fileDB.getId())
                .fileName(fileDB.getName())
                .build();
    }
}
