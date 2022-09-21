/*
package ru.clevertec;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.clevertec.configuration.SpringConfig;
import ru.clevertec.service.Convertible;
import ru.clevertec.fabric.Writer;
import ru.clevertec.service.CheckProductService;
import ru.clevertec.service.ProposedPurchase;
import ru.clevertec.service.proxy.CheckProductServiceProxy;

public class Main {

   */
/* @Autowired
    private static ProposedPurchase proposedPurchase;*//*


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
        Convertible convert = context.getBean("convertTxtPdf", Convertible.class);
        convert.getCheckPDF();

        context.close();
    }
}
*/
