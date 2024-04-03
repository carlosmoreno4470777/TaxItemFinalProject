package com.promineotech.taxitems.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.taxitems.entity.Item;

public interface ItemDao extends JpaRepository<Item, Long> {

}
