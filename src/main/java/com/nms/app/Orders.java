package com.nms.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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
public class Orders implements Iterable<Order> {
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
		sandwhiches = new ArrayList<Sandwhich>();
		orders = new ArrayList<Order>();
		
		
	}
	
	public class OrderIterator implements Iterator<Order> {
		int current = 0;
		
		@Override
		public boolean hasNext() {
			if (current < Orders.this.orders.size()) {
                return true;
            } else {
                return false;
}
		}

		@Override
		public Order next() {
			if (!hasNext()) {
                throw new NoSuchElementException();
            }
			return orders.get(current++);
		}
		
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
		System.err.println("list.length: " + fList.length);
		if (fList.length <= 0) {
			throw new OrdersException("Error: There is no order file!");
		} 
		String excelFile = fList[0].getPath();

		// Open the excel file then 
		FileInputStream inputStream;
		//inputStream = new FileInputStream(new File(excelFile));
		inputStream = new FileInputStream("./data/tuckshop.xlsx");
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
			Order thisOrder = new Order();
			thisOrder = thisOrder.readOrderFromExcelRows(nextRow);
			
			//If we find a sandwhich in the previous orders, add it to the sandwhich list for later print out
			if(thisOrder.getSandwhich()!=null&&thisOrder.getSandwhich()!=""){
				sandwhiches.add(new Sandwhich(thisOrder.getSandwhich(), thisOrder.getChildFirstName()+thisOrder.getChildLastName(), thisOrder.getSpecialIntructions(), thisOrder.getChildClass()));
			}
			menuItems.storeOrderIngredients(thisOrder);
			orders.add(thisOrder);
		}
		workOrder.close();
	}

	public void displayOrders() {
		Iterator<Order> orderIterator = orders.iterator();
		while (orderIterator.hasNext()) {
			orderIterator.next().orderToString();
			//System.out.println("\n");	
		}

	}
	
	public List getOrders() {
		return this.orders;
	}
	
	public Order getOrder(int index) {
		return this.orders.get(index);
	}
	
	public List getSandwhiches() {
		return this.sandwhiches;
	}
	
	public Sandwhich getSandwhich(int index) {
		return this.sandwhiches.get(index);
	}
	
	public MenuItems getMenuItems() {
		return this.menuItems;
	}
	
	public int size() {
		return this.orders.size();
	}

	@Override
	public Iterator<Order> iterator() {
		// TODO Auto-generated method stub
		return new OrderIterator();
	}
}
