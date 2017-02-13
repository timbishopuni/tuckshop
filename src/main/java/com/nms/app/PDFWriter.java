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
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	// Add meta data to the PDF document
	private static void addMetaData(Document document) {
		document.addTitle("NMS Tuckshop Labels");
		document.addSubject("Labels");
		document.addKeywords("Labels, Tuckshop, NMS");
	}

	public static void addTickets(Document ticketDocument, Orders orders){
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(new Float(100.00));
		
		for (int i = 0; i < orders.size(); i++) {
			Order thisOrder = orders.getOrder(i);
			PdfPCell cell = new PdfPCell();
			cell.setMinimumHeight((float) 25.0);
			//cell.setFixedHeight((float)25.0);
			
			//The phrase is the main ticket container
			Phrase allTicket = new Phrase("", FontFactory.getFont(FontFactory.HELVETICA, (float)6, new BaseColor(0, 0, 0)));

			//Chunks constitute the individual lines in the tickets
			Chunk orderID = 
					new Chunk ("ORDER: "+(int)thisOrder.getReference()+"\n");
			Chunk childName = 
					new Chunk ("NAME: "+thisOrder.getChildLastName()+", "+thisOrder.getChildFirstName()+"\n");
			Chunk childClass = 
					new Chunk ("CLASS: "+thisOrder.getChildClass()+"\n");
			Chunk childTeacher = 
					new Chunk ("TEACHER: "+thisOrder.getChildClass()+"\n");
			Chunk specialInstructions = 
					new Chunk (checkSpecialRequirements(thisOrder.getSpecialIntructions()));
			Chunk actualOrder =
					new Chunk (	"\n" + 
								formatElement(thisOrder.getSandwhich()) +
								formatElement(thisOrder.getDrinks()) +
								formatElement(thisOrder.getFruit()) +
								formatElement(thisOrder.getHotFood()) +
								formatElement(thisOrder.getOther()) +
								formatElement(thisOrder.getSushi()) +
								formatElement(thisOrder.getSnack()));
			
			//Add all the chunks to the phrase
			allTicket.add(orderID);
			allTicket.add(childName);
			allTicket.add(childClass);
			allTicket.add(childTeacher);
			allTicket.add(specialInstructions);
			allTicket.add(actualOrder);
			
			//Add the phrase to the cell
			cell.setPhrase(allTicket);
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
	
	public static String formatElement(String order) {
		if (order != null && order != "") {
			return (order + "\n");
		} else {
			return "";
		}
	}
	
	public static String checkSpecialRequirements(String special) {
		if (special != null && special != "") {
			String returnableString = "SPECIAL_INSTRUCTIONS:\n";
			return returnableString+= special += "\n";
		} else {
			return "";
		}
	}

	public static void createTable(Section subCatPart) throws BadElementException {
		PdfPTable table = new PdfPTable(3);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

//		PdfPCell c1 = new PdfPCell(new Phrase("Tickets"));
//		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table.addCell(c1);
//
//		c1 = new PdfPCell(new Phrase("Table Header 2"));
//		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table.addCell(c1);
//
//		c1 = new PdfPCell(new Phrase("Table Header 3"));
//		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table.addCell(c1);
//		table.setHeaderRows(1);

		table.addCell("1.0                       someting                      ++something");
		table.addCell("1.1");
		table.addCell("1.2");
		table.addCell("1.3");
		table.addCell("2.2");
		table.addCell("2.3");

		subCatPart.add(table);

	}

	private static void createList(Section subCatPart) {
		List list = new List(true, false, 10);
		list.add(new ListItem("First point"));
		list.add(new ListItem("Second point"));
		list.add(new ListItem("Third point"));
		subCatPart.add(list);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	public static void generatePDF(Orders orders) {
		try {
			int marginSize = 50;
			Document document = new Document(PageSize.A5.rotate());
			document.setMargins(marginSize,marginSize,marginSize,marginSize);
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			
			/* Add content to PDF */
			addMetaData(document);
			// addTitlePage(document);
			addTickets(document, orders);
			
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
