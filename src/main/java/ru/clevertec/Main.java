package ru.clevertec;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.clevertec.controller.Convertible;
import ru.clevertec.fabric.Writer;
import ru.clevertec.orm.CrudDB;
import ru.clevertec.service.CheckProductService;
import ru.clevertec.service.ProposedPurchase;
import ru.clevertec.service.proxy.CheckProductServiceProxy;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class);

        CrudDB crudDB = context.getBean("productCrudDB", CrudDB.class);

        ProposedPurchase proposedPurchase = context.getBean("proposedPurchase"
                , ProposedPurchase.class);

        proposedPurchase.masProducts(args);// Массив предпологаемой покупки

        CheckProductService service = new CheckProductServiceProxy();
        service.service();// Подсчёт всех сумм
        Writer.checkWritingConsol();
        Writer.checkWritingFile();
        Writer.invalidDataWriting();
//        Email.sendingMail();
        Convertible convert = context.getBean("convertTxt_Pdf", Convertible.class);
        convert.getCheckPDF();

        /*for (Map.Entry<Integer, Product> map : crudDB.readAllDB().entrySet()) {
            System.out.println(map);
        }*/
//        System.out.println(crudDB.create(18, "Ananas..", 7.5, true));
//        crudDB.delete(18);
//        System.out.println(crudDB.update(17, "Banana...", 0.50, false));

//        ConnectionDB productConnectionDB = ConnectionDB.getInstance();
//        productConnectionDB.closeConnection();
        context.close();
    }
}
