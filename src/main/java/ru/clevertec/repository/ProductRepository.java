package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.Discount;
import ru.clevertec.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String name);
    Optional<Product> findTopByOrderByIdDesc();

   /* @Modifying
    @Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
    void setUserInfoById(String firstname, String lastname, Integer userId);*/
//    Boolean deleteById(Integer id);
//    Optional<Product> findByIdProduct(Integer id_product);
//    void deleteByIdProduct(Integer id);
}
