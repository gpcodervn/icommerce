package com.icommerce.audit.controller;

import com.icommerce.audit.model.request.CustomerActivityRequest;
import com.icommerce.audit.model.response.CustomerActivityResponse;
import com.icommerce.audit.service.CustomerActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/customer-audits")
@Slf4j
public class CustomerActivityController {

    private final CustomerActivityService customerActivityService;

    @Autowired
    public CustomerActivityController(CustomerActivityService customerActivityService) {
        this.customerActivityService = customerActivityService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CustomerActivityResponse create(@Valid @RequestBody CustomerActivityRequest request) {
        return customerActivityService.save(request);
    }

}