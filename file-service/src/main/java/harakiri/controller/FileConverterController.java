package harakiri.controller;

import harakiri.dto.api.respose.ConvertedFileResponse;
import harakiri.dto.request.ConvertFileRequest;
import harakiri.dto.response.SaveFileResponse;
import harakiri.mapper.FileMapper;
import harakiri.service.ConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/file/convert")
public class FileConverterController {
    private final ConverterService converterService;
    private final FileMapper fileMapper;

    @PostMapping("/docx-html")
    public SaveFileResponse convertFile(@RequestBody ConvertFileRequest file, @RequestParam String name) throws IOException {
        if (!name.endsWith(".docx")) {
            name += ".docx";
        }
        ConvertedFileResponse t = converterService.convertDocxToHTML(file, name);
        return fileMapper.saveFile(t);
    }

    @PostMapping("/m/docx-html")
    public SaveFileResponse convertFile(@RequestParam("file") MultipartFile file, @RequestParam String name) {
        try {
            if (!name.endsWith(".docx")) {
                name += ".docx";
            }
            ConvertedFileResponse t = converterService.convertDocxToHTML(file, name);
            return fileMapper.saveFile(t);
        } catch (Exception e) {
            System.out.println("asdad");
        }
        return null;
    }

}
