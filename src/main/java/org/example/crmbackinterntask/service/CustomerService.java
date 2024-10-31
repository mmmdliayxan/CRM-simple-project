package org.example.crmbackinterntask.service;

import org.example.crmbackinterntask.model.request.CustomerRequest;
import org.example.crmbackinterntask.model.response.CustomerResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    CustomerResponse getById(Long id);

    Page<CustomerResponse> getAll(int page, int size, String sortBy, String sortDir);

    void add(CustomerRequest customerRequest);

}
