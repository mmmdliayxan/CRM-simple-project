package org.example.crmbackinterntask.mapper;

import org.example.crmbackinterntask.entity.Customer;
import org.example.crmbackinterntask.model.request.CustomerRequest;
import org.example.crmbackinterntask.model.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerResponse toCustomerDto(Customer customer);

    Customer toCustomer(CustomerRequest customerRequest);

}
