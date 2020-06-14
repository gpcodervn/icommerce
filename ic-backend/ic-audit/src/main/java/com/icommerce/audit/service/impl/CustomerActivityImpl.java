package com.icommerce.audit.service.impl;

import com.icommerce.audit.entity.CustomerActivity;
import com.icommerce.audit.model.request.CustomerActivityRequest;
import com.icommerce.audit.model.response.CustomerActivityResponse;
import com.icommerce.audit.repository.CustomerActivityRepository;
import com.icommerce.audit.service.CustomerActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerActivityImpl implements CustomerActivityService {

    private final CustomerActivityRepository customerActivityRepository;

    @Autowired
    public CustomerActivityImpl(CustomerActivityRepository customerActivityRepository) {
        this.customerActivityRepository = customerActivityRepository;
    }

    @Override
    public CustomerActivityResponse save(CustomerActivityRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        CustomerActivity customerActivity = modelMapper.map(request, CustomerActivity.class);
        CustomerActivity savedCustomerActivity = customerActivityRepository.save(customerActivity);
        return modelMapper.map(savedCustomerActivity, CustomerActivityResponse.class);
    }
}
