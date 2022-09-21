# MyRepository

## Описание.
### Черновик.
Проект работает через Postman.
Запросы:
http://localhost:8080/product/find_all - вывод всех товаров
http://localhost:8080/product/page?page=0 - вывод заданной
страницы, по 2 товара.
http://localhost:8080/product/id&id=5 - вывод заданного товара по id
http://localhost:8080/product/update_product - обновление товара
через тело запроса. Пример:
{
        "title": "Mil",
        "price": 1.5,
        "discount": true
    }
http://localhost:8080/product/del?id=16 - удаление товара.
http://localhost:8080/product/add - добавление товара
через тело запроса. Пример:
{
        "title": "Milkvo",
        "price": 1.5,
        "discount": true
    }