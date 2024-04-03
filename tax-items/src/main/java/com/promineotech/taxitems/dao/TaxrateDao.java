package com.promineotech.taxitems.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.taxitems.entity.Taxrate;

public interface TaxrateDao extends JpaRepository<Taxrate, Long> {

}
