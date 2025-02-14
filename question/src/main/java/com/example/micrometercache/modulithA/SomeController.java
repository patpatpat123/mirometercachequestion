package com.example.micrometercache.modulithA;

import org.springframework.web.bind.annotation.*;

@RestController
class SomeController {

    private final SomeService someService;

    SomeController(SomeService someService) {
        this.someService = someService;
    }

    @PostMapping(value = "/question")
    String events(@RequestBody MyRequest myRequest) {
        return someService.doBusinessLogic(myRequest.customerId(), myRequest.itemId());
    }
}
