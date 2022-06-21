package ru.clevertec.service;

import org.junit.jupiter.api.*;
import ru.clevertec.fabric.Reader;
import ru.clevertec.model.Check;
import ru.clevertec.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckDiscontServiceTest {

    static Map<Integer, Product> products;
    static List<Product> masProducts;

    @BeforeAll
    static void createProducts() {
        products = Reader.getProduct();
    }

    @AfterAll
    static void deliteProducts() {
        products = null;
    }

    @BeforeEach
    void setMasProducts() {
      CheckDiscontService.checks = new ArrayList<>();
       CheckDiscontService.masProducts = new ArrayList<>();
       masProducts = CheckDiscontService.masProducts;

        String[] args = {"3-15.5", "200-500", "2-5", "2-5", "1-15", "11-15",
                "12-10", "card-6", "7-10"};
        ProposedPurchase.masProducts(args);
    }

    @AfterEach
    void deliteMasProduct() {
        CheckDiscontService.checks = null;
        CheckDiscontService.masProducts = null;
    }

    @Test
    @DisplayName("Тест на создание покупки")
    void getMasProductTest() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(2, "Herring", 7.30, false));
        products.get(products.size() - 1).setNumber(10);
        products.add(new Product(1, "Milk", 1.50, true));
        products.get(products.size() - 1).setNumber(15);
        products.add(new Product(11, "Mushrooms", 10.00, true));
        products.get(products.size() - 1).setNumber(15);
        products.add(new Product(12, "Грибы", 10.00, true));
        products.get(products.size() - 1).setNumber(10);

        Assertions.assertEquals(products.get(0).getTitle(),
                masProducts.get(0).getTitle());
    }

    @Test
    @DisplayName("Общая стоимость всего товара по скидке, добавление в чек")
    void setDiscountTotalTest() {
        String[] str = {"card"};

        CheckDiscontService service = new CheckDiscontService();
        service.setDiscountTotal(str);
        Assertions.assertEquals(245.25, Check.getDiscountTotal());
    }

    @Test
    @DisplayName("Общая стоимость всего нескидочного товара, добавление в чек")
    void setTotalCostOfAllNon_discountItemTest() {
        String[] str = {"card"};
        CheckDiscontService service = new CheckDiscontService();
        service.setTotalCostOfAllNon_discountItem(str);
        Assertions.assertEquals(73, Check.getTotalCostOfAllNon_discountItem());
    }

    @Test
    @DisplayName("Полная стоимость всего товара (без скидки)")
    void setSumTotalTest() {
        CheckDiscontService service = new CheckDiscontService();
        service.setSumTotal();
        Assertions.assertEquals(345.5, Check.getSumTotal());
    }

    @Test
    @DisplayName("Сумма всей скидки")
    void setDiscontSumTest() {
        CheckDiscontService service = new CheckDiscontService();
        service.setDiscontSum();
        Assertions.assertEquals(27.25, Check.getDiscontSum());
    }

    @Test
    @DisplayName("Окончательная сумма (с учётом скидок)")
    void setFinalAmountTest() {
        CheckDiscontService service = new CheckDiscontService();
        service.setFinalAmount();
        Assertions.assertEquals(318.25, Check.getFinalAmount());
    }
}
