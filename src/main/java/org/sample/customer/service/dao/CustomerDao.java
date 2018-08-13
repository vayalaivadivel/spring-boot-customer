package org.sample.customer.service.dao;

import java.util.List;

import org.sample.customer.service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author vadivel
 *
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, String> {
	@Query("SELECT p FROM Customer p WHERE p.name LIKE %:name%")
	public List<Customer> searchByName(@Param("name") String name);
}
