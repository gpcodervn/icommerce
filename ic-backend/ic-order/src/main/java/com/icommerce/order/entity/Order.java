package com.icommerce.order.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "order", catalog = "icommerce")
public class Order extends AuditEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String no;

    private Double price;

    private Long customerId;

    @Column(name = "ordered_at", columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime orderedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<OrderDetail> orderDetails;

}
