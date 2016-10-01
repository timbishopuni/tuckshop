package com.nms.app;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

/**
 * This is an early attempt to output the order data into pdf format
 * Perhaps revisit later but so far seems very clunky
 * Example taken straight from https://svn.apache.org/viewvc/pdfbox/trunk/example
 * 
 */
public class PDFWriterPDFBox {
	PDDocument document;
	PDAcroForm acroForm;
	String defaultAppearanceString;
	
	public PDFWriterPDFBox() {
		this.document = new PDDocument();
		acroForm = new PDAcroForm(document);
		
	}

	public void intialSetup() {
		// Adobe Acrobat uses Helvetica as a default font and
		// stores that under the name '/Helv' in the resources dictionary
		PDFont font = PDType1Font.HELVETICA;
		PDResources resources = new PDResources();
		resources.put(COSName.getPDFName("Helv"), font);
		addPage();
		document.getDocumentCatalog().setAcroForm(acroForm);
		
		// Add and set the resources and default appearance at the form level
		acroForm.setDefaultResources(resources);
		
		// Acrobat sets the font size on the form level to be
		// auto sized as default. This is done by setting the font size to '0'
		String defaultAppearanceString = "/Helv 0 Tf 0 g";
		acroForm.setDefaultAppearance(defaultAppearanceString);
		
	}
	public void addPage(){
		PDPage page = new PDPage(PDRectangle.A4);
		document.addPage(page);
	}
	public void addTextBox(String heading, String color){
		// Add a form field to the form.
		PDTextField textBox = new PDTextField(acroForm);
		textBox.setPartialName("TuckShop Labels");
		
		defaultAppearanceString = "/Helv 12 Tf 0 0 0 rg";
		textBox.setDefaultAppearance(defaultAppearanceString);
		
		// add the field to the acroform
		acroForm.getFields().add(textBox);
		
	}
	public void addContent(Orders orders){
		
	}

	public static void main(String[] args) throws IOException {
		

//		// add the field to the acroform
//		acroForm.getFields().add(textBox);
//
//		// Specify the annotation associated with the field
//		PDAnnotationWidget widget = textBox.getWidgets().get(0);
//		PDRectangle rect = new PDRectangle(50, 750, 200, 50);
//		widget.setRectangle(rect);
//		widget.setPage(page);
//
//
//		// make sure the annotation is visible on screen and paper
//		widget.setPrinted(true);
//
//		// Add the annotation to the page
//		page.getAnnotations().add(widget);
//		// set the field value
//		textBox.setValue("Sample field");
//		document.save("target/SimpleForm.pdf");
	}

}
