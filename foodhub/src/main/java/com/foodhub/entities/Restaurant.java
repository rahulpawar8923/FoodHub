package com.foodhub.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
@Table(name = "restaurant")
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @NotNull
    @Column(length = 100)
    private String name;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String address;

    @NotNull
    @Column(length = 15)
    private String phone;

    @NotNull
    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private LocalTime openingTime;

    @NotNull
    private LocalTime closingTime;

    private boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuItem> menuItems;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews;
}
