package ru.clevertec.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import ru.clevertec.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    ResponseEntity<Optional<Product>> addItemProduct(HttpServletRequest request);

    ResponseEntity<String> deleteIdProduct(@RequestParam("id") String idProduct);

    ResponseEntity<List<Product>> getProductAll();

    ResponseEntity<Optional<Product>> getProductId(@RequestParam("id") String id);

    ResponseEntity<List<Product>> getProductPage(@RequestParam("page") String page);

    ResponseEntity<Optional<Product>> updateIdProduct(HttpServletRequest request);
}
