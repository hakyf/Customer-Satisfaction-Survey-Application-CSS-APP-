package id.co.mii.clientapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private UUID code;
    private LocalDate expired;
    private Status status;
    private Client client;
    private Employee employee;

    public String getName() {
        return name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Client getClient() {
        return client;
    }
}
