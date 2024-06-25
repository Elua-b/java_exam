package rw.ac.rca.spring_boot_template.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Customer", uniqueConstraints = {@UniqueConstraint(columnNames = {"mobile"})})
@OnDelete(action = OnDeleteAction.CASCADE)
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private  String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "account")
    private String account;
    @Column(name = "balance")
    private double balance;
    @Column(name = "updated_time")
    private LocalDateTime lastUpdatedDateTime;



}
