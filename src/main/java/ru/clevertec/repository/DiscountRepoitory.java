package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.Discount;

import java.util.Optional;

@Repository
public interface DiscountRepoitory extends JpaRepository<Discount, Integer> {
    void deleteByProductId(Integer id);
    Discount findTopByOrderByIdDesc();

}
