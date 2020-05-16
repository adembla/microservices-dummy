package com.hitesh.microservices.invoice.controller.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hitesh.microservices.invoice.dto.Item;
import com.hitesh.microservices.invoice.dto.OrderDetail;
import com.hitesh.microservices.invoice.models.Invoice;
import com.hitesh.microservices.invoice.models.Items;
import com.hitesh.microservices.invoice.repository.InvoiceRepository;
import com.hitesh.microservices.invoice.repository.ItemsRepository;
import com.hitesh.microservices.invoice.repository.ModeOfPayRepository;

/**
 * @author hitjoshi
 *
 */
@Service
public class InvoiceService {
	
    protected Logger logger = Logger.getLogger(InvoiceService.class
            .getName());
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	ModeOfPayRepository modeOfPayRepository;
	
	@Autowired
	ItemsRepository itemsRepository;
	
	@Autowired
	RestTemplate restTemplate;

	public Boolean createInvoiceAndOrder(OrderDetail orderDetail) {

		// get mode of pay first
//		ModeOfPay modeOfPay = modeOfPayRepository.findOne(orderDetail.getModePayId());
		Invoice invoice = new Invoice();
		invoice.setCustomerId(orderDetail.getCustomerId());
		invoice.setModeOfPay(orderDetail.getModePayId());
		invoice.setDateOfPurchase(orderDetail.getDateofPurChase()!=null?orderDetail.getDateofPurChase(): Calendar.getInstance().getTime());
//		invoice.setTotalTaxAmount(orderDetail.getTransactionId());
		Invoice invoiceSaved = invoiceRepository.save(invoice);
		if(null != invoiceSaved) {
			List<Item> itemList = orderDetail.getItems();
			List<Items> itemsEntityList = populateItemEntity(itemList,invoiceSaved);
			Iterable<Items> itemsList =  itemsRepository.save(itemsEntityList);
			if(null != itemsList) {
			return true;	
			}
		}
		return false;
	}
	
	public OrderDetail findOrderDetailByInvoiceId(String invoiceId) {
		  Invoice invoice =  invoiceRepository.findOne(Long.parseLong(invoiceId));
	        List<Items>  itemsList = itemsRepository.findByInvoiceId(invoice);
	        OrderDetail orderDetail = populateOrderEntry(invoice, itemsList);
	        return orderDetail;
	}
	
	private List<Items> populateItemEntity(List<Item> itemList, Invoice invoice ){
		List<Items> itemEntityList = new ArrayList<>();
		for(Item item : itemList) {
			Items itemEntity = new Items();
			itemEntity.setInvoiceId(invoice);
			itemEntity.setProdId(item.getProductId());
			itemEntity.setQuantity(item.getQuantity());
			itemEntity.setTotalCost(item.getTotalCost());
			itemEntityList.add(itemEntity);
		}
		return itemEntityList;
	}
	
	private OrderDetail populateOrderEntry(Invoice invoice, List<Items> itemsList) {
		OrderDetail  orderDetail = new OrderDetail();
		orderDetail.setCustomerId(invoice.getCustomerId());
		orderDetail.setDateofPurChase(invoice.getDateOfPurchase());
		orderDetail.setModePayId(invoice.getModeOfPay());
		orderDetail.setTotalCost(invoice.getTotalTaxAmount());
		List<Item> itemList = populateItemDTO(itemsList, invoice);
		orderDetail.setItems(itemList);
		return orderDetail;
	}
	
	private List<Item> populateItemDTO(List<Items> itemEntityList, Invoice invoice ){
		List<Item> itemList = new ArrayList<>();
		for(Items itemEntity : itemEntityList) {
			Item item = new Item();
			item.setProductId(itemEntity.getProdId());
			item.setQuantity(itemEntity.getQuantity());
			item.setTotalCost(itemEntity.getTotalCost());
			itemList.add(item);
		}
		return itemList;
	}
	

}