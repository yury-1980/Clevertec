package ru.clevertec.controller.impl;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import ru.clevertec.controller.Command;
import ru.clevertec.entity.Product;
import ru.clevertec.repository.ProductReository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@AllArgsConstructor
public class GetProductAll implements Command {

    private final int OK = 200;
    private ProductReository productReository;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Product> all = productReository.findAll();

        try (PrintWriter writer = resp.getWriter()) {
            for (Product product : all)
                writer.println(product);
            resp.setStatus(OK);
        }
    }
}
