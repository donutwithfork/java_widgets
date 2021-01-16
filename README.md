# Miro Widget test task

# Что это

Небольшой проект для знакомства с Java, Spring boot, Maven

## Для установки нужно:

Следовать инструкциям
https://spring.io/quickstart


## Установка зависимостей и запуск:

В директории проекта вызвать команду:

Mac\linux (win powershell)
```sh
./mvnw spring-boot:run
```


## Чего сделал:

Основные требования вроде есть, виджеты создаются, по zIndex сортируются, вправо смещаются

Проверил работоспособность на win и mac (если при проверке не заведется, то проблема на вашей стороне :D )

Доп задания: 

- лимиты  ->  неа
- фильтрация  ->  неа
- новое хранилище ->  ща посмотрим, звучит интересно


## Как проверить

Примеры запросов сделал в Insomnia формате (точно работает) и в open-api (не проверял)

Файлы в корне проекта, doc_insomnia.yaml и doc_openapi.yaml

Красочно расписывать доку не буду, но приведу примеры всех запросов


### Запросики

#### Создание виджета

POST /widget

Request Body:

```javascript
{
	"xCoord": 1,
	"yCoord": 1,
	"width": 4,
	"heigth": 4,
	"zIndex": 1
}
```

Response Body:

```javascript
{
  "id": 0,
  "xCoord": 1,
  "yCoord": 1,
  "width": 4,
  "heigth": 4,
  "zIndex": 1
}
```



### Изменение виджета

PUT /widget/{id}

Request Body:

```javascript
{
	"xCoord": 1,
	"yCoord": 1,
	"width": 6,
	"heigth": 6,
	"zIndex": 1
}
```

Response Body:

```javascript
{
  "id": 0,
  "xCoord": 1,
  "yCoord": 1,
  "width": 6,
  "heigth": 6,
  "zIndex": 1
}
```




### Получение списка виджетов

GET /widget

Response Body:

```javascript
[
    {
        "id": 0,
    	"xCoord": 1,
    	"yCoord": 1,
    	"width": 4,
    	"heigth": 4,
    	"zIndex": 1
    },
    {
        "id": 1,
    	"xCoord": 2,
    	"yCoord": 2,
    	"width": 1,
    	"heigth": 1,
    	"zIndex": 2
    },
]
```



#### Получение виджета по его ID

GET /widget/{id}

Response Body:

```javascript
{
    "id": 0,
	"xCoord": 1,
	"yCoord": 1,
	"width": 4,
	"heigth": 4,
	"zIndex": 1
}
```



## Code coverage
Тут я не догнал как лучше сделать чтобы не сильно сторонние либы юзать
нашёл вот эту штуку https://github.com/ryanluker/vscode-coverage-gutters
Она генерит неплохую html и норм отчёт + можно смотреть в IDE покрытие красивенько (в VSCode по крайней мере)

TL;DR актуальная версия (если я не замохал с обновлением) результатов code coverage можно найти в директории codeCoverageResult/index.html

Чтобы сгенерить новую, делаем:

Windows:
```sh
.\run_code_coverage.cmd
```

Mac or Linux (на линухе не проверял):
```sh
sh code_coverage
```

Либо руками вызываем по порядочку и смотрим красивенькую html в директории codeCoverageResult:
```sh
mvn test
mvn jacoco:report
mv target/site/jacoco/jacoco.xml cov.xml -Force
mv target/site/jacoco/index.html ./codeCoverageResult/index.html -Force
```


# ?

А всё.