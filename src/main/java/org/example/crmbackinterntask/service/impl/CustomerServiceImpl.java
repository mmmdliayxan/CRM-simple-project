package org.example.crmbackinterntask.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.crmbackinterntask.entity.Customer;
import org.example.crmbackinterntask.entity.User;
import org.example.crmbackinterntask.mapper.CustomerMapper;
import org.example.crmbackinterntask.model.exceptions.NotFoundException;
import org.example.crmbackinterntask.model.request.CustomerRequest;
import org.example.crmbackinterntask.model.response.CustomerResponse;
import org.example.crmbackinterntask.repository.CustomerRepository;
import org.example.crmbackinterntask.repository.UserRepository;
import org.example.crmbackinterntask.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    @Override
    public CustomerResponse getById(Long id) {
        log.info("getById method is started for customer with id:{}",id);
        CustomerResponse customerDto = customerRepository.findById(id)
                .map(customerMapper::toCustomerDto)
                .orElseThrow(()->new NotFoundException("Customer is not found"));
        log.info("getById method is successfully done:{}",customerDto);
        return customerDto;
    }

    @Override
    public Page<CustomerResponse> getAll(int page, int size, String sortBy, String sortDir) {
        log.info("getAllPayments method is started with parameters: page={},size={},sortBy={}.sortDir={}", page, size, sortBy, sortDir);
        Sort.Direction direction = Sort.Direction.fromString(sortDir);
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(direction, sortBy));
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        Page<CustomerResponse> customerResponsePage = customerPage.map(customerMapper::toCustomerDto);
        log.info("getAllPayments method completed successfully, Total elements: {}, Total pages: {} ",customerResponsePage.getTotalElements(), customerResponsePage.getTotalPages());
        return customerResponsePage;
    }

    @Override
    public void add(CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);
        customerRepository.save(customer);
        log.info("customer is saved successfully");
    }

}
