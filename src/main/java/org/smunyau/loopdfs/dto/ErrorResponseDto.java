package org.smunyau.loopdfs.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private LocalDateTime errorTime;
    private int errorCode;
    private String errorMsg;
    private String errorPath;

}
