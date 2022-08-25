package ru.clevertec;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.clevertec.controller.ConvertTxt_Pdf;
import ru.clevertec.controller.Convertible;
import ru.clevertec.dao.ConnectionDB;
import ru.clevertec.fabric.Writer;
import ru.clevertec.model.Product;
import ru.clevertec.orm.CrudDB;
import ru.clevertec.orm.ProductCrudDB;
import ru.clevertec.service.CheckProductService;
import ru.clevertec.service.ProposedPurchase;
import ru.clevertec.service.proxy.CheckProductServiceProxy;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class);

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

//        ConnectionDB productConnectionDB = ConnectionDB.getInstance();
//        productConnectionDB.closeConnection();
        context.close();
    }
}
