package com.example.micrometercache.modulithA;

import com.example.micrometercache.modulithB.CustomerService;
import com.example.micrometercache.modulithC.ItemService;
import org.springframework.stereotype.Service;

//import java.util.concurrent.StructuredTaskScope;

@Service
class SomeService {

    private final CustomerService customerService;
    private final ItemService itemService;

    SomeService(CustomerService customerService, ItemService itemService) {
        this.customerService = customerService;
        this.itemService = itemService;
    }

    String doBusinessLogic(String customerId, String itemId) {
//        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
        String decryptedCustomer = customerService.getDecryptedCustomer(customerId);
        String decryptedItem = itemService.getDecryptedItem(itemId);
//        scope.join();
//        return decryptedCustomer.get() + decryptedItem.get();
        return decryptedCustomer + " " + decryptedItem;
    }

}
