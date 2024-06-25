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
@Table(name = "Saving")
public class Saving {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name = "saving_id")
    private UUID id;
    @Column(name = "account")
    private String account;
    @Column(name = "amount")
    private double amount;
    @Column(name = "banking_date")
    private Date BankingDate;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EType type= EType.SAVING;
    @ManyToOne
    private Customer customer;
}
