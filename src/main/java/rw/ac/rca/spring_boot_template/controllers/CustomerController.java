package rw.ac.rca.spring_boot_template.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.spring_boot_template.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.spring_boot_template.dtos.responses.Response;
import rw.ac.rca.spring_boot_template.services.serviceImpl.CustomerServiceImpl;
import rw.ac.rca.spring_boot_template.utils.ApiResponse;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerServiceImpl customerService;

//    public ResponseEntity<Response> getAllCustomers() {
//        return ResponseEntity.ok().body(new Response(true, "Successfully fetched the customers", customerService.getAllCustomers()));
//    }
//
    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable UUID customerId) {
        return ResponseEntity.ok().body(new ApiResponse(true, "Successfully fetched the customer", customerService.getCustomerById(customerId)));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        return ResponseEntity.ok().body(new ApiResponse(true, "Successfully created the customer", customerService.createCustomer(createCustomerDTO)));
    }
    @PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable UUID customerId, @RequestBody CreateCustomerDTO createCustomerDTO) {
        return ResponseEntity.ok().body(new ApiResponse(true, "Successfully updated the customer", customerService.updateCustomer(customerId, createCustomerDTO)));
    }
    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable UUID customerId) {
        return ResponseEntity.ok().body(new ApiResponse(true, "Successfully deleted the customer", customerService.deleteCustomer(customerId)));
    }

}
