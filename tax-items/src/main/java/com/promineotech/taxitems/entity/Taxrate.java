package com.promineotech.taxitems.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Taxrate {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taxRateId;
    private BigDecimal rate;
    private String name;
    
	//One to Many relationship 
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
    @OneToMany(mappedBy = "taxRate", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Item> items = new HashSet<>();
	
	
}
