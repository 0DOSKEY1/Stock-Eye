package com.stockeye.controller;

import com.stockeye.model.Item;
import com.stockeye.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ThymeleafController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/inventory")
    public String showInventory(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "inventory";
    }

    @PostMapping("/inventory/add")
    public String addItem(@ModelAttribute Item item) {
        itemService.createItem(item);
        return "redirect:/inventory";
    }

    @PostMapping("/inventory/update")
    public String updateItem(@ModelAttribute Item item) {
        itemService.updateItem(item.getId(), item);
        return "redirect:/inventory";
    }

    @GetMapping("/inventory/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/inventory";
    }
}
