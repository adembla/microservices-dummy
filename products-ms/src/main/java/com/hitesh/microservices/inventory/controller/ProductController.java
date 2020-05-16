package com.hitesh.microservices.inventory.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hitesh.microservices.inventory.dto.InventoryDetailDTO;
import com.hitesh.microservices.inventory.models.Product;
import com.hitesh.microservices.inventory.repository.ProductRepository;
import com.hitesh.microservices.inventory.service.ProductService;

/**
 * @author Hitesh Joshi
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductRepository inventoryRepository;

	@Autowired
	ProductService inventoryService;
	

	@GetMapping(produces = "application/json")
	public List<Product> getAllinventory(){    
		List<Product> allProducts = new ArrayList<>();
		Iterable<Product> findAll = inventoryRepository.findAll();
		findAll.forEach(product -> {allProducts.add(product);});
		return allProducts;
	}

	@GetMapping(value="/{id}", produces = "application/json")
	public Product getInventoryById(@PathVariable("id") String id){        
		return inventoryRepository.findOne(Long.parseLong(id));
	}

	//    @RequestMapping(value="/inventory/name/{name}", method= RequestMethod.GET, produces = "application/json")
	//    public List<Inventory> getInventoryByName(@PathVariable("name") String InventoryName){        
	//        return inventoryRepository.findByInventoryName(InventoryName);
	//    }

	@RequestMapping(value="/inventory/new/", method= RequestMethod.POST, produces = "application/json",consumes = "application/json")
	public Product addInventory(@RequestBody Product inventory){
		return inventoryRepository.save(inventory);
	}

	/**
	 * localhost:3333/inventory/check
	 * [{
  			"inventoryId":1,
  			"quantity":9
		},
		{
  			"inventoryId":2,
  			"quantity":200	
		}]
	 * @param inventoryDetailDTOList
	 * @return
	 */
	@RequestMapping(value="/inventory/check", method= RequestMethod.POST, produces = "application/json",consumes = "application/json")
	public List<InventoryDetailDTO>  checkInventory(@RequestBody List<InventoryDetailDTO> inventoryDetailDTOList){        
		return inventoryService.checkForInventory(inventoryDetailDTOList);
	}

	/**
	 * localhost:3333/inventory/update
	 * [{
"inventoryId":1,
"quantity":10
},
{
"inventoryId":2,
"quantity":10
},
{
"inventoryId":3,
"quantity":10
}
]
	 * @param inventoryDetailDTOList
	 * @return
	 */

	@RequestMapping(value="/inventory/update", method= RequestMethod.PUT, produces = "application/json",consumes = "application/json")
	public List<InventoryDetailDTO>  updateInventory(@RequestBody List<InventoryDetailDTO> inventoryDetailDTOList){        
		return inventoryService.updateInventory(inventoryDetailDTOList);
	}


}
