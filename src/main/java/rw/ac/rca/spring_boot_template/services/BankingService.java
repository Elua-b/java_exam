package rw.ac.rca.spring_boot_template.services;

import java.util.UUID;

public interface BankingService {

    public void save(UUID id, double amount);
    public void withdraw(UUID id, double amount);
    public void transfer(UUID from, UUID to, double amount);

}
