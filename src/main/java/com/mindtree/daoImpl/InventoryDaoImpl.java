package com.mindtree.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.GenericDaoImpl.GenericDaoImpl;
import com.mindtree.dao.InventoryDao;
import com.mindtree.entity.Inventory;

@Repository
public class InventoryDaoImpl extends GenericDaoImpl<Inventory, Integer> implements InventoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/*@Override
	public void addProducts(Inventory inventory) {
		sessionFactory.getCurrentSession().save(inventory);
		
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> getAllProducts() {
		return sessionFactory.getCurrentSession().createQuery("from Inventory where status = 'Approved'").getResultList();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> getProductOfUser(int userId) {
		return sessionFactory.getCurrentSession().createQuery("from Inventory i where i.user.userId = :userId and i.status = 'Approved'").setParameter("userId", userId).getResultList(); 
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> getPendingProducts() {
		return sessionFactory.getCurrentSession().createQuery("from Inventory").getResultList();
		
	}*/

	@Override
	public void approveProduct(int productId) {
		System.out.println("Here");
		Session session = sessionFactory.getCurrentSession();
		Inventory inventory = session.byId(Inventory.class).load(productId);
		inventory.setStatus("Approved");
		session.flush();
				
		
	}

	@Override
	public void deleteProduct(int productId) {
		Session session = sessionFactory.getCurrentSession();
		Inventory inventoryProduct = session.byId(Inventory.class).load(productId);
		inventoryProduct.setStatus("Pending");
		session.flush();
	}

	/*@Override
	public void deleteProductByStoreManager(int productId) {
		Session session = sessionFactory.getCurrentSession();
		Inventory inventoryProduct = session.byId(Inventory.class).load(productId);
		session.delete(inventoryProduct);
		
	}*/

	/*@Override
	public void updateProduct(Inventory inventory) {
		sessionFactory.getCurrentSession().update(inventory);
		
	}*/
	
	
	

}
