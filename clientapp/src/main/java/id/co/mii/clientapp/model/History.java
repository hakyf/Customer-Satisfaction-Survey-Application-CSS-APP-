package id.co.mii.clientapp.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

    private Long id;
    private String notes;
    private LocalDate date;
    private Employee employee;
    private Status status;
    private Survey survey;

}
