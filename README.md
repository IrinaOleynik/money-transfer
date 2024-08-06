# Курсовой проект «Сервис перевода денег»

## Описание
Проект предоставляет собой REST-сервис для интеграции с FRONT для перевода денег с одной банковской карты на другую. 
Описание веб приложения (FRONT) доступно [по адресу](https://github.com/serp-ya/card-transfer). 
Сервис может взаимодействовать как и с локально запущенным FRONT приложением, так и с демо версией, расположенной [по адресу](https://serp-ya.github.io/card-transfer/).

Приложение запускиется на порту 5500 и реализует [протокол](https://github.com/netology-code/jd-homeworks/blob/master/diploma/MoneyTransferServiceSpecification.yaml) (за исключением номера порта).
## Запуск приложения
Для запуска приложения используются команды Docker
1. Скачать или склонировать репозиторий приложения в программу IntelliJ IDEA
2. Упаковать проект в Maven
3. В терминале приложения командой `docker build -t app:latest .` создать образ приложения
4. Упаковать образ в контейнер командой `docker-compose build`
5. Запустить контейнер командой `docker-compose up`
6. Для остановки приложения нажать Ctrl+C
## Пример использования
В качестве данных для тестирования, репозиторий содержит две банковские карты
| Номер карты | Дата действия | Код CVV | Баланс карты |Валюта |
|---------------------|-------|-----|--------|-----|
| 1111 1111 1111 1111 | 11/25 | 111 | 100.00 | RUR |
| 2222 2222 2222 2222 |	12/25 |	222 | 100.00 | RUR |

Чтобы оперцая прошла успешно, на полях в веб приложении необходимо ввести данные карт, указанные выше, a также сумму перевода (не более 100). Далее вы увидите сообщение об успешной операции.
В случае, если данные указаны не верно, или произошла ошибка во время выполнения операции, вы увидите сообщение об ошибке с описанием причины ошибки.