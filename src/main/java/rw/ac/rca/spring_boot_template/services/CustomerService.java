package rw.ac.rca.spring_boot_template.services;

import rw.ac.rca.spring_boot_template.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.spring_boot_template.models.Customer;

import java.util.UUID;

public interface CustomerService {
    public Customer createCustomer(CreateCustomerDTO createCustomerDTO);
    public Customer updateCustomer(UUID id,CreateCustomerDTO createCustomerDTO);
    public Customer getCustomerById(UUID id);
    public Customer deleteCustomer(UUID id);



}
