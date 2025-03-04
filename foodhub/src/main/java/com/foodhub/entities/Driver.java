package com.foodhub.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "driver")
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    @NotNull
    @Column(length = 100)
    private String name;

    @NotNull
    @Column(length = 15)
    private String phone;

    @NotNull
    @Column(unique = true)
    private String email;

    @Column(length = 20)
    private String vehicleNumber;

    private boolean isAvailable = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "driver")
    private List<OrderDelivery> deliveries;
}
