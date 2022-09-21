package ru.clevertec.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.entity.Product;
import ru.clevertec.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<Optional<Product>> addItemProduct(HttpServletRequest request) {

        return productService.addItemProduct(request);
    }

    @Transactional
    @DeleteMapping("/del")
    public ResponseEntity<String> deleteIdProduct(@RequestParam("id") String idProduct) {

        return productService.deleteIdProduct(idProduct);
    }

    @GetMapping("/find_all")
    public ResponseEntity<List<Product>> getProductAll() {
        return productService.getProductAll();
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Product>> getProductId(@RequestParam("id") String id) {

        return productService.getProductId(id);

    }

    @GetMapping("/page")
    public ResponseEntity<List<Product>> getProductPage(@RequestParam("page")
                                                                String page) {

        return productService.getProductPage(page);
    }

    @Transactional
    @PutMapping("/update_product")
    public ResponseEntity<Optional<Product>> updateIdProduct(HttpServletRequest request) {

        return productService.updateIdProduct(request);
    }

}
