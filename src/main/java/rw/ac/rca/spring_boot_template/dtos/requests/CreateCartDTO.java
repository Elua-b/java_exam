package rw.ac.rca.spring_boot_template.dtos.requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Data
@RequiredArgsConstructor

public class CreateCartDTO {

    private  UUID userId;
    private Double totalPrice=0.0;

}
