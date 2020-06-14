package com.icommerce.core.configuration.event;

import com.icommerce.core.dto.CustomerActivityDto;
import org.springframework.context.ApplicationEvent;

public class CustomerActivityEvent extends ApplicationEvent {

    public CustomerActivityEvent(CustomerActivityDto customerActivity) {
        super(customerActivity);
    }
}