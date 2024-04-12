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
public class ItemData {

	private Long itemId;
	private String name;
	private String description;
	private BigDecimal basePrice;
	private ItemTaxrate taxRate;
	// private BigDecimal rate;
	private Set<CustomerResponse> customers = new HashSet<>();

	// Constructor that converts a Item object to a ItemData object
	public ItemData(Item item) {
		this.itemId = item.getItemId();
		this.name = item.getName();
		this.description = item.getDescription();
		this.basePrice = item.getBasePrice();
		taxRate = new ItemTaxrate(item.getTaxRate());

		// loop to get the customer set
		for (Customer customersRec : item.getCustomers()) {
			customers.add(new CustomerResponse(customersRec));
		}

	}
	
	@Data
	@NoArgsConstructor
	public static class ItemTaxrate {
		  private Long taxRateId;
		  private BigDecimal rate;
		  private String name;
		
		//pet park that takes a contributor
		public ItemTaxrate(Taxrate taxRate) {
			//set the fields 
			taxRateId = taxRate.getTaxRateId();
			rate = taxRate.getRate();
			name = taxRate.getName();
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

		// constructor
		CustomerResponse(Customer customers) {
			this.customerId = customers.getCustomerId();
			this.firstName = customers.getFirstName();
			this.lastName = customers.getLastName();
			this.email = customers.getEmail();
			this.cardNumber = customers.getCardNumber();

		}

	}

}
