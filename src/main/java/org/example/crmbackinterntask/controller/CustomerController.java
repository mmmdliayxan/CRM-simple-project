package org.example.crmbackinterntask.controller;


import lombok.RequiredArgsConstructor;
import org.example.crmbackinterntask.model.request.CustomerRequest;
import org.example.crmbackinterntask.model.response.CustomerResponse;
import org.example.crmbackinterntask.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable Long id){
        return customerService.getById(id);
    }

    @GetMapping
    public Page<CustomerResponse> getAll(@RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy,
                                         @RequestParam(defaultValue = "asc") String sortDir){
        return customerService.getAll(page,size,sortBy,sortDir);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody CustomerRequest customerRequest){
        customerService.add(customerRequest);
    }

}
