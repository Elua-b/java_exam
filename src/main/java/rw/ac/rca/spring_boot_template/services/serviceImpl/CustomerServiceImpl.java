package rw.ac.rca.spring_boot_template.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.spring_boot_template.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.spring_boot_template.exceptions.InternalServerErrorException;
import rw.ac.rca.spring_boot_template.exceptions.NotFoundException;
import rw.ac.rca.spring_boot_template.models.Customer;
import rw.ac.rca.spring_boot_template.repositories.ICustomerRepository;
import rw.ac.rca.spring_boot_template.services.CustomerService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final ICustomerRepository customerRepository;
    @Override
    public Customer createCustomer(CreateCustomerDTO createCustomerDTO) {
        try {
            Customer customer1= new Customer();
            customer1.setFirstName(createCustomerDTO.getFirstName());
            customer1.setLastName(createCustomerDTO.getLastName());
            customer1.setEmail(createCustomerDTO.getEmail());
            customer1.setMobile(createCustomerDTO.getMobile());
            customer1.setDob(createCustomerDTO.getDob());
            customer1.setBalance(createCustomerDTO.getBalance());
            customer1.setAccount(createCustomerDTO.getAccount());

            return customer1;
        }catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    @Override
    public Customer updateCustomer(UUID id, CreateCustomerDTO createCustomerDTO) {
        try{

            if(customerRepository.findById(id).isEmpty()){
                throw new NotFoundException("Customer not found");
            }else{
                Customer customer1= customerRepository.findById(id).get();
                customer1.setFirstName(createCustomerDTO.getFirstName());
                customer1.setLastName(createCustomerDTO.getLastName());
                customer1.setEmail(createCustomerDTO.getEmail());
                customer1.setMobile(createCustomerDTO.getMobile());
                customer1.setDob(createCustomerDTO.getDob());
                customer1.setBalance(createCustomerDTO.getBalance());
                customer1.setAccount(createCustomerDTO.getAccount());
                return customer1;
            }

        }catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    @Override
    public Customer getCustomerById(UUID id) {
            try{
                if(customerRepository.findById(id).isEmpty()){
                    throw new NotFoundException("Customer not found");
                }else{
                    return customerRepository.findById(id).get();
                }
            }catch (Exception e) {
                e.printStackTrace();
                throw new InternalServerErrorException("Internal Server Error");
            }
    }

    @Override
    public Customer deleteCustomer(UUID id) {
        try{
            if(customerRepository.findById(id).isEmpty()){
                throw new NotFoundException("Customer not found");
            }else{
                Customer customer1= customerRepository.findById(id).get();
                customerRepository.delete(customer1);
                return customer1;
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException("Internal Server Error");
        }
    }


}
