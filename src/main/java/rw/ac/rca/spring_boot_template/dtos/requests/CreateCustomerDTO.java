package rw.ac.rca.spring_boot_template.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.RegEx;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDTO {
    //validate
    @NotNull(message = "First name is required")

    private String firstName;
    @NotNull(message = "Last name is required")

    private String lastName;
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Mobile is required")
    //validate if has 10 digits
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    private String mobile;
    @NotNull(message = "Date of birth is required")
    //validate if date is in the past
    @Past(message = "Date of birth must be in the past")
    private Date dob;
    @NotNull(message = "Account is required")
    private String account;
    @NotNull(message = "Balance is required")
    //validate if balance is a number
    @Pattern(regexp = "\\d+", message = "Balance must be a number")
    private int balance;

}
