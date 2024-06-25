package rw.ac.rca.spring_boot_template.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rw.ac.rca.spring_boot_template.enumerations.EType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Withdraw")
public class Withdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "withdraw_id")
    private UUID id;
    @Column(name = "account")
    private String account;
    @Column(name = "amount")
    private double amount;
    @Column(name = "banking_date")
    private Date bankingDate;
    @Column(name = "type")
    private EType type= EType.WITHDRAW;
    @ManyToOne
    private Customer customer;

}
