package ru.clevertec.fabric;

import ru.clevertec.model.Check;
import ru.clevertec.service.CheckDiscont;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class Writer {

    private static List<Check> checks = CheckDiscont.checks;
    private static List<String> listInvalidData = Reader.listInvalidData;

    public static void invalidDataWriting() {
        FileWriter writeToFile = null;
        try {
            String date = String.valueOf(new Date());
            writeToFile = new FileWriter("src/main/resources/invalidData.txt", false);
            writeToFile.write(date + "\n");
            for (String s : listInvalidData)
                writeToFile.write(s + " ");
            writeToFile.write("\n");
            writeToFile.flush();
            writeToFile.close();
        } catch (IOException e) {
            System.out.println("File not written!!!");
        }
    }

    public static void checkWritingFile() {
        FileWriter writeToFile = null;

        try {
            writeToFile = new FileWriter("src/main/resources/salesReceipt.txt", false);
        } catch (IOException e) {
            System.out.println("File not written!!!");
        }
        PrintWriter print = new PrintWriter(writeToFile);

        print.println("=================SALES RECEIPT=================");
        print.printf("%s%15s%14s%10s\n", "QTY", "DESCRIPTION", "PRICE", "TOTAL");
        print.println("_______________________________________________");

        for (Check c : checks) {
            print.printf("%03d  | %-15s  | %6.2f | %7.2f\n", c.getNumber(),
                    c.getTitle(), c.getPrice(), c.getTotalSumOfOneItem());
        }
        print.printf("===============================================\n");
        print.printf("%s%9.2f\n", "Total amount without discount", Check.getSumTotal());
        print.printf("%s%32.2f\n", "Discont", Check.getDiscontSum());
        print.printf("%s%40.2f\n", "TOTAL", Check.getFinalAmount());

        print.close();
        System.out.println();
        System.out.println("the file is located: " + "src/main/resources/salesReceipt.txt");
    }

    public static void checkWritingConsol() {

        System.out.println("=================SALES RECEIPT=================");
        System.out.printf("%s%15s%14s%10s\n", "QTY", "DESCRIPTION", "PRICE", "TOTAL");
        System.out.println("_______________________________________________");

        for (Check c : checks) {
            System.out.printf("%03d  | %-15s  | %6.2f | %7.2f\n", c.getNumber(),
                    c.getTitle(), c.getPrice(), c.getTotalSumOfOneItem());
        }
        System.out.printf("===============================================\n");
        System.out.printf("%s%9.2f\n", "Total amount without discount", Check.getSumTotal());
        System.out.printf("%s%32.2f\n", "Discont", Check.getDiscontSum());
        System.out.printf("%s%40.2f\n", "TOTAL", Check.getFinalAmount());
    }
}
