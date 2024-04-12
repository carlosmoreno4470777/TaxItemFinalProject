package com.promineotech.taxitems.controller.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.promineotech.taxitems.entity.Customer;
import com.promineotech.taxitems.entity.Item;
import com.promineotech.taxitems.entity.Taxrate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaxrateData {

	  private Long taxRateId;
	  private BigDecimal rate;
	  private String name;
	  //private Set<Item> items = new HashSet<>();
	  private Set<ItemResponse> items = new HashSet<>();
  
	  //Constructor that converts a Taxrate object to a TaxrateData object	 
	  public TaxrateData(Taxrate taxrate) {
		    this.taxRateId = taxrate.getTaxRateId();
		    this.rate = taxrate.getRate();
		    
		    //loop to get the item set
   		 	for(Item itemsRec : taxrate.getItems()) {
   		 			items.add(new ItemResponse(itemsRec));
    			}
   		 	
		  }
	  
	  @Data
	  @NoArgsConstructor
	  static class ItemResponse {

		  private Long itemId;
		  private String name;
		  private String description;
		  private BigDecimal basePrice;
		  
		  //private Set<Customer> customers = new HashSet<>();
		  private Set<CustomerResponseDTO> customers = new HashSet<>();

		    //constructor
		    ItemResponse(Item items) { 
		    	itemId = items.getItemId();
		    	name = items.getName();
		    	description = items.getDescription();
		    	basePrice = items.getBasePrice();
			    
			    //loop to get the customer set
				for (Customer customer : items.getCustomers()) { 
					customers.add(new CustomerResponseDTO(customer)); 
				}
				
			 }
		
		}//end of inner class ItemResponse
	  
	  @Data
	  @NoArgsConstructor
	  public static class CustomerResponseDTO {
		  private Long customerId;
	      private String firstName;
	      private String lastName;
	      private String email;
	      private Integer cardNumber;

	      public CustomerResponseDTO(Customer customer) {
	    	  this.customerId = customer.getCustomerId();
	    	  this.firstName = customer.getFirstName();
	    	  this.lastName = customer.getLastName();
	    	  this.email = customer.getEmail();
	    	  this.cardNumber = customer.getCardNumber();
	    }

	  }//end of CustomerResponseDTO
	  
}//End of class TaxrateData
