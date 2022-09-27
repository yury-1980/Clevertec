package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    void deleteByProductId(Integer id);
    Discount findTopByOrderByIdDesc();

}
