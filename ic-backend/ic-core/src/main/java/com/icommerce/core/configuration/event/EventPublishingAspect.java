package com.icommerce.core.configuration.event;

import com.icommerce.core.annotations.CustomerAudit;
import com.icommerce.core.dto.CustomerActivityDto;
import com.icommerce.core.dto.UserCredential;
import com.icommerce.core.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Optional;

@ConditionalOnProperty(
        value="app.customer.audit.enable",
        havingValue = "true",
        matchIfMissing = true)
@Configuration
@Aspect
@Slf4j
public class EventPublishingAspect {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public EventPublishingAspect(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Before(value = "@annotation(com.icommerce.core.annotations.CustomerAudit)")
    public void publishEvent(JoinPoint joinPoint) {
        if(getUserId().isEmpty()) {
            return;
        }
        Optional<CustomerAudit> customerAuditAnnotation = this.getCustomerAuditAnnotation(joinPoint);
        customerAuditAnnotation.ifPresent(annotation -> {
            CustomerActivityDto dto = CustomerActivityDto.builder()
                    .action(annotation.action())
                    .feature(annotation.feature())
                    .triggeredAt(LocalDateTime.now())
                    .content(JsonUtils.toJson(joinPoint.getArgs()))
                    .build();
            log.debug("Publish a create event for " + dto);
            eventPublisher.publishEvent(new CustomerActivityEvent(dto));
        });
    }

    private Optional<CustomerAudit> getCustomerAuditAnnotation(JoinPoint joinPoint) {
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            final String methodName = joinPoint.getSignature().getName();

            if (method.getDeclaringClass().isInterface()) {
                    method = joinPoint.getTarget().getClass()
                            .getDeclaredMethod(methodName, method.getParameterTypes());
                return Optional.ofNullable(method.getAnnotation(CustomerAudit.class));
            }
            return Optional.ofNullable(method.getAnnotation(CustomerAudit.class));
        } catch (NoSuchMethodException e) {
            log.error("Can not get annotation CustomerAudit", e);
        }
        return Optional.empty();
    }

    private Optional<Long> getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        return Optional.ofNullable( ((UserCredential) authentication.getPrincipal()).getId());
    }
}