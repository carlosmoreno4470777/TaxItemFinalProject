package com.promineotech.taxitems.controller.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.promineotech.taxitems.entity.Customer;
import com.promineotech.taxitems.entity.Item;
import com.promineotech.taxitems.entity.Taxrate;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerData {

	  private Long customerId;
	  private String firstName;
	  private String lastName;
	  private String email;
	  private Integer cardNumber;	  
	  private Set<CustomerItem> items = new HashSet<>();

	  //Constructor that converts a Customer object to a CustomerData object
	  public CustomerData(Customer customer) {
		    this.customerId = customer.getCustomerId();
		    this.firstName = customer.getFirstName();
		    this.lastName = customer.getLastName();
		    this.email = customer.getEmail();
		    this.cardNumber = customer.getCardNumber();
		    //loop to get the item set
   		 	for(Item itemsRec : customer.getItems()) {
   		 			items.add(new CustomerItem(itemsRec));
    			}
		  }
	  
	  @Data
	  @NoArgsConstructor
	  static class CustomerItem {

		  private Long itemId;
		  private String name;
		  private String description;
		  private BigDecimal basePrice;
		  		  
		    //constructor
	        CustomerItem(Item items) { 
		    	itemId = items.getItemId();
		    	name = items.getName();
		    	description = items.getDescription();
		    	basePrice = items.getBasePrice();
			 }
		}
	    
}
