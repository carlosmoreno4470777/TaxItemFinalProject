package com.promineotech.taxitems.controller.nodel;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.promineotech.taxitems.entity.Customer;
import com.promineotech.taxitems.entity.Item;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemData {
	
	  private Long itemId;
	  private Long taxRateId;
	  private String name;
	  private String description;
	  private BigDecimal basePrice;
	  private Set<CustomerResponse> customers = new HashSet<>();

	  
	  //Constructor that converts a Item object to a ItemData object
	  public ItemData(Item item) {
		    this.itemId = item.getItemId();
		    this.taxRateId = item.getTaxRateId();
		    this.name = item.getName();
		    this.description = item.getDescription();
		    this.basePrice = item.getBasePrice();
		    
		    //loop to get the customer set
   		 	for(Customer customersRec : item.getCustomers()) {
   		 			customers.add(new CustomerResponse(customersRec));
    			}
   		 		
		  }
	  
	  @Data
	  @NoArgsConstructor
	  static class CustomerResponse {

		    private Long customerId;
		    private String firstName;
		    private String lastName;
		    private String email;
		    private Integer cardNumber;

		    //constructor
		  CustomerResponse(Customer customers) { 
			  customerId = customers.getCustomerId();
			  firstName = customers.getFirstName();
			  lastName = customers.getLastName();
			  email = customers.getEmail();
			  cardNumber = customers.getCardNumber();
			  
			 }
		
		}

}
