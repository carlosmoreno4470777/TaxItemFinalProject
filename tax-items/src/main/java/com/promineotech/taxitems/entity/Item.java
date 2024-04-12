package com.promineotech.taxitems.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Item {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
	
    @Column(name = "tax_rate_id", nullable = false)
    private Long taxRateId;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "tax_rate_id", insertable = false, updatable = false)
    private Taxrate taxRate;
	
    private String name;
    private String description;
    private BigDecimal basePrice;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
    @ManyToMany(mappedBy = "items")
    private Set<Customer> customers = new HashSet<>();
	
}
