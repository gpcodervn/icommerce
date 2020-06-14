package com.icommerce.product.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "product", catalog = "icommerce")
public class Product extends AuditEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String name;

    private String description;

    private Double price;

    @Column(name = "branch_name", length = 50)
    private String branchName;

    private String color;

}
