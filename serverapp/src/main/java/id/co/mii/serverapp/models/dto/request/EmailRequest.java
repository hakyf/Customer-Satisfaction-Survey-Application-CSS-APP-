package id.co.mii.serverapp.models.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import id.co.mii.serverapp.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    private String to;
    private String name;
    private String subject;
    private UUID code;
    private LocalDate expired;
    private Employee employee;

}