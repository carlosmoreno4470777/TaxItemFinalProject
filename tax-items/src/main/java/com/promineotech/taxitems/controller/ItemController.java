package com.promineotech.taxitems.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.taxitems.controller.model.CustomerData;
import com.promineotech.taxitems.controller.model.ItemData;
import com.promineotech.taxitems.controller.model.TaxrateData;
import com.promineotech.taxitems.entity.Customer;
import com.promineotech.taxitems.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/item_tax")
@Slf4j
public class ItemController {
	@Autowired
	private ItemService itemService;
	
    @PostMapping("/taxrate")
    @ResponseStatus(code = HttpStatus.CREATED)
	public TaxrateData insertTaxrate(@RequestBody TaxrateData taxrateData) {
        log.info("Creating taxrate {}", taxrateData);
        return itemService.saveTaxrate(taxrateData);
	}
    
    //CODE for the Item data CRUD
    //We are inserting itemData type from the JSON into the Item entity
    @PostMapping("/taxrate/{taxRateId}/item")
    @ResponseStatus(code = HttpStatus.CREATED)
	public ItemData insertItem(@PathVariable Long taxRateId,@RequestBody ItemData itemData) {
        log.info("Creating item to Tax Items {}: {} ", taxRateId, itemData);
        return itemService.saveItem(taxRateId,itemData);
	}
    
    //We are updating the Item Entity with the information provided by the JSON
    @PostMapping("/taxrate/{taxRateId}/item/{itemId}")
	public ItemData insertItem(@PathVariable Long taxRateId,@PathVariable Long itemId,@RequestBody ItemData itemData) {
    	itemData.setItemId(itemId);
        log.info("Creating item for Tax Items with ID={} ", itemData , taxRateId);
        return itemService.saveItem(taxRateId,itemData);
        
	}
    
    //We are getting the Item entity information from the ID provided in the URI
    @GetMapping("/taxrate/{taxRateId}/item/{itemId}")
	public ItemData gettingItem(@PathVariable Long taxRateId,@PathVariable Long itemId) {
        log.info("Retrive item with ID={} for taxrate with ID={} ", itemId , taxRateId);
        return itemService.retrieveItemById(taxRateId,itemId);
        
	}

    //Delete Item by ID provided in the URI
    @DeleteMapping("/taxrate/{taxRateId}/item/{itemId}")
	public Map<String, String> deleteItem(@PathVariable Long taxRateId,@PathVariable Long itemId) {
        log.info("Delete item with ID={} for taxrate with ID={} ", itemId , taxRateId);
        itemService.deleteItemById(itemId);
        return Map.of("message","Item with ID= " + itemId + " deleted successfully.");
        
	}
    
    //Get the all data from Customer entity
    @GetMapping("/customers")
    public List<CustomerData> retrieveAllCoustomers() {
      log.info("Retrieving all contributors.");
      return itemService.retrieveAllCustomers();
    }
    
    
    //We are updating the Customer_Item join table with information in the tables
    @PostMapping("/customers/{customerId}/item/{itemId}")
	public CustomerData updateCustomerItem(@PathVariable Long customerId, @PathVariable Long itemId) {
        log.info("Creating customer Item with custimer ID {} and Item ID {}  ", customerId , itemId );
        return itemService.insertCustomerItem(customerId, itemId);
	}
    
	
}
