package ru.clevertec.controller;

import com.aspose.words.Document;

public class ConvertTxt_Pdf implements Convertible {

    @Override
    public void getCheckPDF() {
        final String FILE_CHECK_TXT = "src/main/resources/salesReceipt.txt";
        final String FILE_CHECK_PDF = "src/main/resources/checkOutput.pdf";
        Document doc = null;

        try {
            doc = new Document(FILE_CHECK_TXT);
            doc.save(FILE_CHECK_PDF);
        } catch (Exception e) {
            System.out.println("Conversion failed!!!");
//            e.printStackTrace();
        }
    }
}
