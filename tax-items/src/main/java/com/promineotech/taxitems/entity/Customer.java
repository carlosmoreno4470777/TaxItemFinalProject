package com.promineotech.taxitems.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String firstName;
    private String lastName;
    private String email;
    private Integer cardNumber;
    
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable( 
        name = "customer_item",  // Specifying the join table name
        joinColumns = @JoinColumn(name = "customer_id"),  // Foreign key in join table referencing Customer
        inverseJoinColumns = @JoinColumn(name = "item_id")  // Foreign key in join table referencing Item
    )
    private Set<Item> items = new HashSet<>();

}
