package com.nms.app;

import java.io.IOException;

public class App 
{
    public static void main( String[] args )
    {
        Orders orders = new Orders();
        
        try {
			orders.fillOrders();
			//orders.displayOrders();
			PDFWriter.generatePDF(orders);
			System.out.println("Done!");
		} catch (OrdersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
