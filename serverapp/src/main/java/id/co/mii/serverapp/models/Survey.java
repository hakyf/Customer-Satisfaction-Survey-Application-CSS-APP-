package id.co.mii.serverapp.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Component
@Table(name = "tb_survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private LocalDate expired;

    @ManyToOne
    @JoinColumn(name = "current_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private Employee employee;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "survey", cascade = CascadeType.ALL)
    private Result result;

    @OneToMany(mappedBy = "survey")
    private List<Answer> answers;

}
