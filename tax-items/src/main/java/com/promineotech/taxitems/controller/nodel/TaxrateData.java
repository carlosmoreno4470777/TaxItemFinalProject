package com.promineotech.taxitems.controller.nodel;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.promineotech.taxitems.controller.nodel.ItemData.CustomerResponse;
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
		    this.name = taxrate.getName();
		    
		    //loop to get the item set
   		 	for(Item itemsRec : taxrate.getItems()) {
   		 			items.add(new ItemResponse(itemsRec));
    			}
		    
		  }
	  
	  @Data
	  @NoArgsConstructor
	  static class ItemResponse {

		  private Long itemId;
		  private Long taxRateId;
		  private String name;
		  private String description;
		  private BigDecimal basePrice;
		  
		  //Mike how do I handle this???
		    private Set<Customer> customers = new HashSet<>();


		    //constructor
		    ItemResponse(Item items) { 
		    	itemId = items.getItemId();
		    	taxRateId = items.getTaxRateId();
		    	name = items.getName();
		    	description = items.getDescription();
		    	basePrice = items.getBasePrice();
			  
			 }
		
		}


	
}
