package harakiri.controller;

import harakiri.dto.ResponseMessage;
import harakiri.mapper.FileMapper;
import harakiri.model.FileDB;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
public class FileController {
    private final FileMapper fileMapper;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String userId) {
        String message = "";
        try {
            var t = fileMapper.saveFile(file);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(t.getId()));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = fileMapper.getFile(id);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"").body(fileDB.getData());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        fileMapper.delete(id);
    }
}
