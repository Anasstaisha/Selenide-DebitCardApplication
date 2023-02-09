[![Build status](https://ci.appveyor.com/api/projects/status/n37y0kkpkn6irr0d?svg=true)](https://ci.appveyor.com/project/Anasstaisha/selenide-uitesting)


# Задача №1: заказ карты

Необходимо автоматизировать тестирование формы заказа карты:

![image](https://github.com/netology-code/aqa-homeworks/raw/master/web/pic/order.png)

Требования к содержимому полей:

1. В поле фамилии и имени разрешены только русские буквы, дефисы и пробелы.
2. В поле телефона — только 11 цифр, символ + на первом месте.
3. Флажок согласия должен быть выставлен.

Тестируемая функциональность: отправка формы.

Условия: если все поля заполнены корректно, то вы получаете сообщение об успешно отправленной заявке:

![image](https://github.com/netology-code/aqa-homeworks/raw/master/web/pic/success.jpg)

Проект с автотестами должен быть выполнен на базе Gradle с использованием Selenide или Selenium по выбору студента.


