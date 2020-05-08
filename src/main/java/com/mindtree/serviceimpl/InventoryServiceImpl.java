package com.mindtree.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.dao.InventoryDao;
import com.mindtree.dao.UserDao;
import com.mindtree.dto.InventoryDto;
import com.mindtree.entity.Inventory;
import com.mindtree.entity.User;
import com.mindtree.exception.NoInventoryRecord;
import com.mindtree.exception.UserNotExistsException;
import com.mindtree.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	UserDao userRepo;
	
	@Autowired
	InventoryDao inventoryDao;
	
	@Transactional
	@Override
	public String addProduct(Inventory inventory, String userRole, String userName) throws UserNotExistsException {
		User userInfo = userRepo.validUser(userName);
		if(userInfo == null) {
			throw new UserNotExistsException("Invalid User");
		}
		inventory.setUser(userInfo);
		if(userRole.equalsIgnoreCase("department manager")) {
			inventory.setStatus("Pending");
		}
		else if(userRole.equalsIgnoreCase("store manager")) {
			inventory.setStatus("Approved");
		}
		inventoryDao.add(inventory);
		return "Added to Inventory";
	}

	@Transactional
	@Override
	public List<InventoryDto> getAllProduct() throws NoInventoryRecord {
		List<Inventory> inventoryDetails = inventoryDao.getAllProducts();
		List<InventoryDto> inventoryRecord = new ArrayList<>();
		if(inventoryDetails.size() != 0) {
			inventoryDetails.forEach(e -> {
				InventoryDto inventory = new InventoryDto();
				inventory.setProductId(e.getProductId());
				inventory.setProductName(e.getProductName());
				inventory.setVendor(e.getVendor());
				inventory.setProductMrp(e.getProductMrp());
				inventory.setBatchNumber(e.getBatchNumber());
				inventory.setBatchDate(e.getBatchDate());
				inventory.setQuantity(e.getQuantity());
				inventory.setStatus(e.getStatus());
				inventory.setUserName(e.getUser().getUserName());
				inventoryRecord.add(inventory);
			});
		}
		else {
			throw new NoInventoryRecord("No records Found");
		}
		return inventoryRecord;
		
	}

	@Transactional
	@Override
	public List<InventoryDto> getProductOfUser(String userName) throws UserNotExistsException, NoInventoryRecord {
		User user = userRepo.validUser(userName);
		List<Inventory> inventoryDetails = new ArrayList<>();
		List<InventoryDto> inventoryRecord = new ArrayList<>();
		if(user == null) {
			throw new UserNotExistsException("No user Present");
		}
		
		inventoryDetails = inventoryDao.getProductOfUser(user.getUserId());
		if(inventoryDetails.size() != 0) {
			inventoryDetails.forEach(e -> {
				InventoryDto inventory = new InventoryDto();
				inventory.setProductId(e.getProductId());
				inventory.setProductName(e.getProductName());
				inventory.setVendor(e.getVendor());
				inventory.setProductMrp(e.getProductMrp());
				inventory.setBatchNumber(e.getBatchNumber());
				inventory.setBatchDate(e.getBatchDate());
				inventory.setQuantity(e.getQuantity());
				inventory.setStatus(e.getStatus());
				inventory.setUserName(e.getUser().getUserName());
				inventoryRecord.add(inventory);
			});
		}
		else {
			throw new NoInventoryRecord("No records Found");
		}
		return inventoryRecord;
		
	}

	@Transactional
	@Override
	public List<InventoryDto> getPendingProducts() throws NoInventoryRecord {
		List<Inventory> inventoryDetails = new ArrayList<>();
		List<InventoryDto> inventoryRecord = new ArrayList<>();
		inventoryDetails = inventoryDao.getAll();
		if(inventoryDetails.size() != 0) {
			inventoryDetails.forEach(e -> {
				InventoryDto inventory = new InventoryDto();
				inventory.setProductId(e.getProductId());
				inventory.setProductName(e.getProductName());
				inventory.setVendor(e.getVendor());
				inventory.setProductMrp(e.getProductMrp());
				inventory.setBatchNumber(e.getBatchNumber());
				inventory.setBatchDate(e.getBatchDate());
				inventory.setQuantity(e.getQuantity());
				inventory.setStatus(e.getStatus());
				inventory.setUserName(e.getUser().getUserName());
				inventoryRecord.add(inventory);
			});
		}
		else {
			throw new NoInventoryRecord("No records Found");
		}
		return inventoryRecord.stream().filter(e -> e.getStatus().equalsIgnoreCase("pending")).collect(Collectors.toList());
		
		
	}

	@Transactional
	@Override
	public String approveProducts(int productId) {
		//User user = userRepo.validUser(userName);
		//System.out.println(user.getUserId());
		inventoryDao.approveProduct(productId);
		return "Product Approved";
	}

	@Transactional
	@Override
	public String deleteProduct(int productId) {
		inventoryDao.deleteProduct(productId);
		return "Product Deleted";
	}

	@Transactional
	@Override
	public String deleteProductByStoreManager(int productId) {
		inventoryDao.remove(productId);
		return "Product Deleted";
	}

	@Transactional
	@Override
	public List<InventoryDto> getPendingProductsUser(String userName) throws NoInventoryRecord {
		List<Inventory> inventoryDetails = new ArrayList<>();
		List<InventoryDto> inventoryRecord = new ArrayList<>();
		inventoryDetails = inventoryDao.getAll();
		if(!inventoryDetails.isEmpty()) {
			inventoryDetails.forEach(e -> {
				InventoryDto inventory = new InventoryDto();
				inventory.setProductId(e.getProductId());
				inventory.setProductName(e.getProductName());
				inventory.setVendor(e.getVendor());
				inventory.setProductMrp(e.getProductMrp());
				inventory.setBatchNumber(e.getBatchNumber());
				inventory.setBatchDate(e.getBatchDate());
				inventory.setQuantity(e.getQuantity());
				inventory.setStatus(e.getStatus());
				inventory.setUserName(e.getUser().getUserName());
				inventoryRecord.add(inventory);
			});
		}
		else {
			throw new NoInventoryRecord("No records Found");
		}
		return inventoryRecord.stream().filter(e -> e.getStatus().equalsIgnoreCase("pending")).collect(Collectors.toList())
				.stream().filter(s -> s.getUserName().equalsIgnoreCase(userName)).collect(Collectors.toList());
		
	}

	@Transactional
	@Override
	public String updateProduct(InventoryDto product) throws UserNotExistsException {
		Inventory inventory = new Inventory();
		User userInfo = userRepo.validUser(product.getUserName());
		if(userInfo == null) {
			throw new UserNotExistsException("No User Found");
		}
		inventory.setProductId(product.getProductId());
		inventory.setProductName(product.getProductName());
		inventory.setProductMrp(product.getProductMrp());
		inventory.setVendor(product.getVendor());
		inventory.setBatchNumber(product.getBatchNumber());
		inventory.setBatchDate(product.getBatchDate());
		inventory.setQuantity(product.getQuantity());
		inventory.setUser(userInfo);
		if(userInfo.getUserRole().equalsIgnoreCase("department manager")) {
			inventory.setStatus("Pending");
		} else if(userInfo.getUserRole().equalsIgnoreCase("store manager")) {
			inventory.setStatus("Approved");
		}
		inventoryDao.update(inventory);
		return "Product Updated";
	}

	@Transactional
	@Override
	public InventoryDto getProductById(int productId) throws NoInventoryRecord {
		List<Inventory> inventoryDetails = new ArrayList<>();
		List<InventoryDto> inventoryRecord = new ArrayList<>();
		inventoryDetails = inventoryDao.getAllProducts();
		if(!inventoryDetails.isEmpty()) {
			inventoryDetails.forEach(e -> {
				InventoryDto inventory = new InventoryDto();
				inventory.setProductId(e.getProductId());
				inventory.setProductName(e.getProductName());
				inventory.setVendor(e.getVendor());
				inventory.setProductMrp(e.getProductMrp());
				inventory.setBatchNumber(e.getBatchNumber());
				inventory.setBatchDate(e.getBatchDate());
				inventory.setQuantity(e.getQuantity());
				inventory.setStatus(e.getStatus());
				inventory.setUserName(e.getUser().getUserName());
				inventoryRecord.add(inventory);
			});
		}
		if(inventoryRecord.isEmpty())  {
			throw new NoInventoryRecord("No Products Found");
		}
		InventoryDto inventory =  inventoryRecord.stream().filter(e -> e.getProductId() == productId).collect(Collectors.toList()).get(0);
		return inventory;
		
	}

}
