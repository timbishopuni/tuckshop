# Instructions for use:

1. Login to the **Skoolbag Admin Console**.
2. Click on **eForms** in the menu on the left.
3. Click on **Forms**.
4. Find **Tuckshop Primary** in the list, and click on the green **submissions** button.
5. Click **export** in the top left.
6. In the **'form'** dropdown menu, select **Tuckshop Primary**, and select your start and end date.
7. Hit export and save the **.csv** file somewhere you can easily find it.
### STEP 8 IS A VERY IMPORTANT STEP
8. Open the **.csv** file you just saved in Microsoft Excel, and go to **File** -> **Save As**, and save the file as **tuckshop.xlsx**. Ensure that the file type is Excel Workbook (**.xlsx**).
9. Create a new folder on your desktop called **tuckshop**.
10. Move the **tuckshop.jar** and the new **tuckshop.xlsx** file into this folder.
11. While holding the **Shift** key on your keyboard, right click on the **tuckshop** folder you made and select **Open a command window here**.
12. Then type in **"java -jar tuckshop.jar"**.
    i. That's **java**, space, *dash* **jar**, space, **tuckshop**, period, **jar**.
13. Hit the **Enter**/**Return** key on the keyboard.
14. Select the appropriate option:
	i. **a** will generate a PDF will **all labels, combined**
	ii. **h** will generate a PDF for all orders containing **Hot Food**.
	iii. **s** will generate a PDF for all orders containing **Sushi**.
	iv. **m** will generate a PDF for all orders containing **Morning Tea Snacks**.
	v. **l** will generate a PDF for **all lunch orders**, *minus hot food, snacks and sushi*.
14. After it completes, it should produce a new PDF file called **tuckshop.pdf**. This file contains the labels that can now be printed for use.