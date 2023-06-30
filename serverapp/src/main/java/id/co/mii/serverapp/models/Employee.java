package id.co.mii.serverapp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 13)
    private String phone;

    @Column(length = 50, nullable = false)
    private String jobPosition;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "employee")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Survey> surveys;

    @OneToMany(mappedBy = "employee")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<History> histories;
}
