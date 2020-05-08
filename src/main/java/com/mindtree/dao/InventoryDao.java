package com.mindtree.dao;

import java.util.List;

import com.mindtree.GenericDao.GenericDao;
import com.mindtree.entity.Inventory;

public interface InventoryDao extends GenericDao<Inventory, Integer>{
	
	//public void addProducts(Inventory inventory);
	public List<Inventory> getAllProducts();
	public List<Inventory> getProductOfUser(int UserId); //for particular user
	//public List<Inventory> getPendingProducts(); //For approval
	public void approveProduct(int productId);
	public void deleteProduct(int productId);
	//public void deleteProductByStoreManager(int productId);
	//public void updateProduct(Inventory inventory);
}
