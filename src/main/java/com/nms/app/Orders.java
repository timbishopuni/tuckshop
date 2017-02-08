package com.nms.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Main processing class - contains most data containers.
 * Right now it only acocmodates 1 excel order file, but it would be quite simple to extend it to accomodate many
 * @author Tim
 *
 */
public class Orders {
	private List<Order> orders;
	private List<Sandwhich> sandwhiches;
	private MenuItems menuItems;
	private int NUM_COLUMNS = 10;
	private int OFFSET = 1;
	//"../orders"    ---For a folder outside the project folder
	private String ORDER_FOLDER = "./data";
	private File folder;
	private File[] files;

	public Orders() {
		menuItems = new MenuItems();
		
	}

	public File[] listFiles(String directoryName) {
		File directory = new File(directoryName);

		// get all the files from a directory

		File[] fList = directory.listFiles();

		return fList;
	}

	public void fillOrders() throws OrdersException, IOException {

		// Read in any files in the Orders folder
		File[] fList = listFiles(ORDER_FOLDER);
		System.out.println(fList.length);
		if (fList.length > 1) {
			throw new OrdersException("Error: You have attempted to read multiple order files at once!");
		} else if (fList.length == 0) {
			throw new OrdersException("Error: There is no order file!");
		}
		String excelFile = fList[0].getPath();

		// Initialise a new list where we will store all of the orders
		orders = new ArrayList<Order>();
		FileInputStream inputStream;
		inputStream = new FileInputStream(new File(excelFile));
		Workbook workOrder;

		// We will look at the first sheet in the excel file, (there should be
		// only one), then read row by row
		workOrder = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workOrder.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		
		//Skip the first row for the header
		Row nextRow = iterator.next();
		
		while (iterator.hasNext()) {
			nextRow = iterator.next();
			Order aOrder = new Order();
			aOrder = aOrder.readOrderFromExcelRows(nextRow);
			
			//If we find a sandwhich in the previous orders, add it to the sandwhich list for later print out
			if(aOrder.getSandwhich()!=null&&aOrder.getSandwhich()!=""){
				sandwhiches.add(new Sandwhich(aOrder.getSandwhich(), aOrder.getChildFirstName()+aOrder.getChildLastName(), aOrder.getSpecialIntructions(), aOrder.getChildClass()));
			}
			menuItems.storeOrderIngredients(aOrder);
			orders.add(aOrder);
		}
		workOrder.close();
	}

	public void displayOrders() {
		Iterator<Order> orderIterator = orders.iterator();
		while (orderIterator.hasNext()) {
			orderIterator.next().orderToString();
			System.out.println("\n");	
		}

	}
}
