package com.example.micrometercache.modulithC;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Service
public class ItemService {

    private final ItemExchangeInterface itemExchangeInterface;

    ItemService(ItemExchangeInterface itemExchangeInterface) {
        this.itemExchangeInterface = itemExchangeInterface;
    }

    @Observed(name = "ItemService.timed", contextualName = "ItemService.span", lowCardinalityKeyValues = {"ItemService", "ItemService"})
    public String getDecryptedItem(@RequestParam String itemId) {
        Item item = itemExchangeInterface.getByItemVeryExpensiveNetworkCall(itemId);
        return performDecryptionDummy(item);
    }

    private String performDecryptionDummy(Item item) {
        return item.name().toUpperCase(Locale.ROOT);
    }

}
