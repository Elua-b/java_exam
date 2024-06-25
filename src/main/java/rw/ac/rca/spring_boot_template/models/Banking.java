package rw.ac.rca.spring_boot_template.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rw.ac.rca.spring_boot_template.enumerations.EType;

import java.util.Date;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Banking")
public class Banking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "banking_id")
    private UUID id;
    @ManyToOne
    private Customer customer;
    @Column(name = "account")
    private String account;
    @Column(name = "amount")
    private double amount;
    @Column(name = "type")
    private EType type= EType.BANKING;
    @Column(name = "banking_date")
    private Date bankingDate;

}
