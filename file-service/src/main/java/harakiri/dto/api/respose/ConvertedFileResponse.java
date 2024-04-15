
package harakiri.dto.api.respose;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@SuppressWarnings("unused")
public class ConvertedFileResponse {

    @JsonProperty("ConversionCost")
    private Long conversionCost;
    @JsonProperty("Files")
    private List<File> files;

    public Long getConversionCost() {
        return conversionCost;
    }

    public void setConversionCost(Long conversionCost) {
        this.conversionCost = conversionCost;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

}
