package ru.clevertec;

import ru.clevertec.serviceDB.ProcessingDB;
import ru.clevertec.serviceDB.ProductProcessingDB_Impl;
import ru.clevertec.fabric.Writer;
import ru.clevertec.service.CheckProductService;
import ru.clevertec.service.ProposedPurchase;
import ru.clevertec.service.proxy.CheckProductServiceProxy;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        ProductProcessingDB_Impl.connection();


        ProposedPurchase proposedPurchase = new ProposedPurchase();
        proposedPurchase.masProducts(args);// Массив предпологаемой покупки
        CheckProductService service = new CheckProductServiceProxy();
        service.service();// Подсчёт всех сумм
        Writer.checkWritingConsol();
        Writer.checkWritingFile();
        Writer.invalidDataWriting();
//        Email.sendingMail();

        ProcessingDB processingDB = new ProductProcessingDB_Impl();

//        System.out.println(processingDB.create(18, "Ananas.", 7.5, true));
//            processingDB.delete(15);
//        System.out.println(processingDB.update(17, "Banana...", 0.50, false));

        try {
            ProductProcessingDB_Impl.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
