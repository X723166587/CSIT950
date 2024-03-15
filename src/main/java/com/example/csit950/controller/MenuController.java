package com.example.csit950.controller;


import com.example.csit950.model.Customer;
import com.example.csit950.model.Menu;
import com.example.csit950.repository.CustomerRepository;
import com.example.csit950.repository.MenuRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuRepository menuRepository;


    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping
    @ResponseBody
    public List<Menu> selectRestaurant(@RequestParam("restaurant_id")Integer restaurant_id){
        return menuRepository.selectRestaurant(restaurant_id);
    }
}
