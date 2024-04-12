package com.promineotech.taxitems.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.taxitems.controller.model.ItemData;
import com.promineotech.taxitems.controller.model.TaxrateData;
import com.promineotech.taxitems.dao.ItemDao;
import com.promineotech.taxitems.dao.TaxrateDao;
import com.promineotech.taxitems.entity.Item;
import com.promineotech.taxitems.entity.Taxrate;

@Service
public class ItemService {
	
	  @Autowired
	  private TaxrateDao taxRateDao;
	  
	  @Autowired
	  private ItemDao itemDao;
	
	  @Transactional(readOnly = false)
	  public TaxrateData saveTaxrate(TaxrateData taxrateData) {
		  
		Long taxRateId = taxrateData.getTaxRateId();
		Taxrate taxRate = findOrCreateTaxrate(taxRateId);
		copyTaxRateFields(taxRate,taxrateData);
		return new TaxrateData(taxRateDao.save(taxRate));

	  }
	  
	  @Transactional(readOnly = false)
	  public ItemData saveItem(Long taxRateId, ItemData itemData) {
		  System.out.println("ZZZZZZZZ   IN saveItem  taxRateId : "+ taxRateId);
		  
		  Taxrate taxRate = findTaxrateByID(taxRateId);
		  System.out.println("ZZZZZZZZZZZZ  object taxRate  "+taxRate.toString());
		  
		  Long itemId = itemData.getItemId();
		  System.out.println("ZZZZZZZZZZZZ  what is the  itemId "+itemId);
		  
		  Item item = findOrCreateItem(itemId);
		  System.out.println("ZZZZZZZZZ  the item object "+item.toString());
		  
		  System.out.println("ZZZZZZZZZZZ   itemData BEFORE copyItemFields   "+itemData.toString());
		  copyItemFields(item,itemData);
		  System.out.println("ZZZZZZZZZZZ   petpark after copyItemFields   "+item.toString());
		  
		  item.setTaxRate(taxRate);
		  taxRate.getItems().add(item);
		  item.setTaxRateId(taxRate.getTaxRateId());
		  System.out.println("ZZZZZZZZZ  the item object before itemDao.save  "+item.toString());
		  Item dbItemData = itemDao.save(item);
		  System.out.println("ZZZZZZZZZ  the item object AFTER itemDao.save  "+item.toString());
		  return new ItemData(dbItemData);

	}


	private Item findOrCreateItem(Long itemId) {
		Item item;
		if (Objects.isNull(itemId)) {
			System.out.println("ZZZZZZZZZZZZZZ  ARE YOU COING HERE TO CREATE A NEW ITEM?????? ");
			item = new Item();
			System.out.println("Cfreated NEW item in the findOrCreateItem "+item.toString());
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
			//item.setTaxRateId(null);
			
		}
		  
		private void copyTaxRateFields(Taxrate taxRate, TaxrateData taxrateData) {
			taxRate.setRate(taxrateData.getRate());
			taxRate.setName(taxrateData.getName());
			
		}


}
