package com.nms.app;

public class MenuItem {
	
	private int quantity;
	private String name;
	private String categoury;
	private double price;
	
	public MenuItem(int quantity, String name, String categoury, double price){
		this.quantity = quantity;
		this.name = name;
		this.categoury = categoury;
		this.price = price;
		
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoury() {
		return categoury;
	}
	public void setCategoury(String categoury) {
		this.categoury = categoury;
	}
	
	

}
