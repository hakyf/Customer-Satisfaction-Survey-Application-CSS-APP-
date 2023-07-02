package id.co.mii.serverapp.models.dto.request;

import lombok.Data;

@Data
public class EmailRequest {
    
    private String to;
    private String name;
    private String subject;
    private String text;
    private String attach;
}