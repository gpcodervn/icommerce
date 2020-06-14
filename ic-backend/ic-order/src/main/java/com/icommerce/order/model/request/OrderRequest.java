package com.icommerce.order.model.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequest {

    @NotNull
    @NotEmpty
    @Valid
    private List<OrderDetailRequest> orderDetails;
}
