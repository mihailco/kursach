
package harakiri.dto.api.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@SuppressWarnings("unused")
public class SendApiFileRequest {

    @SerializedName("Parameters")
    private List<Parameter> parameters;

}
