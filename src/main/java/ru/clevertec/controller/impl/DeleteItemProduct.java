package ru.clevertec.controller.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.clevertec.controller.Command;
import ru.clevertec.orm.CrudDB;
import ru.clevertec.orm.ProductCrudDB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteItemProduct implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        int id = Integer.parseInt(data.get("id").toString());

        CrudDB deleteProductId = new ProductCrudDB();
        deleteProductId.delete(id);

        try (PrintWriter writer = resp.getWriter()) {
//            writer.write(json);
            resp.setStatus(200);
        }
    }
}
