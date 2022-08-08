package ru.clevertec.controller;

import com.aspose.words.Document;

public class ConvertTxt_Pdf implements Convertible {

    @Override
   public void getCheckPDF() {
        Document doc = null;

        try {
            doc = new Document("src/main/resources/salesReceipt.txt");
            doc.save("src/main/resources/checkOutput.pdf");
        } catch (Exception e) {
            System.out.println("Conversion failed!!!");
//            e.printStackTrace();
        }
    }


}
