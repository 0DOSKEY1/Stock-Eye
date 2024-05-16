package com.stockeye.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockeye.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
