package com.promineotech.taxitems.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.taxitems.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
