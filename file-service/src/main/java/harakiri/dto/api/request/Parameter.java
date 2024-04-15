
package harakiri.dto.api.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("unused")
@Data
@AllArgsConstructor
public class Parameter {

    @SerializedName("FileValue")
    private FileValue fileValue;
    @SerializedName("Name")
    private String name;

}
