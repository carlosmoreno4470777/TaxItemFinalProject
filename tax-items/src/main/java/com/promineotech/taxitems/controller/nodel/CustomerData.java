package com.promineotech.taxitems.controller.nodel;

import java.util.HashSet;
import java.util.Set;

import com.promineotech.taxitems.entity.Customer;
import com.promineotech.taxitems.entity.Item;

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
	  
	  //Constructor that converts a Customer object to a CustomerData object
	  public CustomerData(Customer customer) {
		    this.customerId = customer.getCustomerId();
		    this.firstName = customer.getFirstName();
		    this.lastName = customer.getLastName();
		    this.email = customer.getEmail();
		    this.cardNumber = customer.getCardNumber();
		  }
	  
}
