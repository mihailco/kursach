
package harakiri.dto.api.respose;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public class File {

    @JsonProperty("FileData")
    private String fileData;
    @JsonProperty("FileExt")
    private String fileExt;
    @JsonProperty("FileName")
    private String fileName;
    @JsonProperty("FileSize")
    private Long fileSize;

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

}
