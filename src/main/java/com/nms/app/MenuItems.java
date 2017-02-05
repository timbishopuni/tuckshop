package com.nms.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Commenting provided for the benefit of Andrew Whiskin
 * Class to contain the various menu items and their ingredients present on the menu.
 * Once all orders have been processed, print out all of the data contained in here to a PDF file.
 * 
 * CRITICAL - The data must be summarized via three functions, to enable easy order fulfillment:
 * 1. All Orders - Print out all of the menu item quantities - needed for purchasing
 * 2. All Pizzas - Print out of all pizzas ordered - purchasing
 * 3. All Sandwhiches -More complex. Will need to use the data contained in the Orders class. 
 * 						As the orders are being read, each time a Sandwhich is listed, create a new Sandwhich instance
 * 
 * Maybe best to move the 3 summary functions to a summary data class
 * @author tim.bishop
 *
 */
public class MenuItems {
	List <MenuItem> menuItems;
	
	public MenuItems(){
		menuItems = new ArrayList<MenuItem>();
	}
	/**
	 * Method will add amounts to the quantities of ingredients and items
	 * Will have to manually check through each selection in an order, check if it null, perform a String split around ","
	 * then check if we have a match in existing menu items
	 */
	public void storeOrderIngredients(Order order){
		
		//Sandwhich
		String [] sandwhichIngredients = order.getSandwhich().split(",");

		//Drinks
		
		//Fruit
		
		//Hot Food
		
		//Other  -- Dont currently know what this categoury of food is
		
		//Sushi
		
		//Snack -- Type Must be set to "snack"
		
		//Special Instruction -- Probably dont need to add in this at this location
		
	}
	public void addItemAfterSearch(String item, String categoury, String type){ 
		boolean match = false;
		for(int i = 0;i<menuItems.size();i++){
			if(menuItems.get(i).getName().equals(item) && menuItems.get(i).getType().equals(type)){
				menuItems.get(i).incrementQuantity();
			}
		}
		if(!match){
			menuItems.add(new MenuItem(item, categoury, type));
		}
	}


}
