package id.co.mii.clientapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Survey {

    private Long id;
    private String name;
    private String code;
    private LocalDate expired;
    private Status status;
    private Client client;
    private Employee employee;
    private List<Answer> answers;
    private Result result;

}
