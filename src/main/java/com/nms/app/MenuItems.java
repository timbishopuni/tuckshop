package com.nms.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Commenting provided for the benefit of Andrew Whiskin Class to contain the
 * various menu items and their ingredients present on the menu. Once all orders
 * have been processed, print out all of the data contained in here to a PDF
 * file.
 * 
 * CRITICAL - The data must be summarized via three functions, to enable easy
 * order fulfillment: 1. All Orders - Print out all of the menu item quantities
 * - needed for purchasing 2. All Pizzas - Print out of all pizzas ordered -
 * purchasing 3. All Sandwhiches -More complex. Will need to use the data
 * contained in the Orders class. As the orders are being read, each time a
 * Sandwhich is listed, create a new Sandwhich instance
 * 
 * Maybe best to move the 3 summary functions to a summary data class
 * 
 * @author tim.bishop
 *
 */
public class MenuItems {
	private List<MenuItem> menuItems;
	private String lunch = "Lunch";
	private String snack = "Snack";

	public MenuItems() {
		menuItems = new ArrayList<MenuItem>();
	}

	/**
	 * Method will add amounts to the quantities of ingredients and items Will
	 * have to manually check through each selection in an order, check if it
	 * null, perform a String split around "," then check if we have a match in
	 * existing menu items Splitting the strings around the "," for every order
	 * item is overkill, but neccasary - it is possible for a parent to order
	 * multiple drinks or slices of pizza or other things for their child. We
	 * don't want to miss anything
	 */
	public void storeOrderIngredients(Order order) {

		// Sandwhich
		String[] sandwhichIngredients = order.getSandwhich().split(",");
		for (String ingredient : sandwhichIngredients) {
			addItemAfterSearch(ingredient, "sandwhich", lunch);
		}

		// Drinks
		String[] drinks = order.getDrinks().split(",");
		for (String drink : drinks) {
			addItemAfterSearch(drink, "Drinks", lunch);
		}
		// Fruit
		String[] fruits = order.getFruit().split(",");
		for (String fruit : fruits) {
			addItemAfterSearch(fruit, "Fruit", lunch);
		}
		// Hot Food
		String[] hotFoods = order.getHotFood().split(",");
		for (String hotFood : hotFoods) {
			addItemAfterSearch(hotFood, "Hot Food", lunch);
		}
		// Other -- Dont currently know what this categoury of food is
		String[] others = order.getOther().split(",");
		for (String other : others) {
			addItemAfterSearch(other, "Other", lunch);
		}
		// Sushi
		String[] sushis = order.getSushi().split(",");
		for (String sushi : sushis) {
			addItemAfterSearch(sushi, "Sushi", lunch);
		}
		// Snack -- Type Must be set to "snack" - I believe it corosponds to a
		// different meal time - May be wrong
		String[] snacks = order.getSnack().split(",");
		for (String snack : snacks) {
			if (snack.contains("Milk") || snack.contains("Juice")) {
				addItemAfterSearch(snack, "Drink", this.snack);
			} else {
				addItemAfterSearch(snack, "Snack", this.snack);
			}
		}

	}

	public void addItemAfterSearch(String item, String categoury, String type) {
		boolean match = false;
		for (int i = 0; i < menuItems.size(); i++) {
			if (menuItems.get(i).getName().equals(item) && menuItems.get(i).getType().equals(type)) {
				menuItems.get(i).incrementQuantity();
			}
		}
		if (!match) {
			menuItems.add(new MenuItem(item, categoury, type));
		}
	}
	/**
	 * Sort all of the items/ingredients based on their categoury (Fruit, Hot Food, Drinks etc)
	 * THIS FUNCTION REQUIRES JAVA 8 OR HIGHER
	 */
	public void sortItems(){
		menuItems.sort((o1, o2) -> o1.getCategoury().compareTo(o2.getCategoury()));
	}

}
