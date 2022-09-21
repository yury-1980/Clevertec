/*
package ru.clevertec.controller.impl;

import com.google.gson.Gson;
import ru.clevertec.Main;
import ru.clevertec.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@Component
public class Purchase implements Command {

    private ConnectionDB connectionDB = ConnectionDB.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final int OK = 200;
        String[] products = req.getParameterValues("p");

        for (String str : products)
            System.out.println(str);

        Main.main(products);

        String s = new Gson().toJson(products);

        PrintWriter writer = resp.getWriter();
        writer.write(s);
        resp.setStatus(OK);
        writer.close();
    }
}
*/
