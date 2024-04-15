package harakiri.feign;


import feign.Headers;
import harakiri.configuration.FeignConfig;
import harakiri.dto.api.request.SendApiFileRequest;
import harakiri.dto.api.respose.ConvertedFileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "whata", url = "https://v2.convertapi.com", configuration = FeignConfig.class)
public interface ConverterFileClient {

    @RequestMapping(method = RequestMethod.POST, value = "/convert/docx/to/html?Secret={secret}")
    @Headers("Content-Type: application/json")
    ConvertedFileResponse convertDocxToHtml(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("secret") String secret,
            @RequestBody SendApiFileRequest requestBody);

    @GetMapping("/d/{fileId}/{fileName}.html")
    @Headers("Authorization: Bearer {token}")
    String getConvertedHtml(
            @Param("token") String token,
            @PathVariable("fileId") String fileId,
            @PathVariable("fileName") String fileName);
}
