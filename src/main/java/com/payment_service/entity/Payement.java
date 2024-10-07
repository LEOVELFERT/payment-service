package com.payment_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="PAYMENT_DETAILS")
public class Payement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="ITEM")
    private String item;

    @Column(name="PRICE")
    private Integer price;

    @Column(name="PAYMENT_MODE")
    private String paymentMode;

    @Column(name="PREPARATION_STATUS")
    private String preparationStatus;
}
