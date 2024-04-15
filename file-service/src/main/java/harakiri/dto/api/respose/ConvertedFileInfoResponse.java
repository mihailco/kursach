
package harakiri.dto.api.respose;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class ConvertedFileInfoResponse {

    @JsonProperty("ConversionCost")
    private Long conversionCost;
    @JsonProperty("Files")
    private List<File> files;
}
