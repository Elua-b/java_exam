package rw.ac.rca.spring_boot_template.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.spring_boot_template.models.Customer;

import java.util.UUID;
@Repository
public interface ICustomerRepository  extends JpaRepository<Customer, UUID> {
}
