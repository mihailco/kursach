package harakiri.service;

import harakiri.configuration.ConverterProperties;
import harakiri.dto.api.request.FileValue;
import harakiri.dto.api.request.Parameter;
import harakiri.dto.api.request.SendApiFileRequest;
import harakiri.dto.api.respose.ConvertedFileResponse;
import harakiri.dto.request.ConvertFileRequest;
import harakiri.feign.ConverterFileClient;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConverterService {
    private final ConverterProperties converterProperties;
    private final ConverterFileClient converterFileClient;


    public ConvertedFileResponse convertDocxToHTML(ConvertFileRequest file, String name) throws IOException {
        FileValue fileValue = new FileValue(file.getFile(), name);
        List<Parameter> mParameters = new ArrayList<>();
        mParameters.add(new Parameter(fileValue, "file"));

        return converterFileClient.convertDocxToHtml(
                converterProperties.getClientToken(),
                converterProperties.getClientSecret(),
                new SendApiFileRequest(mParameters)
        );
    }


    public ConvertedFileResponse convertDocxToHTML(MultipartFile file, String name) throws IOException {
        String encFile = Base64.toBase64String(file.getBytes());
        FileValue fileValue = new FileValue(encFile, name);
        List<Parameter> mParameters = new ArrayList<>();
        mParameters.add(new Parameter(fileValue, file.getName()));

        return converterFileClient.convertDocxToHtml(
                converterProperties.getClientToken(),
                converterProperties.getClientSecret(),
                new SendApiFileRequest(mParameters)
        );
    }

    public String getApiFile(String fileId, String fileName) throws IOException {
        return converterFileClient.getConvertedHtml(converterProperties.getClientToken(), fileId, fileName);
    }

}
