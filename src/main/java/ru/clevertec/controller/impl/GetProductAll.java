package ru.clevertec.controller.impl;

import com.google.gson.Gson;
import ru.clevertec.controller.Command;
import ru.clevertec.model.Product;
import ru.clevertec.orm.CrudDB;
import ru.clevertec.orm.ProductCrudDB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class GetProductAll implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final int OK = 200;

        CrudDB crudDB = new ProductCrudDB();
        Map<Integer, Product> products = crudDB.readAllDB();
        String json = new Gson().toJson(products);
        PrintWriter writer = resp.getWriter();
        writer.write(json);
        resp.setStatus(OK);
        writer.close();
    }
}
