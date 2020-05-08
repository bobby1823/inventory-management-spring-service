package com.mindtree.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InventoryDto {

	private int productId;
	private String productName;
	private String vendor;
	private double productMrp;
	private int batchNumber;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "IST")
	private Date batchDate;
	private int quantity;
	private String status;	//Approved/Pending
	private String userName;
	
	public InventoryDto() {
		
	}

	public InventoryDto(int productId, String productName, String vendor, double productMrp, int batchNumber,
			Date batchDate, int quantity, String status, String userName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.vendor = vendor;
		this.productMrp = productMrp;
		this.batchNumber = batchNumber;
		this.batchDate = batchDate;
		this.quantity = quantity;
		this.status = status;
		this.userName = userName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public double getProductMrp() {
		return productMrp;
	}

	public void setProductMrp(double productMrp) {
		this.productMrp = productMrp;
	}

	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
	
}
