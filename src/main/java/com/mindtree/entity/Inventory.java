package com.mindtree.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
@Component
public class Inventory {

	@Id
	@GeneratedValue
	private int productId;
	private String productName;
	private String vendor;
	private double productMrp;
	private int batchNumber;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "IST")
	private Date batchDate;
	private int quantity;
	private String status;	//Approved/Pending
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	public Inventory() {
	
	}

	
	
	public Inventory(int productId, String productName, String vendor, double productMrp, int batchNumber,
			Date batchDate, int quantity, String status, User user) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.vendor = vendor;
		this.productMrp = productMrp;
		this.batchNumber = batchNumber;
		this.batchDate = batchDate;
		this.quantity = quantity;
		this.status = status;
		this.user = user;
	}



	public Inventory(int productId, String productName, String vendor, double productMrp, int batchNumber,
			Date batchDate, int quantity, String status) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.vendor = vendor;
		this.productMrp = productMrp;
		this.batchNumber = batchNumber;
		this.batchDate = batchDate;
		this.quantity = quantity;
		this.status = status;
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
	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "Inventory [productId=" + productId + ", productName=" + productName + ", vendor=" + vendor
				+ ", productMrp=" + productMrp + ", batchNumber=" + batchNumber + ", batchDate=" + batchDate
				+ ", quantity=" + quantity + ", status=" + status + ", user=" + user + "]";
	}




	
		
	
	
	
}
