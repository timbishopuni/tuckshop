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
	private String category; // Hot food, sushi, sandwhich etc
	private String type; // I think this refers to lunch or snack

	/**
	 * This constructor should be used for initial setup of menu items
	 * If there is not already a matching menu item in within the MenuItems list, a new instance will be created
	 * @param Name
	 * @param category
	 */
	public MenuItem(String name, String category, String type){
		this.quantity = 1;
		this.name = name;
		this.category = category;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getcategory() {
		return category;
	}

	public void setcategory(String category) {
		this.category = category;
	}
	public void incrementQuantity(){
		this.quantity+=1;
	}

}
