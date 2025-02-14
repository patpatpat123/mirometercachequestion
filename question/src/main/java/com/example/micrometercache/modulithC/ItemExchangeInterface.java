package com.example.micrometercache.modulithC;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(accept = MediaType.APPLICATION_JSON_VALUE)
interface ItemExchangeInterface {

//    @Retry
    @Cacheable("itemCache")
    @GetExchange("/api/getItem")
    Item getByItemVeryExpensiveNetworkCall(@RequestParam String id);

}
