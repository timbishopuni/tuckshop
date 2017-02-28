package com.nms.app;

import java.util.Scanner;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.ListIterator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.interfaces.PdfViewerPreferences;
/**
 * PDF Writer alternative using iText Will most likely implement instead of
 * PDFBox Sample code taken for learning purposes from
 * http://www.vogella.com/tutorials/JavaPDF/article.html
 * 
 * @author Andrew Whiskin
 *
 */
public class PDFWriter {
	private static String FILE = "./tuckshop.pdf";
	/* private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	*/
	private static Font defaultFont = new Font(FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
	private static Font defaultFontBold = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK));
	private static Font defaultFontBoldItalic = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 10, BaseColor.BLACK));
	private static Font defaultFontItalic = new Font(FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, BaseColor.BLACK));
	private static Font defaultSpecialFontItalic = new Font(FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 8, BaseColor.BLACK));
	private static int marginSize = 10;
	private static Rectangle pageSize = PageSize.A6;

	// Add meta data to the PDF document
	private static void addMetaData(Document document) {
		document.addTitle("NMS Tuckshop Labels");
		document.addSubject("Labels");
		document.addKeywords("Labels, Tuckshop, NMS");
	}

	/** 
	 * Check if field is empty or null
	 * @return Returns true if field is valid
	 * */
	private static <T> boolean checkField(T field) {
		if (field != null && field != "") {
			return true;			
		} else {
			return false;
		}
	}

	private static Paragraph generateCommonParagraph(Order order) {
		// New paragraph; set font
		Paragraph para = new Paragraph();
		para.setFont(defaultFont);
		
		// OrderID section
		Phrase orderIDContainer = new Phrase("ORDER: ",defaultFont);
		Chunk orderID =  new Chunk((int)order.getReference()+"\n",defaultFontBold);
		orderIDContainer.add(orderID);
		para.add(orderIDContainer);
		
		// Child Name Section
		Phrase childNameContainer = new Phrase ("NAME: ",defaultFont);
		Chunk childName = new Chunk (order.getChildLastName()+", "+order.getChildFirstName()+"\n",defaultFontBold);
		childNameContainer.add(childName);
		para.add(childNameContainer);
		
		// Child Class Section
		Phrase childClassContainer = new Phrase ("CLASS: ");
		Chunk childClass = new Chunk(order.getChildClass()+"\n",defaultFontBold);
		childClassContainer.add(childClass);
		para.add(childClassContainer);
		
		if (order.hasSpecialInstructions()) {
			// Special Instruction Section
			Phrase specialInstructionsContainer = new Phrase ("SPECIAL INSTRUCTIONS:\n",defaultFontBoldItalic);
			Chunk specialInstructions = new Chunk(order.getSpecialIntructions()+"\n",defaultSpecialFontItalic);
			specialInstructionsContainer.add(specialInstructions);
			para.add(specialInstructionsContainer);
		}

		para.add(new Chunk(new DottedLineSeparator()));
		para.add(new Chunk("\n"));
		return para;
	}
	
	private static void addOrders(Document doc, Orders orders) {
		Scanner in = new Scanner(System.in);
		System.out.println("Please select which labels you would like to produce:\n"
				+ "Type 'a' for all - this will produce one PDF with all labels combined\n"
				+ "Type 'h' for Hot Food orders\n"
				+ "Type 's' for Sushi orders\n"
				+ "Type 'm' for Morning Tea orders\n"
				+ "Type 'l' for Lunch orders\n");

		String userInput = in.nextLine();
		switch(userInput) {
			case "a": processAllOrders(doc, orders); break;
			case "h": processHotFood(doc, orders); break;
			case "s": processSushi(doc, orders); break;
			case "m": processSnack(doc, orders); break;
			case "l": processLunch(doc, orders); break;
			default: System.out.println("\nInvalid input. Try typing without quotes or apostrophes.\n"); addOrders(doc, orders);
		}
	}
	
	private static void processAllOrders(Document doc, Orders orders) {
		for (Order order : orders) {
			if (order.getSnack() != null && order.getSnack() != "") {
				addSnackPage(doc, order);
				pageBreak(doc);
			}
			addRemainingPage(doc, order);
			if (order.getHotFood() != null && order.getHotFood() != "") {
				addHotFoodPage(doc, order);
				pageBreak(doc);
			}
			if (order.getSushi() != null && order.getSushi() != "") {
				addSushiPage(doc, order);
				pageBreak(doc);
			}
		}
	}
	
	private static void processHotFood(Document doc, Orders orders) {
		for (Order order : orders) {
			if (order.getHotFood() != null && order.getHotFood() != "") {
				addHotFoodPage(doc, order);
				pageBreak(doc);
			}
		}
		System.out.println("processed hot food orders");
	}
	
	private static void processSushi(Document doc, Orders orders) {
		for (Order order : orders) {
			if (order.getSushi() != null && order.getSushi() != "") {
				addSushiPage(doc, order);
				pageBreak(doc);
			}
		}
		System.out.println("processed sushi orders");
	}
	
	private static void processSnack(Document doc, Orders orders) {
		for (Order order : orders) {
			if (order.getSnack() != null && order.getSnack() != "") {
				addSnackPage(doc, order);
				pageBreak(doc);
			}
		}
		System.out.println("processed morning tea orders");
	}
	
	private static void processLunch(Document doc, Orders orders) {
		for (Order order : orders) {
			addRemainingPage(doc, order);
		}
		System.out.println("processed lunch orders");
	}
	
	private static void addRemainingPage(Document doc, Order order) {
		Paragraph para = generateCommonParagraph(order);
		
		// Sandwhich
		para.add(getSandwhichContainer(order));
		// Drinks
		para.add(getDrinksContainer(order));
		// Fruit
		para.add(getFruitContainer(order));
		// Other
		para.add(getOtherContainer(order));
		
		try {
			// Add paragraph to page and page break
			doc.add(para);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		pageBreak(doc);
	}
	
	private static Phrase getSandwhichContainer(Order order) {
		if (checkField(order.getSandwhich())) {
			Phrase sandwhichContainer = new Phrase ("SANDWHICH:\n",defaultFontBold);
			Chunk sandwhich = new Chunk(splitOrderContents(order.getSandwhich()),defaultFont);
			sandwhichContainer.add(sandwhich);
		return sandwhichContainer;
		} else {
			return null;
		}
	}
	
	private static Phrase getDrinksContainer(Order order) {
		if (checkField(order.getDrinks())) {
			Phrase drinksContainer = new Phrase ("DRINKS:\n",defaultFontBold);
			Chunk drinks = new Chunk(splitOrderContents(order.getDrinks()),defaultFont);
			drinksContainer.add(drinks);
			return drinksContainer;
		} else {
			return null;
		}
	}
	
	private static Phrase getFruitContainer(Order order) {
		if (checkField(order.getFruit())) {
			Phrase fruitContainer = new Phrase ("FRUIT:\n",defaultFontBold);
			Chunk fruit = new Chunk(splitOrderContents(order.getFruit()),defaultFont);
			fruitContainer.add(fruit);
			return fruitContainer;			
		} else {
			return null;
		}
	}
	
	private static Phrase getOtherContainer(Order order) {
		if (checkField(order.getOther())) {
			Phrase otherContainer = new Phrase ("OTHER:\n",defaultFontBold);
			Chunk other = new Chunk(splitOrderContents(order.getOther()),defaultFont);
			otherContainer.add(other);
			return otherContainer;
		} else {
			return null;
		}
	}

	private static void addHotFoodPage(Document doc, Order order) {
		Paragraph para = generateCommonParagraph(order);
		
		Phrase hotFoodContainer = new Phrase ("HOT FOOD:\n",defaultFontBold);
		Chunk hotFood = new Chunk(splitOrderContents(order.getHotFood()),defaultFont);
		hotFoodContainer.add(hotFood);
		para.add(hotFoodContainer);
		try {
			// Add paragraph to page and page break
			doc.add(para);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private static void addSushiPage(Document doc, Order order) {
		Paragraph para = generateCommonParagraph(order);
		
		Phrase sushiContainer = new Phrase ("SUSHI:\n",defaultFontBold);
		Chunk sushi = new Chunk(splitOrderContents(order.getSushi()),defaultFont);
		sushiContainer.add(sushi);
		para.add(sushiContainer);
		try {
			// Add paragraph to page and page break
			doc.add(para);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private static void addSnackPage(Document doc, Order order) {
		Paragraph para = generateCommonParagraph(order);
		
		Phrase snackContainer = new Phrase ("MORNING TEA SNACK:\n",defaultFontBold);
		Chunk snack = new Chunk(splitOrderContents(order.getSnack()),defaultFont);
		snackContainer.add(snack);
		para.add(snackContainer);
		try {
			// Add paragraph to page and page break
			doc.add(para);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private static void pageBreak(Document doc) {
		doc.newPage();
	}
	
	/**
	 * Format order string to have new line after it, as long as it is not an empty String
	 * @param
	 * @return Correctly formatted String
	 */
	private static String formatElement(String order) {
		if (order != null && order != "") {
			return ("\u2022 " + order + "\n");
		} else {
			return "";
		}
	}
	
	/**
	 * Splits order contents at every comma in order to format correctly for PDF
	 * @param
	 * @returns String split into new lines
	 */
	private static String splitOrderContents(String order) {
		String stringArray[] = order.split(", ");
		String returnableString = "";
		for (String splitOrder : stringArray) {
			returnableString += formatElement(splitOrder);
		}
		return returnableString;
	}
	
	public static void generatePDF(Orders orders) {
		try {
			Document document = new Document(pageSize);
			document.setMargins(marginSize,marginSize,marginSize,marginSize);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			PdfDestination dest = new PdfDestination(PdfDestination.FIT);
			writer.setOpenAction(PdfAction.gotoLocalPage(1, dest, writer));
			
			// Add content to PDF
			addMetaData(document);
			addOrders(document, orders);
			
			// Close and confirm
			document.close();
			
			// Open file automatically
			Desktop.getDesktop().open(new File(FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
