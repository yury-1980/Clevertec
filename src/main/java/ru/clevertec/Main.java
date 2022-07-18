package ru.clevertec;

import ru.clevertec.fabric.Writer;
import ru.clevertec.service.CheckProductService;
import ru.clevertec.service.ProposedPurchase;
import ru.clevertec.service.proxy.CheckProductServiceProxy;

public class Main {

    public static void main(String[] args) {

        ProposedPurchase.masProducts(args);// Массив предпологаемой покупки
        CheckProductService service = new CheckProductServiceProxy();
//        service.setDiscountTotal(args);
        service.service();// Подсчёт всех сумм
        Writer.checkWritingConsol();
        Writer.checkWritingFile();
        Writer.invalidDataWriting();
//        Email.sendingMail();
    }
}
