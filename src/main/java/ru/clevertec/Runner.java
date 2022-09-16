package ru.clevertec;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.clevertec.configuration.SpringConfig;
import ru.clevertec.entity.Discount;
import ru.clevertec.entity.Product;
import ru.clevertec.repository.DiscountRepoitory;
import ru.clevertec.repository.ProductReository;

import java.util.List;
import java.util.Optional;

public class Runner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        DiscountRepoitory discountRepoitory =
                context.getBean(DiscountRepoitory.class);
        ProductReository productReository = context.getBean(ProductReository.class);
        /*Optional<Product> byId = product.findByIdProduct(110);
        System.out.println();
        System.out.println(byId);*/

//        discountRepoitory.deleteByProductId(63);
        productReository.deleteById(2);


       /* Product productEnt = Product.builder()
                .id_product(18).name("T").price(1.4)
//                .discount(Discount.builder().discount(true).product_id(18).build())
                .build();
product.save(productEnt);

        Discount discountEnt =
                Discount.builder().discount(true).product_id(18).build();
        discountRepoitory.save(discountEnt);*/

       /* List<Product> all = product.findAll();
        for (Product p : all)
        System.out.println(p);*/
    }
}
