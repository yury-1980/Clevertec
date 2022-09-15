package ru.clevertec.controller.impl;

import lombok.AllArgsConstructor;
import ru.clevertec.controller.Command;
import ru.clevertec.repository.DiscountRepoitory;
import ru.clevertec.repository.ProductReository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
public class DeleteIdProduct implements Command {

    private final int OK = 200;
    private ProductReository productReository;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String parameter = req.getParameter("id");
        int id = Integer.parseInt(parameter);

        if (productReository.existsById(id)) {
            productReository.deleteById(id);
            resp.setStatus(OK);
        } else {
            try (PrintWriter writer = resp.getWriter()) {
                writer.println("This ID does not exist!!!");
            }
        }
    }
}
