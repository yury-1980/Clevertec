package ru.clevertec;

import ru.clevertec.fabric.Writer;
import ru.clevertec.service.CheckDiscont;

public class Main {

    public static void main(String[] args)  {

        CheckDiscont.masProducts(args);
        Writer.checkWritingConsol();
        Writer.checkWritingFile();
        Writer.invalidDataWriting();
//        Email.sendingMail();
    }
}
