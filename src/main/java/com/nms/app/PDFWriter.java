package com.nms.app;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.ListIterator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 * PDF Writer alternative using iText Will most likely implement instead of
 * PDFBox Sample code taken for learning purposes from
 * http://www.vogella.com/tutorials/JavaPDF/article.html
 * 
 * @author Tim Bishop
 *
 */
public class PDFWriter {
	private static String FILE = "./target/tuckshop.pdf";
	/* private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	*/
	private static Font defaultFont = new Font(FontFactory.getFont(FontFactory.HELVETICA, 15, BaseColor.BLACK));
	private static Font defaultFontBold = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, BaseColor.BLACK));
	private static Font defaultFontBoldItalic = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 15, BaseColor.BLACK));
	private static Font defaultFontItalic = new Font(FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 15, BaseColor.BLACK));
	private static int marginSize = 10;
	private static Rectangle pageSize = PageSize.A4;

	// Add meta data to the PDF document
	private static void addMetaData(Document document) {
		document.addTitle("NMS Tuckshop Labels");
		document.addSubject("Labels");
		document.addKeywords("Labels, Tuckshop, NMS");
	}

	public static void addTickets(Document ticketDocument, Orders orders){
		int tableColumns = 1;
		PdfPTable table = new PdfPTable(tableColumns);
		table.setWidthPercentage(100);
		
		for (int i = 0; i < orders.size(); i++) {
			Order thisOrder = orders.getOrder(i);
			PdfPCell cell = new PdfPCell();
			//cell.setMinimumHeight((float) 80.0);
			cell.setFixedHeight(pageSize.getHeight() - marginSize);
			cell.setBorderWidth(1);
			cell.setPadding(10);
			
			//The phrase is the main ticket container
			Phrase container = new Phrase("", defaultFont);

			//Paragraphs constitute the individual lines in the tickets
			Phrase orderID = 
					new Phrase ("ORDER: "+(int)thisOrder.getReference()+"\n");
			System.err.println(thisOrder.getReference());
			Phrase childName = 
					new Phrase ("NAME: "+thisOrder.getChildLastName()+", "+thisOrder.getChildFirstName()+"\n");
			Phrase childClass = 
					new Phrase ("CLASS: "+thisOrder.getChildClass()+"\n");
			Phrase childTeacher = 
					new Phrase ("TEACHER: "+thisOrder.getChildClass()+"\n");
			Phrase specialInstructions = 
					// new Phrase (checkSpecialRequirements(thisOrder.getSpecialIntructions()));
					new Phrase ();
			Phrase actualOrder =
					new Phrase (	"\n" + 
								splitOrderContents(thisOrder.getSandwhich()) +
								splitOrderContents(thisOrder.getDrinks()) +
								splitOrderContents(thisOrder.getFruit()) +
								splitOrderContents(thisOrder.getHotFood()) +
								splitOrderContents(thisOrder.getOther()) +
								splitOrderContents(thisOrder.getSushi()) +
								splitOrderContents(thisOrder.getSnack()));
			
			//Add all the chunks to the phrase
			container.add(orderID);
			container.add(childName);
			container.add(childClass);
			container.add(childTeacher);
			container.add(specialInstructions);
			container.add(actualOrder);
			
			//Add the phrase to the cell
			cell.setPhrase(container);
			cell.setNoWrap(false);
			
			//Add the cell to the table
			table.addCell(cell);
		}
		try {
			ticketDocument.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Paragraph generateCommonParagraph(Order order) {
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
		
		/* Child Teacher Section 
		Phrase childTeacherContainer = new Phrase ("TEACHER: ",defaultFont);
		Chunk childTeacher = new Chunk (order.getChildClass()+"\n",defaultFontBold);
		childTeacherContainer.add(childTeacher);
		para.add(childTeacherContainer);
		 * */
		
		if (order.hasSpecialInstructions()) {
			// Special Instruction Section
			Phrase specialInstructionsContainer = new Phrase ("\n SPECIAL_INSTRUCTIONS:\n",defaultFontBoldItalic);
			Chunk specialInstructions = new Chunk(order.getSpecialIntructions()+"\n",defaultFontItalic);
			specialInstructionsContainer.add(specialInstructions);
			para.add(specialInstructionsContainer);				
		}
		
		return para;
	}
	
	public static void addOrders(Document doc, Orders orders) {
		for (Order order : orders) {
			Paragraph para = generateCommonParagraph(order);
			
			if (order.getHotFood() != null && order.getHotFood() != "") {
				addHotFoodPage(doc, para, order);
			}
			if (order.getSushi() != null && order.getSushi() != "") {
				//addSushiPage(doc, para, order);
			}
			if (order.getSnack() != null && order.getSnack() != "") {
				//addSnackPage(doc, para, order);
			}
		}
	}
	
	public static void addHotFoodPage(Document doc, Paragraph para, Order order) {
		Phrase hotFoodContainer = new Phrase ("\n HOT FOOD:\n",defaultFontBoldItalic);
		Chunk hotFood = new Chunk(splitOrderContents(order.getHotFood()));
		hotFoodContainer.add(hotFood);
		para.add(hotFoodContainer);
		try {
			// Add paragraph to page and page break
			doc.add(para);
			doc.newPage();
			System.out.println("hotFOOD");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static void addSushiPage(Document doc, Paragraph para, Order order) {
		try {
			// Add paragraph to page and page break
			doc.add(para);
			doc.newPage();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static void addSnackPage(Document doc, Paragraph para, Order order) {		
		try {
			// Add paragraph to page and page break
			doc.add(para);
			doc.newPage();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Format order string to have new line after it, as long as it is not an empty String
	 * @param
	 * @return Correctly formatted String
	 */
	public static String formatElement(String order) {
		if (order != null && order != "") {
			return ("1 x " + order + "\n");
		} else {
			return "";
		}
	}
	
	/**
	 * Splits order contents at every comma in order to format correctly for PDF
	 * @param
	 * @returns String split into new lines
	 */
	public static String splitOrderContents(String order) {
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
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			
			// Add content to PDF
			addMetaData(document);
			// addTitlePage(document);
			//addTickets(document, orders);
			addOrders(document, orders);
			
			// Close and confirm
			document.close();
			System.err.println("DONE");
			
			// Open file automatically
			Desktop.getDesktop().open(new File(FILE));;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
