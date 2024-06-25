package rw.ac.rca.spring_boot_template.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name = "message_id")
    private UUID id;
    @Column(name = "message")
    private String message;
    @ManyToOne
    private Customer customer;
    @Column(name = "created_time")
    private Date createdDateTime;

}
