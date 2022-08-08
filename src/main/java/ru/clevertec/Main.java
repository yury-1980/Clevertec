package ru.clevertec;

import ru.clevertec.controller.ConvertTxt_Pdf;
import ru.clevertec.controller.Convertible;
import ru.clevertec.dao.ConnectionDB;
import ru.clevertec.fabric.Writer;
import ru.clevertec.orm.CrudDB;
import ru.clevertec.orm.ProductCrudDB;
import ru.clevertec.service.CheckProductService;
import ru.clevertec.service.ProposedPurchase;
import ru.clevertec.service.proxy.CheckProductServiceProxy;

public class Main {

    public static void main(String[] args) {

        String[] strArgs = {"1-15", "11-15"};

        if (args.length == 0)
            args = strArgs;

        ConnectionDB productConnectionDB = ConnectionDB.getInstance();
        CrudDB crudDB = new ProductCrudDB();

        ProposedPurchase proposedPurchase = new ProposedPurchase();
        proposedPurchase.masProducts(args);// Массив предпологаемой покупки
        CheckProductService service = new CheckProductServiceProxy();
        service.service();// Подсчёт всех сумм
        Writer.checkWritingConsol();
        Writer.checkWritingFile();
        Writer.invalidDataWriting();
//        Email.sendingMail();
        Convertible convert = new ConvertTxt_Pdf();
        convert.getCheckPDF();


//        System.out.println(crudDB.create(18, "Ananas..", 7.5, true));
//        crudDB.delete(18);
//        System.out.println(crudDB.update(17, "Banana...", 0.50, false));

        productConnectionDB.closeConnection();
    }
}
