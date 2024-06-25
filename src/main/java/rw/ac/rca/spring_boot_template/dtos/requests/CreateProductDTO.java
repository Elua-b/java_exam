package rw.ac.rca.spring_boot_template.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Code cannot be blank")
    private String code;
    @NotBlank(message = "Price cannot be blank")
    private double price;
    @NotBlank(message = "Quantity cannot be blank")
    private int quantity;
    @NotBlank(message = "Date cannot be blank")
    private String image;
    @NotBlank(message = "Product type cannot be blank")
    private String productType;

}
