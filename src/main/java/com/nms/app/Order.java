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
	// Consider adding a submitted by or parent name field in the eFroms, then
	// add it in here
	// Basic order data members
	// All of these strings are the categories(Columns) created when the orders
	// are filled out
	private String formType;
	private String submitDate;
	private double reference;
	private String childFirstName;
	private String childLastName;
	private String childClass;
	private String parentFirstName;
	private String parentLastName;
	private String parentEmail;
	private String sandwhich;
	private String drinks;
	private String fruit;
	private String hotFood;
	private String other;
	private String Sushi;
	private String snack;
	private String specialIntructions;

	private static final Logger LOGGER = Logger.getLogger(Order.class.getName());

	public Order() {
		formType = "";
		submitDate = "";
		reference= -1;
		childFirstName= "";
		childLastName= "";
		childClass= "";
		parentFirstName= "";
		parentLastName= "";
		parentEmail= "";
		sandwhich= "";
		drinks= "";
		fruit= "";
		hotFood= "";
		other= "";
		Sushi= "";
		snack= "";
		specialIntructions = "";
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
	 * Code will be adapted from
	 * http://www.codejava.net/coding/how-to-read-excel-files-in-java-using-
	 * apache-poi Essentially a copy and paste now to learn how apache poi works
	 * 
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

			// Change to fit order, columns start from index 0 and up
			if (getCellValue(nextCell) != null) {
				switch (columnIndex) {
				case 0:
					aOrder.setFormType((String) getCellValue(nextCell));
					LOGGER.info("Setting form type:  " + getCellValue(nextCell));
					break;
				case 1:
					aOrder.setSubmitDate((String) getCellValue(nextCell));
					LOGGER.info("Setting submit date:  " + getCellValue(nextCell));
					break;
				case 3:
					aOrder.setReference((Double) getCellValue(nextCell));
					LOGGER.info("Setting reference number:  " + getCellValue(nextCell));
					break;
				case 5:
					aOrder.setChildFirstName((String) getCellValue(nextCell));
					LOGGER.info("Setting child first name: " + getCellValue(nextCell));
					break;
				case 6:
					aOrder.setChildLastName((String) getCellValue(nextCell));
					LOGGER.info("Setting child last name: " + getCellValue(nextCell));
					break;
				case 7:
					aOrder.setChildClass((String) getCellValue(nextCell));
					LOGGER.info("Setting child class: " + getCellValue(nextCell));
					break;
				case 8:
					aOrder.setParentFirstName((String) getCellValue(nextCell));
					LOGGER.info("Setting parent first name: " + getCellValue(nextCell));
					break;
				case 9:
					aOrder.setParentLastName((String) getCellValue(nextCell));
					LOGGER.info("Setting parent last name: " + getCellValue(nextCell));
					break;
				case 10:
					aOrder.setParentEmail((String) getCellValue(nextCell));
					LOGGER.info("Setting Parent Email: " + getCellValue(nextCell));
					break;
				case 11:
					aOrder.setSandwhich((String) getCellValue(nextCell));
					LOGGER.info("Setting sandwhich: " + getCellValue(nextCell));
					break;
				case 12:
					aOrder.setDrinks((String) getCellValue(nextCell));
					LOGGER.info("Setting sandwhich: " + getCellValue(nextCell));
					break;
				case 13:
					aOrder.setFruit((String) getCellValue(nextCell));
					LOGGER.info("Setting Fruit: " + getCellValue(nextCell));
					break;
				case 14:
					aOrder.setOther((String) getCellValue(nextCell));
					LOGGER.info("Setting Other: " + getCellValue(nextCell));
					break;
				case 15:
					aOrder.setSushi((String) getCellValue(nextCell));
					LOGGER.info("Setting Sushi: " + getCellValue(nextCell));
					break;
				case 16:
					aOrder.setHotFood((String) getCellValue(nextCell));
					LOGGER.info("Setting Hot Food: " + getCellValue(nextCell));
					break;
				case 17:
					if (aOrder.getFormType().equals("Tuckshop Pre Primary")) {
						aOrder.setSpecialIntructions((String) getCellValue(nextCell));
						LOGGER.info("Setting PP Special Instructions: " + getCellValue(nextCell));
					} else {
						aOrder.setSnack((String) getCellValue(nextCell));
						LOGGER.info("Setting Snack: " + getCellValue(nextCell));
					}
					break;
				// For Pre Primary orders cell index 18 will not exist, so we
				// dont need to have an if statement (the while(cell.hasNext()
				// conditional take care of this))
				case 18:
					aOrder.setSpecialIntructions((String) getCellValue(nextCell));
					LOGGER.info("Setting Primary Special Instructions: " + getCellValue(nextCell));
				}
			}
		}
		return aOrder;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public double getReference() {
		return reference;
	}

	public void setReference(double d) {
		this.reference = d;
	}

	public void orderToString() {
		System.out.println(childFirstName + " " + childLastName + " " + childClass + " " + parentFirstName + " "
				+ parentLastName + " " + parentEmail + " " + sandwhich + " " + drinks + " " + fruit + " " + hotFood
				+ " " + other + " " + specialIntructions);
	}

	public String getFruit() {
		return fruit;
	}

	public void setFruit(String fruit) {
		this.fruit = fruit;
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

	public String getDrinks() {
		return drinks;
	}

	public void setDrinks(String drinks) {
		this.drinks = drinks;
	}

	public String getSpecialIntructions() {
		return specialIntructions;
	}

	public void setSpecialIntructions(String specialIntructions) {
		this.specialIntructions = specialIntructions;
	}

	public String getParentFirstName() {
		return parentFirstName;
	}

	public void setParentFirstName(String parentFirstName) {
		this.parentFirstName = parentFirstName;
	}

	public String getParentLastName() {
		return parentLastName;
	}

	public void setParentLastName(String parentLastName) {
		this.parentLastName = parentLastName;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public String getSandwhich() {
		return sandwhich;
	}

	public void setSandwhich(String sandwhich) {
		this.sandwhich = sandwhich;
	}

	public String getSnack() {
		return snack;
	}

	public void setSnack(String snack) {
		this.snack = snack;
	}

	public String getHotFood() {
		return hotFood;
	}

	public void setHotFood(String hotFood) {
		this.hotFood = hotFood;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getSushi() {
		return Sushi;
	}

	public void setSushi(String sushi) {
		Sushi = sushi;
	}
}
