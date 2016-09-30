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


public class Orders {
	private List<Order> orders;
	private ExcelReader excelReader;
	private int NUM_COLUMNS = 10;
	private int OFFSET = 1;
	private String ORDER_FOLDER = "./orders";
	private File folder;
	private File [] files;
	
	
	public Orders(){
	}
		
	public File [] listFiles(String directoryName){
        File directory = new File(directoryName);

        //get all the files from a directory

        File[] fList = directory.listFiles();

        return fList;
	}
	public void fillOrders() throws TooManyOrdersException, IOException{
		
		//Read in any files in the Orders folder
		File[] fList = listFiles(ORDER_FOLDER);
		if(fList.length>1){
			throw new TooManyOrdersException("Error: You have attempted to read multiple order files at once!");
		}
		String excelFile = fList[0].getPath();
		
		//Initialise a new list where we will store all of the orders
		List<Order> orders = new ArrayList<Order>();
	    FileInputStream inputStream;
		inputStream = new FileInputStream(new File(excelFile));
	    Workbook workOrder;
	    
	    //We will look at the first sheet in the excel file, (there should be only one), then read row by row
		workOrder = new XSSFWorkbook(inputStream);
	    Sheet firstSheet = workOrder.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Order aOrder = new Order();
	        aOrder = aOrder.readOrderFromExcelRows(nextRow);
	        orders.add(aOrder);	
		}
	}

}
