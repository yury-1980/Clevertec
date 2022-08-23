package ru.clevertec;

import ru.clevertec.dao.ProductConnectionDB;
import ru.clevertec.fabric.Writer;
import ru.clevertec.orm.CrudDB;
import ru.clevertec.orm.ProductCrudDB;
import ru.clevertec.service.CheckProductService;
import ru.clevertec.service.ProposedPurchase;
import ru.clevertec.service.proxy.CheckProductServiceProxy;

public class Main {

    public static void main(String[] args) {

        ProductConnectionDB productConnectionDB = ProductConnectionDB.getInstance();
        CrudDB crudDB = new ProductCrudDB();

        ProposedPurchase proposedPurchase = new ProposedPurchase();
        proposedPurchase.masProducts(args);// Массив предпологаемой покупки
        CheckProductService service = new CheckProductServiceProxy();

        service.service();// Подсчёт всех сумм
        Writer.checkWritingConsol();
        Writer.checkWritingFile();
        Writer.invalidDataWriting();
//        Email.sendingMail();


//        System.out.println(crudDB.create(15, "Ananas.", 7.5, true));
//           crudDB.delete(15);
//        System.out.println(crudDB.update(17, "Banana...", 0.50, false));

       productConnectionDB.closeConnection();
    }
}
