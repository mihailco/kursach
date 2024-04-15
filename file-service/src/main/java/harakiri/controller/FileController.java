package harakiri.controller;

import harakiri.dto.response.ResponseMessage;
import harakiri.dto.response.SaveFileResponse;
import harakiri.mapper.FileMapper;
import harakiri.model.FileDB;
import harakiri.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
public class FileController {
    private final FileMapper fileMapper;
    private final FileService fileService;

    @PostMapping("/m/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            var t = fileMapper.saveFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(t.getFileId()));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping("/upload")
    public SaveFileResponse saveFile(@RequestBody FileDB file) {
        return  fileMapper.saveFile(file);
    }

    @GetMapping("/{id}")
    public FileDB getFile(@PathVariable String id) {
        return fileMapper.getFile(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        fileMapper.delete(id);
    }

//    @PostMapping("/convert")
//    public String convertFile(@RequestParam("file") MultipartFile multipartFile){
//
//    }
}
