package com.nms.app;

/**
 * 
 * @author Tim This class is used to hold the information for all the 
 *         menu items. This class focuses more on the individual ingredients, (for sandwiches).
 *         
 *         This class should have a number of instances within the MenItems class, 
 *         one for each individual menu item. Each time an oder is read, all of the information from the
 *         order should be updated. Use String matching and an array search to locate the correctly named menu item
 *         , then increment the menu item quantity.
 *         
 */

public class MenuItem {

	private int quantity;
	private String name;
	private String categoury; // Lunch or Snack
	private String type; // Hot food cold food etc

	public MenuItem(int quantity, String name, String categoury, double price) {
		this.quantity = quantity;
		this.name = name;
		this.categoury = categoury;

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
