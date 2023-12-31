### job4j_url_shortcut

# Short_cut
## О проекте

Данный проект является учебным и представляет собой 
реализацию RESTful сервиса по преобразованию URL-ссылок
в ассоциированные ключи (короткие ссылки). Также площадка предоставляет
статистику по количеству запросов по каждому URL-адресу. Авторизация 
осуществляется с помощью JWT.

## Стек технологий:

+ Java 17
+ Spring Boot 2.7
+ Hibernate 5,
+ Mockito 4, JUnit 5
+ Maven
+ PostgreSQL, Liquibase, h2(h2database)
+ Lombok
+ Spring Security
+ Java JWT

## Запуск приложения:

Для успешного запуска проекта на вашем компьютере
должно быть установлено:
- JDK 17(+)
- Maven
- PostgreSql
- Git

Процесс установки:
1. Скачайте проект к себе на компьютер, используя команду:  
   `git clone https://github.com/PavelM2205/job4j_url_shortcut.git`.
2. В PostgreSQL создайте базу данных с именем "url_shortcut".
3. Добавьте свои данные "spring.datasource.username" и 
"spring.datasource.password" для доступа к базе данных в файле  
   `application.properties`.
4. Перейдите в директорию проекта и выполните команду:  
   `mvn install -Dmaven.test.skip=true`.
5. Запустите приложение, используя команду:  
   `mvn exec:java -Dexec.mainClass=ru.job4j.url.shortcut.Job4jUrlShortcutApplication`.
6. После запуска приложение будет доступно по адресу:
   http://localhost:8080/.

## Использование:

Для начала работы необходимо зарегистрировать свой сайт
на площадке и получить автоматически сгенерированные логин и пароль.
Для этого необходимо отправить POST-запрос с телом запроса `"site":"ИМЯ_САЙТА"`
по адресу http://localhost:8080/registration.

Пример запроса:
![ScreenShot](src/main/resources/img/registration_request.png)

Ответ:
![ScreenShot](src/main/resources/img/registration_response.png)

Далее для аутентификации необходимо отправить полученные
логин и пароль в формате POST-запроса `"login":"ПОЛУЧЕННЫЙ_ЛОГИН, "password":"ПОЛУЧЕННЫЙ_ПАРОЛЬ"`
по адресу http://localhost:8080/login.

Пример запроса:
![ScreenShot](src/main/resources/img/login_request.png)

Ответ:
![ScreenShot](src/main/resources/img/login_response.png)

Для того, чтобы сгенерировать короткую ссылку отправьте POST-запрос в формате
`"url":"АДРЕС_ССЫЛКИ"` по адресу http://localhost:8080/convert.

Пример запроса:
![ScreenShot](src/main/resources/img/convert_request.png)

Ответ:
![ScreenShot](src/main/resources/img/convert_response.png)

Для того, чтобы перейти по адресу короткой ссылки на требующийся ресурс
отправьте GET-запрос по адресу http://localhost:8080/redirect/{shortLink}, где
`{shortLink}` - это имя короткой ссылки.

Пример запроса:
![ScreenShot](src/main/resources/img/redirect_request.png)

Для получения статистики о количестве запросов по каждому URL-адресу
отправьте GET-запрос по адресу http://localhost:8080/statistic.

Пример запроса:
![ScreenShot](src/main/resources/img/statistic_request.png)

Ответ:
![ScreenShot](src/main/resources/img/statistic_response.png)


