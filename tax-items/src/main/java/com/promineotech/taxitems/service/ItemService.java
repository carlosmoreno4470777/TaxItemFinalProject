package com.promineotech.taxitems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.taxitems.controller.model.CustomerData;
import com.promineotech.taxitems.controller.model.ItemData;
import com.promineotech.taxitems.controller.model.TaxrateData;
import com.promineotech.taxitems.dao.CustomerDao;
import com.promineotech.taxitems.dao.ItemDao;
import com.promineotech.taxitems.dao.TaxrateDao;
import com.promineotech.taxitems.entity.Customer;
import com.promineotech.taxitems.entity.Item;
import com.promineotech.taxitems.entity.Taxrate;

@Service
public class ItemService {
	
	  @Autowired
	  private TaxrateDao taxRateDao;
	  
	  @Autowired
	  private ItemDao itemDao;
	  
	  @Autowired
	  private CustomerDao customerDao;
	
	  @Transactional(readOnly = false)
	  public TaxrateData saveTaxrate(TaxrateData taxrateData) {
		
		Long taxRateId = taxrateData.getTaxRateId();
		Taxrate taxRate = findOrCreateTaxrate(taxRateId);
		copyTaxRateFields(taxRate,taxrateData);
		return new TaxrateData(taxRateDao.save(taxRate));

	  }
	  
	  @Transactional(readOnly = false)
	  public ItemData saveItem(Long taxRateId, ItemData itemData) {
		  
		  Taxrate taxRate = findTaxrateByID(taxRateId);		  
		  Long itemId = itemData.getItemId();		  
		  Item item = findOrCreateItem(itemId);		  
		  copyItemFields(item,itemData);
		  
		  item.setTaxRate(taxRate);
		  taxRate.getItems().add(item);
		  item.setTaxRateId(taxRate.getTaxRateId());
		  Item dbItemData = itemDao.save(item);
		  return new ItemData(dbItemData);

	}

	  /**
	   * Delete a item given the item ID
	   */
		@Transactional(readOnly = false)
		public void deleteItemById(Long itemId) {
			Item item = findItemByID(itemId);
			  // Delete associated customers before deleting the item
			  for (Customer customer : item.getCustomers()) {
			    customer.getItems().remove(item); // Remove association from Customer side
			  }
			itemDao.delete(item);
		}


	private Item findOrCreateItem(Long itemId) {
		Item item;
		if (Objects.isNull(itemId)) {
			item = new Item();
		} else {				
			item = findItemByID(itemId);
		}
		return item;
	}

	private Taxrate findOrCreateTaxrate(Long taxRateId) {
		Taxrate taxRate;
		
		if (Objects.isNull(taxRateId)) {
			taxRate = new Taxrate();
		} else {				
			taxRate = findTaxrateByID(taxRateId);
		}
		
		return taxRate;
		
	}

	private Item findItemByID(Long itemId) {

		return itemDao.findById(itemId).orElseThrow(
				() -> new NoSuchElementException("Item with ID " + itemId + " not found"));
	}
		
		private Taxrate findTaxrateByID(Long taxRateId) {
			
			return taxRateDao.findById(taxRateId).orElseThrow(
					() -> new NoSuchElementException("Tax Rate with ID " + taxRateId + " not found"));
			
		}
		
		private void copyItemFields(Item item, ItemData itemData) {
			item.setItemId(itemData.getItemId());
			item.setName(itemData.getName());
			item.setDescription(itemData.getDescription());
			item.setBasePrice(itemData.getBasePrice());
			
		}
		  
		private void copyTaxRateFields(Taxrate taxRate, TaxrateData taxrateData) {
			taxRate.setRate(taxrateData.getRate());
			taxRate.setName(taxrateData.getName());
			
		}

		public ItemData retrieveItemById(Long taxRateId, Long itemId) {
			findTaxrateByID(taxRateId);
			Item item = findItemByID(itemId);
			if(item.getTaxRate().getTaxRateId() != taxRateId ) {
				throw new IllegalStateException("Item with ID=" + itemId 
						+ " is not owned by Taxrate with ID= " + taxRateId);
			}
			return new ItemData(item);
			
		}

		public List<CustomerData> retrieveAllCustomers() {
			// Implement logic to retrieve all customers
		      List<Customer> customers = customerDao.findAll();
		      
		      // Convert retrieved customers to CustomerData objects
		      List<CustomerData> customerDataList = new ArrayList<>();
		      for (Customer customer : customers) {
		        customerDataList.add(new CustomerData(customer));
		      }
		      
		      return customerDataList;
		}
		
		  @Transactional(readOnly = false)
		  public CustomerData insertCustomerItem(Long customerId, Long itemId) {
		    Customer customer = customerDao.findById(customerId)
		        .orElseThrow(() -> new NoSuchElementException("Customer with ID " + customerId + " not found"));
		    Item item = findItemByID(itemId);

		    // Check if the association already exists
		    if (customer.getItems().stream().anyMatch(i -> i.getItemId().equals(itemId))) {
		      throw new IllegalArgumentException("Customer with ID " + customerId + " already has item with ID " + itemId);
		    }

		    customer.getItems().add(item);
		    item.getCustomers().add(customer);

		    customerDao.save(customer);

		    return new CustomerData(customer);
		  }

}
