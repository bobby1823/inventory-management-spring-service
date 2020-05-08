package com.mindtree.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mindtree.AppConfig.AppConfigSettings;
import com.mindtree.dto.InventoryDto;
import com.mindtree.entity.Inventory;
import com.mindtree.exception.NoInventoryRecord;
import com.mindtree.exception.UserNotExistsException;
import com.mindtree.service.InventoryService;

@RestController
@EnableWebMvc
@RequestMapping(value = "/inventory")
@CrossOrigin("*")
public class InventoryController {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfigSettings.class);
	
	InventoryService inventoryService = context.getBean(InventoryService.class);
	
	
	@PostMapping(value = "/addproduct/{userRole}/{userName}")
	public ResponseEntity<String> addProducts(@RequestBody Inventory inventory, @PathVariable("userRole") String userRole, @PathVariable("userName") String userName){
		try {
			inventoryService.addProduct(inventory, userRole, userName);
			
		} catch (UserNotExistsException e) {
			return new ResponseEntity<String>("No user to add products",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("Product Successfully Added",HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/getuserproducts/{userName}")
	public ResponseEntity<List<InventoryDto>> getProductOfUser(@PathVariable("userName") String userName) throws UserNotExistsException, NoInventoryRecord{
		List<InventoryDto> inventoryRecord = new ArrayList<>();
		try {
			inventoryRecord = inventoryService.getProductOfUser(userName);
		} catch (UserNotExistsException e) {
			return new ResponseEntity<>(inventoryRecord,HttpStatus.NO_CONTENT);
		} catch(NoInventoryRecord e) {
			return new ResponseEntity<List<InventoryDto>>(inventoryRecord,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InventoryDto>>(inventoryRecord,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getpendingproducts")
	public ResponseEntity<List<InventoryDto>> getPendingProducts() throws NoInventoryRecord {
		List<InventoryDto> pendingProducts = new ArrayList<>();
		try {
			pendingProducts = inventoryService.getPendingProducts();
			
		} catch (NoInventoryRecord e) {
			return new ResponseEntity<List<InventoryDto>>(pendingProducts,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InventoryDto>>(pendingProducts,HttpStatus.OK);
	}
	
	@GetMapping(value = "/approveproduct/{productId}")
	public ResponseEntity<String> approveProducts(@PathVariable("productId") int productId){
		return new ResponseEntity<String>(inventoryService.approveProducts(productId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getallproducts")
	public ResponseEntity<List<InventoryDto>> getAllproducts(){
		List<InventoryDto> inventoryRecord = new ArrayList<>();
		try {
			inventoryRecord = inventoryService.getAllProduct();
		} catch (NoInventoryRecord e) {
			return new ResponseEntity<List<InventoryDto>>(inventoryRecord,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InventoryDto>>(inventoryRecord,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteproduct/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId){
		System.out.println(productId);
		return new ResponseEntity<>(inventoryService.deleteProduct(productId),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/SMdeleteproduct/{productId}")
	public ResponseEntity<String> deleteProductByStoreManager(@PathVariable("productId") int productId){
		System.out.println(productId);
		return new ResponseEntity<>(inventoryService.deleteProductByStoreManager(productId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/pendingproduct/{userName}")
	public ResponseEntity<List<InventoryDto>> pendingProductsUser(@PathVariable("userName") String userName) throws UserNotExistsException, NoInventoryRecord{
		List<InventoryDto> inventoryRecord = new ArrayList<>();
		try {
			inventoryRecord = inventoryService.getPendingProductsUser(userName);
		} catch (NoInventoryRecord e) {
			return new ResponseEntity<List<InventoryDto>>(inventoryRecord,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InventoryDto>>(inventoryRecord,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getproductId/{productId}")
	public ResponseEntity<InventoryDto> pendingProductsUsesr(@PathVariable("productId") int productId) throws NoInventoryRecord {
		InventoryDto inventory = new InventoryDto();
		try {
			inventory = inventoryService.getProductById(productId);
		} catch (NoInventoryRecord e) {
			return new ResponseEntity<>(inventory,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<InventoryDto>(inventory,HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/updateproduct")
	public ResponseEntity<String> updateInventory(@RequestBody InventoryDto inventory){
		try {
			inventoryService.updateProduct(inventory);
		} catch (UserNotExistsException e) {
			return new ResponseEntity<>("No User Found",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
	}
	

}
