package harakiri.controller;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Controller
@RequestMapping("/api/v1/course")
   public class DocxToHtmlController {

      @PostMapping("/convert")
      public String convertDocxToHtml(@RequestPart("file") MultipartFile file) throws IOException {
         if (file.isEmpty()) {
            return "Выберите файл для конвертации.";
         }

         // Используем Apache POI для чтения docx файла
         try (InputStream inputStream = file.getInputStream()) {
            XWPFDocument document = new XWPFDocument(inputStream);

            // Создаем StringBuilder для записи результата
            StringBuilder htmlStringBuilder = new StringBuilder();

            // Проходим по параграфам и извлекаем текст
            document.getParagraphs().forEach(paragraph -> {
               htmlStringBuilder.append("<p>").append(paragraph.getText()).append("</p>");
            });

            // Проходим по встроенным изображениям и вставляем их в HTML в виде Base64
            document.getAllPictures().forEach(picture -> {
               String base64Image = Base64.getEncoder().encodeToString(picture.getData());
               String imgSrc = "data:" + picture.getPictureType() + ";base64," + base64Image;
               htmlStringBuilder.append("<img src='").append(imgSrc).append("'/>");
            });

            // Возвращаем HTML
            return htmlStringBuilder.toString();
         }
      }
   }


