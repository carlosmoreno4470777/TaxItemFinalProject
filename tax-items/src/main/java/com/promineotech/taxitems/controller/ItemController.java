package com.promineotech.taxitems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.taxitems.controller.model.ItemData;
import com.promineotech.taxitems.controller.model.TaxrateData;
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
    
    @PostMapping("/taxrate/{taxRateId}/item")
    @ResponseStatus(code = HttpStatus.CREATED)
	public ItemData insertItem(@PathVariable Long taxRateId,@RequestBody ItemData itemData) {
        log.info("Creating item to Tax Items {}: {} ", taxRateId, itemData);
        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ taxRateId  " + taxRateId);
        return itemService.saveItem(taxRateId,itemData);
	}
    
    
	
}
