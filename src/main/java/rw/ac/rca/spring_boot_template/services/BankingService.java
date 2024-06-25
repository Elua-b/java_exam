package rw.ac.rca.spring_boot_template.services;

import jakarta.mail.MessagingException;

import java.util.UUID;

public interface BankingService {

    public void save(UUID id, double amount) throws MessagingException;
    public void withdraw(UUID id, double amount) throws MessagingException;
    public void transfer(UUID from, UUID to, double amount);

}
