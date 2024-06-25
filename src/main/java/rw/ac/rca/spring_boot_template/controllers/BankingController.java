package rw.ac.rca.spring_boot_template.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.spring_boot_template.services.serviceImpl.BankingServiceImpl;
import rw.ac.rca.spring_boot_template.utils.ApiResponse;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banking")
public class BankingController {
    private final BankingServiceImpl bankingService;

//transaction
    @PostMapping("/save/{id}")
    public ResponseEntity<ApiResponse> save(@PathVariable UUID id,@RequestParam double amount) {
        bankingService.save(id, amount);
        return ResponseEntity.ok().body(new ApiResponse(true, "Successfully saved the amount"));
    }
    @PostMapping("/withdraw/{id}")
    public ResponseEntity<ApiResponse> withdraw(@PathVariable UUID id,@RequestParam double amount) {
        bankingService.withdraw(id, amount);
        return ResponseEntity.ok().body(new ApiResponse(true, "Successfully withdrew the amount"));
    }
    @PostMapping("/transfer/{from}/{to}")
    public ResponseEntity<ApiResponse> transfer(@PathVariable UUID from,@PathVariable UUID to ,@RequestParam double amount) {
        bankingService.transfer(from,to,amount);
        return ResponseEntity.ok().body(new ApiResponse(true, "Successfully transferred the amount"));
    }

}
