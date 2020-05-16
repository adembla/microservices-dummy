/**
 * 
 */
package com.hitesh.microservices.inventory.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitesh.microservices.inventory.dto.InventoryDetailDTO;
import com.hitesh.microservices.inventory.models.Product;
import com.hitesh.microservices.inventory.repository.ProductRepository;

/**
 * @author hitjoshi
 *
 */
@Service
public class ProductService {
	@Autowired
	ProductRepository inventoryRepository;

	public List<InventoryDetailDTO> checkForInventory(List<InventoryDetailDTO> inventoryDetailDTOList) {
		List<Long> idList = new ArrayList<>();
		Map<Long, InventoryDetailDTO> map = new HashMap<>();
		
		for(InventoryDetailDTO inventoryDetailDTO: inventoryDetailDTOList) {
			idList.add(inventoryDetailDTO.getInventoryId());
			map.put(inventoryDetailDTO.getInventoryId(), inventoryDetailDTO);
		}
		Iterable<Product> inventoryList = inventoryRepository.findAll(idList);
		List<InventoryDetailDTO> inventoryDetailDTOResultList = new ArrayList<>();
		for(Product inventory: inventoryList) {
			InventoryDetailDTO  inventoryDetailDTO = map.get(inventory.getId());
			inventoryDetailDTO.setAvailableQuantity(inventory.getQuantity());
			inventoryDetailDTO.setUnitPrice(inventory.getUnitPrice());
			inventoryDetailDTO.setAvailability(inventoryDetailDTO.getQuantity() < inventory.getQuantity());
			inventoryDetailDTOResultList.add(inventoryDetailDTO);
		}
		return inventoryDetailDTOResultList;
	}

	public List<InventoryDetailDTO> updateInventory(List<InventoryDetailDTO> inventoryDetailDTOList) {
		List<Long> idList = new ArrayList<>();
		Map<Long, InventoryDetailDTO> map = new HashMap<>();
		for(InventoryDetailDTO inventoryDetailDTO: inventoryDetailDTOList) {
			idList.add(inventoryDetailDTO.getInventoryId());
			map.put(inventoryDetailDTO.getInventoryId(), inventoryDetailDTO);
		}
		Iterable<Product> inventoryList = inventoryRepository.findAll(idList);
		for(Product inventory: inventoryList) {
			InventoryDetailDTO inventoryDetailDTO = map.get(inventory.getId());
			inventory.setQuantity(inventory.getQuantity()-inventoryDetailDTO.getQuantity());
			}
		Iterable<Product> savedInventory= inventoryRepository.save(inventoryList);
       if(null !=savedInventory) return inventoryDetailDTOList;
       else return null;
	}

}
