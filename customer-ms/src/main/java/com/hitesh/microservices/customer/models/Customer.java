/**
 * 
 */
package com.hitesh.microservices.customer.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author hitjoshi
 *
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 2176467627845093463L;

	@Id
	 @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name="CUST_NAME")
    private String customerName;
    
    @Column(name="CUST_GENDER")
    private String customerGender;
    
    @Column(name="CUST_PHONE_NUM")
    private Integer customerPhoneNum;
    
    @Column(name="ALT_NUM",nullable=true)
    private Integer altPhoneNum;
    
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JsonManagedReference
     private Set<Address> address;
    

    public Customer() {
		// TODO Auto-generated constructor stub
	}
    
	public Customer(Long id, String customerName, String customerGender, Integer customerPhoneNum, Integer altPhoneNum,
			Set<Address> address) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerGender = customerGender;
		this.customerPhoneNum = customerPhoneNum;
		this.altPhoneNum = altPhoneNum;
		this.address = address;
	}



	public Customer(String customerName, String customerGender, Integer customerPhoneNum, Integer altPhoneNum,
			Set<Address> address) {
		super();
		this.customerName = customerName;
		this.customerGender = customerGender;
		this.customerPhoneNum = customerPhoneNum;
		this.altPhoneNum = altPhoneNum;
		this.address = address;
	}



	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName + ", customerGender=" + customerGender
				+ ", customerPhoneNum=" + customerPhoneNum + ", altPhoneNum=" + altPhoneNum + ", address=" + address
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public Integer getCustomerPhoneNum() {
		return customerPhoneNum;
	}

	public void setCustomerPhoneNum(Integer customerPhoneNum) {
		this.customerPhoneNum = customerPhoneNum;
	}

	public Integer getAltPhoneNum() {
		return altPhoneNum;
	}

	public void setAltPhoneNum(Integer altPhoneNum) {
		this.altPhoneNum = altPhoneNum;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altPhoneNum == null) ? 0 : altPhoneNum.hashCode());
		result = prime * result + ((customerGender == null) ? 0 : customerGender.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((customerPhoneNum == null) ? 0 : customerPhoneNum.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (altPhoneNum == null) {
			if (other.altPhoneNum != null)
				return false;
		} else if (!altPhoneNum.equals(other.altPhoneNum))
			return false;
		if (customerGender == null) {
			if (other.customerGender != null)
				return false;
		} else if (!customerGender.equals(other.customerGender))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (customerPhoneNum == null) {
			if (other.customerPhoneNum != null)
				return false;
		} else if (!customerPhoneNum.equals(other.customerPhoneNum))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
