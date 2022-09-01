package ru.clevertec.controller.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.controller.Command;
import ru.clevertec.model.Product;
import ru.clevertec.dao.CrudDB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class GetProductQuantity implements Command {

    private CrudDB crudDB;

    @Autowired
    public GetProductQuantity(CrudDB crudDB) {
        this.crudDB = crudDB;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int page_size = Integer.parseInt(req.getParameter("size"));
        final int OK = 200;

        Map<Integer, Product> products = crudDB.readAllDB(page_size);
        String json = new Gson().toJson(products);
        PrintWriter writer = resp.getWriter();
        writer.write(json);
        resp.setStatus(OK);
        writer.close();
    }
}
