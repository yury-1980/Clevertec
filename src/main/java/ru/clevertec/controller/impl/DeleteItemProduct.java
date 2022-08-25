package ru.clevertec.controller.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.controller.Command;
import ru.clevertec.orm.CrudDB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class DeleteItemProduct implements Command {

    @Autowired
    CrudDB deleteProductId;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final int OK = 200;

        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        int id = Integer.parseInt(data.get("id").toString());

        deleteProductId.delete(id);

        try (PrintWriter writer = resp.getWriter()) {
//            writer.write(json);
            resp.setStatus(OK);
        }
    }
}
