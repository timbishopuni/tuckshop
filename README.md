# tuckshop
Order processing system

COMPLETE - 
-------------------------------------------------------------------------
	-READ AND STORE-
All read and store functionality is complete --  but untested after latest changes.
I made some changes to the tuckshop order form, it now should use the template "tuckshop primary" located in skoolbag e-forms. 
---------------------------------------------------------------------------------------------
INCOMPLETE 
-------------------------------------------------------------------------------
	-PDF OUTPUT - 
	-TICKETS
A scaffold exists for output to pdf in table form. Work will have to be done to standardize the table spacings and output of the actual order data. For the PDF tickets all of the data you need to output is contained in the orders file (there is an array of Order in there). Conform to the supplied tickets example - make it exactly the same.
	-SUMMARY PAGES-
3 separate summary pages relating to order data must be completed. The logic for these functions should be placed in MenuItems (some work is done). Create as new class which outputs to a PDF file. Refer to existing PDF writer class and supplied documentation.
--------------------------------------------------------------------------------------------------


	-PROCESS - 
Process wise, discussions will have to be had regarding using one or multiple forms. As shown in the old tuckshop user documentation there were 2 separate order forms. The system may be able to accomadate these 2 forms as is - as the only difference is the absence of the "morning tea" order category on the pre-primary form. 

	-TESTING - 
Thourough manual testing MUST take place well before launch date - otherwise you will have a disaster on your hands.
My advice is conduct some small scale testing in office - getting staff to fill out little orders and process actual payments - then ensure all order data is captured and also ensure the money is flowing through to the school paypal account.Ensure that you can match payments to orders somehow.

