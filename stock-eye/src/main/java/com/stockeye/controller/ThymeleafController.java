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

    @GetMapping("/inventory/{id}")
    public String showItemDetails(@PathVariable Long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        return "item-details";
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

    @PostMapping("/inventory/edit")
    public String editItem(Item item) {
        itemService.updateItem(item.getId(), item);
        return "redirect:/inventory";
    }

    @GetMapping("/inventory/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/inventory";
    }
}
