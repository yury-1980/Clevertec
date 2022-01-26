import fabric.Email;
import fabric.Writer;
import service.CheckDiscont;

public class Main {

    public static void main(String[] args)  {

        CheckDiscont.masProducts(args);
        Writer.checkWritingConsol();
        Writer.checkWritingFile();
        Email.sendingMail();
    }
}
