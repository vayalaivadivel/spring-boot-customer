package org.sample.customer.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author vadivel
 *
 */
@Entity
@Table(name = "customer")
public class Customer {
	
	
	private String name;
	private String address;
	private String phoneno;	
	
	@Column(name = "address", nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "phoneno", nullable = false)
	public String getPhoneno() {
		return phoneno;
	}
	
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	@Id
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", phoneno=" + phoneno + "]";
	}
	

}
