package ru.clevertec.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.clevertec.entity.Discount;
import ru.clevertec.entity.Product;
import ru.clevertec.exception.ProductNotFoundException;
import ru.clevertec.repository.DiscountRepoitory;
import ru.clevertec.repository.ProductRepository;
import ru.clevertec.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    DiscountRepoitory discountRepoitory;
    ProductRepository productRepository;

    @Override
    public ResponseEntity<Optional<Product>> addItemProduct(HttpServletRequest request)
            {

        JsonObject data = null;

        try {
            data = new Gson().fromJson(request.getReader(), JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String name = data.get("title").toString().replaceAll("\"", "");
        double price = Double.parseDouble(data.get("price").toString());
        boolean discount = Boolean.parseBoolean(data.get("discount").toString());

                Discount discountEnt =
                        Discount.builder().price(price).discount(discount).build();
                discountRepoitory.save(discountEnt);

                Integer lastId = discountRepoitory.findTopByOrderByIdDesc().getId();

                Product productEnt = Product.builder().name(name).priceDiscountId(lastId)
                        .build();
                productRepository.save(productEnt);

                Optional<Product> lastProduct =
                        productRepository.findTopByOrderByIdDesc();

                return ResponseEntity.ok(lastProduct);
            }

    @Override
    public ResponseEntity<String> deleteIdProduct(String idProduct) {

        int id = Integer.parseInt(idProduct);

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else
            return ResponseEntity.ok("This ID does not exist!!!");

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Product>> getProductAll() {

        List<Product> all = productRepository.findAll();

        return ResponseEntity.ok(all);
    }

    @Override
    public ResponseEntity<Optional<Product>> getProductId(String id) {

        int idProduct = Integer.parseInt(id);

        if (productRepository.existsById(idProduct)) {
            Optional<Product> byId =
                    productRepository.findById(idProduct);
            return ResponseEntity.ok(byId);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Product>> getProductPage(String page) {

        Pageable firstPage = PageRequest.of(Integer.parseInt(page), 2);
        List<Product> products = productRepository.findAll(firstPage).toList();

        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<Optional<Product>> updateIdProduct(HttpServletRequest request) {

        JsonObject data = null;
        try {
            data = new Gson().fromJson(request.getReader(), JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int id = Integer.parseInt(data.get("id").toString());
        String name = data.get("title").toString().replaceAll("\"", "");
        double price = Double.parseDouble(data.get("price").toString());
        boolean discount = Boolean.parseBoolean(data.get("discount").toString());

        if (productRepository.existsById(id)) {
            Discount discountEnt =
                    Discount.builder().id(id).price(price).discount(discount).build();
            discountRepoitory.save(discountEnt);
            Integer lastId = discountRepoitory.findTopByOrderByIdDesc().getId();

            Product productEnt =
                    Product.builder().id(id).name(name).priceDiscountId(lastId)
                            .build();
            productRepository.save(productEnt);

            Optional<Product> lastProduct =
                    productRepository.findTopByOrderByIdDesc();

            return ResponseEntity.ok(lastProduct);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
