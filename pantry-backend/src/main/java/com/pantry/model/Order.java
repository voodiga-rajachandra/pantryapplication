package com.pantry.model;

import jakarta.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String itemName;
    private String status; // pending, completed

    // getters and setters
}
