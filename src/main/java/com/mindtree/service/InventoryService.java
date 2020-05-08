package com.mindtree.service;

import java.util.List;

import com.mindtree.dto.InventoryDto;
import com.mindtree.entity.Inventory;
import com.mindtree.exception.NoInventoryRecord;
import com.mindtree.exception.UserNotExistsException;

public interface InventoryService {
	
	public String addProduct(Inventory inventory,String userRole,String userName) throws UserNotExistsException;
	public List<InventoryDto> getAllProduct() throws NoInventoryRecord; //for Store Manager
	public List<InventoryDto> getProductOfUser(String userName) throws UserNotExistsException, NoInventoryRecord; //for particular user
	public List<InventoryDto> getPendingProducts() throws NoInventoryRecord; //For approval
	public String approveProducts(int productId);
	public String deleteProduct(int productId);
	public String deleteProductByStoreManager(int productId);
	public List<InventoryDto> getPendingProductsUser(String userName) throws NoInventoryRecord; //For department manager to check status
	public String updateProduct(InventoryDto product) throws UserNotExistsException;
	public InventoryDto getProductById(int productId) throws NoInventoryRecord;
	
}
