package ru.clevertec;

import ru.clevertec.fabric.Writer;
import ru.clevertec.service.ProductService;
import ru.clevertec.service.ProposedPurchase;
import ru.clevertec.service.proxy.CheckDiscontServiceProxy;

public class Main {

    public static void main(String[] args) {

        ProposedPurchase.masProducts(args);// Массив предпологаемой покупки
        ProductService service = new CheckDiscontServiceProxy();
//        service.setDiscountTotal(args);
        service.service();// Подсчёт всех сумм
        Writer.checkWritingConsol();
        Writer.checkWritingFile();
        Writer.invalidDataWriting();
//        Email.sendingMail();
    }
}
