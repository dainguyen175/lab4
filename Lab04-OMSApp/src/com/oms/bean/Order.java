package com.oms.bean;

import java.util.*;

public class Order {
	private String id;
	private String code;
	private String customerName;
	private String customerPhoneNumber;
	private String customerAddress;
	private ArrayList<OrderLine> orderLines;
	private float totalCost;
	
	public Order() {
		orderLines = new ArrayList<OrderLine>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public float getTotalCost() {
		float res = 0;
		if (orderLines!= null) {
			Iterator<OrderLine> iter = orderLines.iterator();
			float sumWeight = 0;
			for (;iter.hasNext();) {
				OrderLine ol = iter.next();
				sumWeight += ol.getWeight() * ol.getProductQuantity();
				res +=  ol.getProductCost() * ol.getProductQuantity();
			}
			if (customerAddress != null) {
				if (res < 500000)
					res+=calCost(sumWeight, checkCity(customerAddress));
			}
			System.out.println(sumWeight);
			System.out.println(customerAddress);
			System.out.println(res);
		}
		return res;
	}
	
	private boolean checkCity(String s) {
		String[] patterns = new String[] {"Hà Nội" , "HN" , "HCM" , "Hồ Chí Minh"};
		for (int i=0; i<patterns.length; i++) {
			if (s.toLowerCase().contains(patterns[i].toLowerCase()))
				return true;
		}
		return false;
	}
	
	private float calCost (float sumWeight, boolean isCity) {
		float totalCost = 0;
		float firstPoint = 3;
		float firstPrice = 22000;
		float nextPoint = 0.5f;
		float nextPrice = 2500;
		if (!isCity) {
			firstPoint = 0.5f;
			firstPrice = 30000;
			nextPoint = 0.5f;
			nextPrice = 2500;
		}
		
		if(sumWeight < firstPoint) {
			return firstPrice;
		}
		else {
			return firstPrice+(float)(Math.ceil((sumWeight-firstPoint)/0.5))*nextPrice;
		}
	}
	
	public void addOrderLine(OrderLine orderLine) {
		boolean existed = false;
		for (OrderLine ol: orderLines) {
			if (ol.getProductId().equals(orderLine.getProductId())) {
				ol.setProductQuantity(ol.getProductQuantity() + orderLine.getProductQuantity());
				existed = true;
				break;
			}
		}
		
		if (!existed) {
			orderLines.add(orderLine);
		}
	}

	public boolean search(Order order) {
		if (this.id != null && !this.id.equals("") && !this.id.contains(order.id)) {
			return false;
		}
		if (this.code != null && !this.code.equals("") && !this.code.contains(order.code)) {
			return false;
		}
		if (this.customerName != null && !this.customerName.equals("") && !this.customerName.contains(order.customerName)) {
			return false;
		}
		if (this.totalCost != 0 && this.totalCost != order.totalCost) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Order) {
			return this.code.equals(((Order) obj).code);
		}
		return false;
	}
}