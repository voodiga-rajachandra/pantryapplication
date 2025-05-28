package com.pantry.controller;

import com.pantry.model.Item;
import com.pantry.model.Order;
import com.pantry.repository.ItemRepository;
import com.pantry.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PantryController {

    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private OrderRepository orderRepo;

    @GetMapping("/items")
    public List<Item> getItems() {
        return itemRepo.findAll();
    }

    @PostMapping("/order")
    public Order placeOrder(@RequestBody Order order) {
        order.setStatus("pending");
        return orderRepo.save(order);
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderRepo.findAll();
    }

    @PutMapping("/order/{id}/complete")
    public Order completeOrder(@PathVariable Long id) {
        Order order = orderRepo.findById(id).orElse(null);
        if (order != null) {
            order.setStatus("completed");
            return orderRepo.save(order);
        }
        return null;
    }
}
