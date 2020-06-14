package com.icommerce.audit.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "customer_activity", catalog = "icommerce")
public class CustomerActivity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String action;

    private String feature;

    private String content;

}
