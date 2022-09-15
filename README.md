# MyRepository

## Описание.
### Черновик.
Проект работает через Postman.
Запросы:
http://localhost:8080/controller?command_name=find_all - вывод всех товаров
http://localhost:8080/controller?command_name=find_quantity&page=1 - вывод заданной
страницы, по 2 товара.
http://localhost:8080/controller?command_name=find_id&id=5 - вывод заданного товара по id
http://localhost:8080/controller?command_name=update_item_product - обновление товара
через тело запроса. Пример:
{
        "title": "Mil",
        "price": 1.5,
        "discount": true
    }
http://localhost:8080/controller?command_name=delete_product&id=21 - удаление товара.
http://localhost:8080/controller?command_name=addItem_product - добавление товара
через тело запроса. Пример:
{
        "title": "Milkvo",
        "price": 1.5,
        "discount": true
    }