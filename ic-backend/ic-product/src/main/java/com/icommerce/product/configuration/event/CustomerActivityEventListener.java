package com.icommerce.product.configuration.event;

import com.icommerce.core.configuration.event.CustomerActivityEvent;
import com.icommerce.core.dto.CustomerActivityDto;
import com.icommerce.core.token.extractor.TokenExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Component
@Slf4j
public class CustomerActivityEventListener implements ApplicationListener<CustomerActivityEvent> {

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private CustomerEventExecutor customerEventExecutor;

    @Override
    public void onApplicationEvent(CustomerActivityEvent event) {
        log.debug("on received event: " + event.getSource());

        getCurrentHttpRequest().ifPresent(request -> {
            String jwt = tokenExtractor.extract(request);
            if (StringUtils.hasText(jwt)) {
                customerEventExecutor.executeInBackground(jwt, (CustomerActivityDto) event.getSource());
            }
        });
    }


    private static Optional<HttpServletRequest> getCurrentHttpRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(requestAttributes -> ServletRequestAttributes.class.isAssignableFrom(requestAttributes.getClass()))
                .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
                .map(ServletRequestAttributes::getRequest);
    }
}