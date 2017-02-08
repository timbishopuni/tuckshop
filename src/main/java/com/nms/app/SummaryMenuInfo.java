package com.nms.app;

import java.util.List;

/**
 * Use this class to print the 3 seperate order summary PDF pages
 * @author Tim
 *
 */

public class SummaryMenuInfo {
	/** 
	 */
	private MenuItems menuItems; //Make sure you use the same menuItems that is filled out in the Orders class
	private List<Sandwhich> sandwhiches; //Same poiunt, use same sandwhich list from Orders
	
	public void printSandwhichOrders(){}
	public void printPizza(){}
	public void printAllOrders(){}
	
}
