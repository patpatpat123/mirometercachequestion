package com.example.micrometercache.modulithB;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Service
public class CustomerService {

    private final CustomerExchangeInterface customerExchangeInterface;

    CustomerService(CustomerExchangeInterface customerExchangeInterface) {
        this.customerExchangeInterface = customerExchangeInterface;
    }

    @Observed(name = "CustomerService.timed", contextualName = "CustomerService.span", lowCardinalityKeyValues = {"CustomerService", "CustomerService"})
    public String getDecryptedCustomer(@RequestParam String customerId) {
        Customer customer = customerExchangeInterface.getByCustomerVeryExpensiveNetworkCall(customerId);
        return performDecryptionDummy(customer);
    }

    private String performDecryptionDummy(Customer customer) {
        return customer.customerName().toUpperCase(Locale.ROOT);
    }

}
