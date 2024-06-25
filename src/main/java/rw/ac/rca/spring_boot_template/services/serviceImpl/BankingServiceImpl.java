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


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
    public void save(UUID id, double amount)  {
        try{
            Customer customer = customerRepository.findById(id).get();
            if(customer == null){
                throw new InternalServerErrorException("Customer not found");
            }
            customer.setBalance(customer.getBalance() + amount);
            customerRepository.save(customer);

            Saving saving=new Saving();
            saving.setCustomer(customer);
            saving.setAmount(amount);
            saving.setBankingDate(LocalDate.now());

            savingRepository.save(saving);
            emailService.sendSavingEmail(customer, amount);
            //send transaction email


            Message message = new Message();
            message.setCustomer(customer);
            message.setMessage("Saved " + amount + " to the account");
            message.setCreatedDateTime(LocalDate.now());
            messageRepository.save(message);
        }catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException("Internal Server Error");
        }


    }

    @Override
    public void withdraw(UUID id, double amount) {
        try{
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
            withdraw.setBankingDate(LocalDate.now());

            withDrawRepository.save(withdraw);
            //email service
            emailService.sendWithdrawEmail(customer, amount);
            Message message = new Message();
            message.setCustomer(customer);
            message.setCreatedDateTime(LocalDate.now());
            message.setMessage("Withdrew " + amount + " from the account");
            messageRepository.save(message);
        }catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException("Internal Server Error");
        }



    }

    @Override
    public void transfer(UUID from, UUID to, double amount) {
        try {
            List<Saving> fromSavings = savingRepository.findByCustomerId(from);
            if (fromSavings.isEmpty()) {
                throw new InternalServerErrorException("No savings account found for customer");
            }
            Optional<Customer> toCustomer = customerRepository.findById(to);

            //check if the customer exists
            if (toCustomer.isEmpty()) {
                throw new InternalServerErrorException("Customer not found");
            }
            Saving fromSaving = fromSavings.get(0);

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
            message.setCreatedDateTime(LocalDate.now());
            message.setMessage("Transferred " + amount + " to " + toCustomer.get().getFirstName() + " " + toCustomer.get().getLastName());

            messageRepository.save(message);


        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

}
