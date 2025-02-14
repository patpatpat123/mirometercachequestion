package com.example.micrometercache.modulithB;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(accept = MediaType.APPLICATION_JSON_VALUE)
interface CustomerExchangeInterface {

//    @Retry
    @Cacheable("customerCache")
    @GetExchange("/api/getCustomer")
    Customer getByCustomerVeryExpensiveNetworkCall(@RequestParam String id);

}
