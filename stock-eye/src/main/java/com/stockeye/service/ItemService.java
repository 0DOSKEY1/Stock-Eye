package com.stockeye.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockeye.model.Item;
import com.stockeye.repository.ItemRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void initData() {
        if (itemRepository.count() == 0) {
            Item cpuItem = new Item();
            cpuItem.setName("CPU");
            cpuItem.setQuantity(10);
            cpuItem.setDescription(
                    "The CPU, also referred to as the “central” or “main” processor, is a complex set of electronic circuitry that runs the machine's operating system and apps. The CPU interprets, processes and executes instructions, most often from the hardware and software programs running on the device.");
            itemRepository.save(cpuItem);
        }
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item item) {
        if (itemRepository.existsById(id)) {
            Item existingItem = itemRepository.findById(id).orElseThrow();
            existingItem.setName(item.getName());
            existingItem.setQuantity(item.getQuantity());
            existingItem.setDescription(item.getDescription());
            return itemRepository.save(existingItem);
        }
        return null;
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public void cleanData() {
        itemRepository.deleteAll();
    }
}
