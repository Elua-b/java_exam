package rw.ac.rca.spring_boot_template.services.serviceImpl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.spring_boot_template.exceptions.InternalServerErrorException;
import rw.ac.rca.spring_boot_template.models.Customer;
import rw.ac.rca.spring_boot_template.models.Message;
import rw.ac.rca.spring_boot_template.models.Saving;
import rw.ac.rca.spring_boot_template.models.Withdraw;
import rw.ac.rca.spring_boot_template.repositories.ICustomerRepository;
import rw.ac.rca.spring_boot_template.repositories.IMessageRepository;
import rw.ac.rca.spring_boot_template.repositories.ISavingRepository;
import rw.ac.rca.spring_boot_template.repositories.IWithdrawRepository;
import rw.ac.rca.spring_boot_template.services.BankingService;


import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankingServiceImpl implements BankingService {
 private final ISavingRepository savingRepository;
 private  final ICustomerRepository customerRepository;
 private final IWithdrawRepository withDrawRepository;
    private final IMessageRepository messageRepository;

    private final  EmailService emailService;
    @Override
    public void save(UUID id, double amount) {
        Customer customer = customerRepository.findById(id).get();
        if(customer == null){
            throw new InternalServerErrorException("Customer not found");
        }
        customer.setBalance(customer.getBalance() + amount);
        customerRepository.save(customer);

        Saving saving=new Saving();
        saving.setCustomer(customer);
        saving.setAmount(amount);
        saving.setBankingDate(new Date());

        savingRepository.save(saving);
        //send transaction email


        Message message = new Message();
        message.setCustomer(customer);
        message.setMessage("Saved " + amount + " to the account");
        message.setCreatedDateTime(new Date());
        messageRepository.save(message);

    }

    @Override
    public void withdraw(UUID id, double amount) {
      Customer customer= customerRepository.findById(id).get();
        if(customer == null){
            throw new InternalServerErrorException("Customer not found");
        }
        if(customer.getBalance() < amount){
            throw new InternalServerErrorException("Insufficient funds");
        }
        customer.setBalance(customer.getBalance() - amount);
        customerRepository.save(customer);

        Withdraw withdraw=new Withdraw();
        withdraw.setCustomer(customer);
        withdraw.setAmount(amount);
        withdraw.setBankingDate(new Date());

        withDrawRepository.save(withdraw);

        Message message = new Message();
        message.setCustomer(customer);
        message.setMessage("Withdrew " + amount + " from the account");
        message.setCreatedDateTime(new Date());
        messageRepository.save(message);


    }

    @Override
    public void transfer(UUID from, UUID to, double amount) {
        try {
            Saving fromSaving = savingRepository.findByCustomerId(from);
            Optional<Customer> toCustomer = customerRepository.findById(to);

            //check if the customer exists
            if (toCustomer.isEmpty()) {
                throw new InternalServerErrorException("Customer not found");
            }
            //check if the saving account exists
            if (fromSaving.getAmount() < amount) {
                throw new Exception("Insufficient funds in saving account");
            }
            fromSaving.setAmount(fromSaving.getAmount() - amount);
            toCustomer.get().setBalance(toCustomer.get().getBalance() + amount);
            savingRepository.save(fromSaving);
            customerRepository.save(toCustomer.get());
            emailService.sendTransactionEmail(fromSaving.getCustomer(), toCustomer.get(), amount);

            Message message = new Message();
            message.setCustomer(fromSaving.getCustomer());
            message.setCreatedDateTime(new Date());
            message.setMessage("Transferred " + amount + " to " + toCustomer.get().getFirstName() + " " + toCustomer.get().getLastName());

            messageRepository.save(message);


        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

}
