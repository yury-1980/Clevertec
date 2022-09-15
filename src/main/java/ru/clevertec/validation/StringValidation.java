package ru.clevertec.validation;

public class StringValidation {

    public static final String regId = "^([1-9]\\d?|100)$";
    public static final String regTitle = "^([A-Z][a-z]{2,29})|([А-Я][а-я]{2,"
            + "29})$";
    public static final String regPrice = "^([1-9]\\d?\\.\\d\\d|100\\.00)$";
    public static final String regDiscont = "^true|false$";

    // Колличество товара при покупке
    public static final String quantityOfGoods = "^([1-9]|1[0-9]|20)$";

}
