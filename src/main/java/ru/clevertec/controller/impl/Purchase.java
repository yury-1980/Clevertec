package ru.clevertec.controller.impl;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import ru.clevertec.Main;
import ru.clevertec.controller.Command;
import ru.clevertec.dao.ConnectionDB;
import ru.clevertec.orm.CrudDB;
import ru.clevertec.orm.ProductCrudDB;
import ru.clevertec.service.ProposedPurchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class Purchase implements Command {

    private ConnectionDB connectionDB = ConnectionDB.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final int OK = 200;
        CrudDB crudDB = new ProductCrudDB();
        String[] products = req.getParameterValues("p");

        for (String str : products)
            System.out.println(str);

//        ProposedPurchase proposedPurchase = new ProposedPurchase();
//        proposedPurchase.masProducts(products);

        Main.main(products);

        String s = new Gson().toJson(products);

        PrintWriter writer = resp.getWriter();
        writer.write(s);
        resp.setStatus(OK);
        writer.close();
    }
}
