## Загрузчик сообещниий в rabbit

#### Запуск

java -jar generate-0.0.1.jar --Dspring.config.location=appliation.yml

Рядом с jar файлом лежит файл с конфигами application.yml и файл с идентификаторами ids

В application.yml можно задать другое имя и расположение файла 
по настройке filepath

#### Сборка утилиты

./gradlew build
./gradle.bat build

#### Содержимое application.yml


```yml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    password: guest
    username: guest

# адрес мастера
urlmaster: http://localhost:8080

# exchange, который связан с очредью для наливки
exchange: exchange.name
routingkey: command

# путь и имя файла
# в файле лежат список путей файлов filePathId в столбик без знаков препинания
# в данном случае это файл ids который будет лежать рядом с jar-файлом
filepath: ids
```

