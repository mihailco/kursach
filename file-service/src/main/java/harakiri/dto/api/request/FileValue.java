
package harakiri.dto.api.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("unused")
@Data
@AllArgsConstructor
public class FileValue {

    @SerializedName("Data")
    private String data;
    @SerializedName("Name")
    private String name;

}
