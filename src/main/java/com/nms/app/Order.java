package com.nms.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Order {
	//Consider adding a submitted by or parent name field in the eFroms, then add it in here
	//Basic order data members
	//All of these strings are the categories(Columns) created when the orders are filled out
	private String childFirstName;
	private String childLastName;
	private String childClass;
	private String program;	//Tag for optional removal
	private String drinks;
	private String chickenSandwhich;
	private String hamSandwhich;
	private String vegemiteSandwhich;
	//Upon looking at the menu options we should have 1 sandwhich category, with multiple options
	private String sandwhich;
	private String snack;
	private String hotFood;
	private String other;
	private String Sushi;
	private String dietaryRequirements;
	private String specialIntructions;
	
	 private static final Logger LOGGER = Logger.getLogger(Order.class.getName());
	
	
	
	public Order(){
	}
	private Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	    }
	 
	    return null;
	}
	
	/**
	 * Code will be adapted from http://www.codejava.net/coding/how-to-read-excel-files-in-java-using-apache-poi
	 * Essentially a copy and paste now to learn how apache poi works
	 * @param excelFilePath
	 * @return
	 * @throws IOException
	 */
	public Order readOrderFromExcelRows(Row orderRow) throws IOException {
	    Iterator<Cell> cellIterator = orderRow.cellIterator();
	    Order aOrder = new Order();
	    while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            int columnIndex = nextCell.getColumnIndex();
	 
	            
	            //Change to fit order
	            switch (columnIndex) {
	            case 5:
	                aOrder.setChildFirstName((String) getCellValue(nextCell));
	                LOGGER.info("Setting child first name: "+getCellValue(nextCell));
	                break;
	            case 6:
	                aOrder.setChildLastName((String) getCellValue(nextCell));
	                LOGGER.info("Setting child last name: "+getCellValue(nextCell));
	                break;
	            case 7:
	                aOrder.setChildClass((String) getCellValue(nextCell));
	                LOGGER.info("Setting child class: "+getCellValue(nextCell));
	                break;
	            case 8:
	            	aOrder.setProgram((String)getCellValue(nextCell));
	            	LOGGER.info("Setting child program: "+getCellValue(nextCell));
	            	break;
	            case 9:
	            	aOrder.setDrinks((String)getCellValue(nextCell));
	            	LOGGER.info("Setting drinks: "+getCellValue(nextCell));
	            	break;
	            case 10:
	            	aOrder.setChickenSandwhich((String)getCellValue(nextCell));
	            	LOGGER.info("Setting chicken sandwhich: "+getCellValue(nextCell));
	            	break;
	            case 11:
	            	aOrder.setHamSandwhich((String)getCellValue(nextCell));
	            	LOGGER.info("Setting ham sandwhich: "+getCellValue(nextCell));
	            	break;
	            case 12:
	            	aOrder.setVegemiteSandwhich((String)getCellValue(nextCell));
	            	LOGGER.info("Setting vegemite sandwhich: "+getCellValue(nextCell));
	            	break;
	            case 13:
	            	aOrder.setSpecialIntructions((String)getCellValue(nextCell));
	            	LOGGER.info("Setting special instructions: "+getCellValue(nextCell));
	            	break;
	           
	            }
	        } 
	    return aOrder;
	}
	public void orderToString(){
		System.out.println(childFirstName+" "+childLastName+ " " +childClass+" "+program+" "+drinks+" "+" "+chickenSandwhich+" "+hamSandwhich+" "+vegemiteSandwhich+" "+dietaryRequirements+" "+specialIntructions);
	}

	
	public String getChildFirstName() {
		return childFirstName;
	}
	public void setChildFirstName(String childFirstName) {
		this.childFirstName = childFirstName;
	}
	public String getChildLastName() {
		return childLastName;
	}
	public void setChildLastName(String childLastName) {
		this.childLastName = childLastName;
	}
	public String getChildClass() {
		return childClass;
	}
	public void setChildClass(String childClass) {
		this.childClass = childClass;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getDrinks() {
		return drinks;
	}
	public void setDrinks(String drinks) {
		this.drinks = drinks;
	}
	public String getChickenSandwhich() {
		return chickenSandwhich;
	}
	public void setChickenSandwhich(String chickenSandwhich) {
		this.chickenSandwhich = chickenSandwhich;
	}
	public String getHamSandwhich() {
		return hamSandwhich;
	}
	public void setHamSandwhich(String hamSandwhich) {
		this.hamSandwhich = hamSandwhich;
	}
	public String getVegemiteSandwhich() {
		return vegemiteSandwhich;
	}
	public void setVegemiteSandwhich(String vegemiteSandwhich) {
		this.vegemiteSandwhich = vegemiteSandwhich;
	}
	public String getDietaryRequirements() {
		return dietaryRequirements;
	}
	public void setDietaryRequirements(String dietaryRequirements) {
		this.dietaryRequirements = dietaryRequirements;
	}
	public String getSpecialIntructions() {
		return specialIntructions;
	}
	public void setSpecialIntructions(String specialIntructions) {
		this.specialIntructions = specialIntructions;
	}
}
