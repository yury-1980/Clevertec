package ru.clevertec.controller.impl;

import lombok.AllArgsConstructor;
import ru.clevertec.controller.Command;
import ru.clevertec.entity.Product;
import ru.clevertec.repository.ProductReository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@AllArgsConstructor
public class GetProductId implements Command {

    private final int OK = 200;
    private ProductReository productReository;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Optional<Product> byId = productReository.findById(Integer.valueOf(req.getParameter(
                "id")));
        try (PrintWriter writer = resp.getWriter()) {
            writer.println(byId);
            resp.setStatus(OK);
        }
    }
}
