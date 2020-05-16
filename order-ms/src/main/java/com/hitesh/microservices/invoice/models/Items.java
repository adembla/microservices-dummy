/**
 * 
 */
package com.hitesh.microservices.invoice.models;

/**
 * @author hitjoshi
 *
 */
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author hitjoshi
 *
 */
@Entity
@Table(name = "orderitems")
public class Items {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER) 
	@JsonManagedReference
	@JoinColumn(name = "INVOICE_ID")
	private Invoice invoiceId;
	
	@Column(name="PROD_ID")
	private Long prodId;

	@Column(name="QUANTITY", nullable= true)
	private Integer quantity;
	
	@Column(name="TOTAL_COST", nullable= true)
	private BigInteger totalCost;
	
	@Column(name="TRANSACTION_ID", nullable= true)
	private String transactionId;

	public Invoice getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Invoice invoiceId) {
		this.invoiceId = invoiceId;
	}


	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public BigInteger getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigInteger totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
		result = prime * result + ((prodId == null) ? 0 : prodId.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
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
		Items other = (Items) obj;
		if (invoiceId == null) {
			if (other.invoiceId != null)
				return false;
		} else if (!invoiceId.equals(other.invoiceId))
			return false;
		if (prodId == null) {
			if (other.prodId != null)
				return false;
		} else if (!prodId.equals(other.prodId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		return true;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", invoiceId=" + invoiceId + ", prodId=" + prodId + ", quantity=" + quantity
				+ ", totalCost=" + totalCost + ", transactionId=" + transactionId + "]";
	}

	public Items(Long id, Invoice invoiceId, Long prodId, Integer quantity, BigInteger totalCost,
			String transactionId) {
		super();
		this.id = id;
		this.invoiceId = invoiceId;
		this.prodId = prodId;
		this.quantity = quantity;
		this.totalCost = totalCost;
		this.transactionId = transactionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Items() {
	}
	
}
