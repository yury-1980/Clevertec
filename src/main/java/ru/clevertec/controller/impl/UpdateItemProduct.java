package ru.clevertec.controller.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.clevertec.controller.Command;
import ru.clevertec.model.Product;
import ru.clevertec.orm.CrudDB;
import ru.clevertec.orm.ProductCrudDB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UpdateItemProduct implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final int CREATED = 201;
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);

        int id = Integer.parseInt(data.get("id").toString());
        String name = data.get("title").toString().replaceAll("\"", "");
        double price = Double.parseDouble(data.get("price").toString());
        boolean discount = Boolean.parseBoolean(data.get("discount").toString());

        CrudDB UpdateProduct = new ProductCrudDB();
        Product product = UpdateProduct.update(id, name, price, discount);

        List<Product> products = new ArrayList<>();

        products.add(product);// Gson с одним обьектом не хочет работать
        String json = new Gson().toJson(products);

        try (PrintWriter writer = resp.getWriter()) {
            writer.write(json);
            resp.setStatus(CREATED);
        }
    }
}
