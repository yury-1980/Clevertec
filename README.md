# MyRepository

## Описание.
### Черновик.
Проект работает через Postman.
Запросы:
http://localhost:8080/controller?command_name=purchase&p=1-5&p=2-7&p=3-5 - подсчёт чека.
http://localhost:8080/controller?command_name=find_quantity&size=1 - вывод заданного
количества товара.
http://localhost:8080/controller?command_name=update_item_product - обновление товара
через тело запроса. Пример:
{
        "id": 21,
        "title": "Mil",
        "price": 1.5,
        "discount": true,
        "number": 0
    }
http://localhost:8080/controller?command_name=delete_product&id=21 - удаление товара.
http://localhost:8080/controller?command_name=addItem_product - добавление товара
через тело запроса. Пример:
{
        "id": 21,
        "title": "Milkvo",
        "price": 1.5,
        "discount": true,
        "number": 0
    }