package ru.clevertec.service;

import ru.clevertec.model.Check;

import java.util.List;

public interface CheckProductService {
    List<Check> service();

    // Общая стоимость всего товара по скидке, добавление в чек
    void setDiscountTotal(String[] str);

    // Общая стоимость всего нескидочного товара, добавление в чек
    void setTotalCostOfAllNon_discountItem(String[] str);

    // Полная стоимость всего товара (без скидки)
    void setSumTotal();

    // Сумма всей скидки
    void setDiscontSum();

    // Окончательная сумма (с учётом скидок)
    void setFinalAmount();
}
