package com.example.csit950.controller;


import com.example.csit950.model.Customer;
import com.example.csit950.model.Menu;
import com.example.csit950.repository.CustomerRepository;
import com.example.csit950.repository.MenuRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/update/{menuId}")
    public ResponseEntity<?> updateMenu(@PathVariable int menuId, @RequestBody Menu menu) {
        menu.setItem_id(menuId); // Ensure the item ID is set correctly
        menuRepository.updateMenu(menu); // Use the instance variable menuRepo
        return ResponseEntity.ok().body("Menu item updated successfully.");
    }

    @PostMapping
    public String addMenu(@RequestBody Menu menu) {
        System.out.println("Received menu: " + menu);
        menuRepository.addMenu(menu);
        return "Menu item created successfully.";
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable int menuId) {
        menuRepository.deleteMenu(menuId);
        return ResponseEntity.ok("Menu item deleted successfully.");
    }

}
