package ru.clevertec.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.clevertec.controller.Command;
import ru.clevertec.entity.Product;
import ru.clevertec.repository.ProductReository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
public class GetProductQuantity implements Command {

    private final int OK = 200;
    private ProductReository productReository;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Integer page = Integer.valueOf(req.getParameter("page"));

        Pageable firstPage = PageRequest.of(page, 2);
        Page<Product> products = productReository.findAll(firstPage);

        try (PrintWriter writer = resp.getWriter()) {
            for (Product product : products)
                writer.println(product);
            resp.setStatus(OK);
        }
    }
}
