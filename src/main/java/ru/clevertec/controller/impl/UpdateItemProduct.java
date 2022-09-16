package ru.clevertec.controller.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import ru.clevertec.controller.Command;
import ru.clevertec.entity.Discount;
import ru.clevertec.entity.Product;
import ru.clevertec.repository.DiscountRepoitory;
import ru.clevertec.repository.ProductReository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Transactional
@AllArgsConstructor
public class UpdateItemProduct implements Command {

    private final int CREATED = 201;
    private ProductReository productReository;
    private DiscountRepoitory discountRepoitory;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        int id = Integer.parseInt(data.get("id").toString());
        String name = data.get("title").toString().replaceAll("\"", "");
        double price = Double.parseDouble(data.get("price").toString());
        boolean discount = Boolean.parseBoolean(data.get("discount").toString());

        if (productReository.existsById(id)) {
            Discount discountEnt =
                    Discount.builder().id(id).price(price).discount(discount).build();
            discountRepoitory.save(discountEnt);
            Integer lastId = discountRepoitory.findTopByOrderByIdDesc().getId();

            Product productEnt =
                    Product.builder().id(id).name(name).priceDiscountId(lastId)
                            .build();
            productReository.save(productEnt);

            Optional<Product> updateProduct =
                    productReository.findTopByOrderByIdDesc();

            try (PrintWriter writer = resp.getWriter()) {
                writer.println(updateProduct);
                resp.setStatus(CREATED);
            }
        } else {
            try (PrintWriter writer = resp.getWriter()) {
                writer.println("This ID does not exist!!!");
            }
        }
    }
}
