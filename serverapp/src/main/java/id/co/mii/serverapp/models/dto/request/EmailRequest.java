package id.co.mii.serverapp.models.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;
import id.co.mii.serverapp.models.Survey;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {
    private Survey survey;
    private String to;
    private String name;
    private String subject;
    private String text;
    private UUID code;
    

}