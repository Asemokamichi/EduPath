# EduPath

EduPath - это веб-приложение для создания и прохождения онлайн курсов, разработанное с использованием Spring Boot.

## Функциональные возможности

- Регистрация и авторизация пользователей
- Создание, обновление и удаление курсов
- Добавление и редактирование уроков в курсе
- Запись студентов на курсы
- Отслеживание прогресса студентов

## Схема базы данных

### Users (Пользователи)

| Поле       | Тип    | Описание                                                  |
|------------|--------|-----------------------------------------------------------|
| `id`       | PK     | Уникальный идентификатор пользователя                     |
| `username` | String | Имя пользователя                                          |
| `password` | String | Пароль (хранится в зашифрованном виде)                    |
| `email`    | String | Электронная почта                                         |
| `role`     | String | Роль пользователя (студент, преподаватель, администратор) |

### Courses (Курсы)

| Поле            | Тип    | Описание                                        |
|-----------------|--------|-------------------------------------------------|
| `id`            | PK     | Уникальный идентификатор курса                  |
| `title`         | String | Название курса                                  |
| `description`   | String | Описание курса                                  |
| `instructor_id` | FK     | Идентификатор преподавателя (ссылка на `Users`) |

### Lessons (Уроки)

| Поле        | Тип    | Описание                                       |
|-------------|--------|------------------------------------------------|
| `id`        | PK     | Уникальный идентификатор урока                 |
| `title`     | String | Название урока                                 |
| `content`   | String | Содержание урока (текст, видео, ссылка и т.д.) |
| `course_id` | FK     | Идентификатор курса (ссылка на `Courses`)      |

### Enrollments (Записи)

| Поле          | Тип    | Описание                                       |
|---------------|--------|------------------------------------------------|
| `id`          | PK     | Уникальный идентификатор записи                |
| `user_id`     | FK     | Идентификатор пользователя (ссылка на `Users`) |
| `course_id`   | FK     | Идентификатор курса (ссылка на `Courses`)      |
| `enrolled_at` | String | Дата и время записи                            |

### Progress (Прогресс)

| Поле           | Тип    | Описание                                       |
|----------------|--------|------------------------------------------------|
| `id`           | PK     | Уникальный идентификатор прогресса             |
| `user_id`      | FK     | Идентификатор пользователя (ссылка на `Users`) |
| `lesson_id`    | FK     | Идентификатор урока (ссылка на `Lessons`)      |
| `completed_at` | String | Дата и время завершения урока                  |

## Таблица CRUD операций

### Users (Пользователи)

| Операция                                         | Запрос                                                                                                           | Ошибка          | Сообщение                                                                                                                          |
|--------------------------------------------------|------------------------------------------------------------------------------------------------------------------|-----------------|------------------------------------------------------------------------------------------------------------------------------------|
| **Create**: Регистрация нового пользователя      | POST /users<br>Request Body: { "username": "string", "password": "string", "email": "string", "role": "string" } | 400 Bad Request | {"error": "InvalidRequest", "message": "The request body is invalid. Please provide a valid username, password, email, and role."} |
|                                                  |                                                                                                                  | 409 Conflict    | {"error": "UserAlreadyExists", "message": "A user with the provided email or username already exists."}                            |
| **Read**: Получение информации о пользователе    | GET /users/{id}                                                                                                  | 404 Not Found   | {"error": "UserNotFound", "message": "No user found with the provided ID."}                                                        |
| **Update**: Обновление информации о пользователе | PUT /users/{id}<br>Request Body: { "username": "string", "email": "string", "role": "string" }                   | 400 Bad Request | {"error": "InvalidRequest", "message": "The request body is invalid. Please provide a valid username, email, and role."}           |
|                                                  |                                                                                                                  | 404 Not Found   | {"error": "UserNotFound", "message": "No user found with the provided ID."}                                                        |
| **Delete**: Удаление пользователя                | DELETE /users/{id}                                                                                               | 404 Not Found   | {"error": "UserNotFound", "message": "No user found with the provided ID."}                                                        |

### Courses (Курсы)

| Операция                                  | Запрос                                                                                                   | Ошибка          | Сообщение                                                                                                                            |
|-------------------------------------------|----------------------------------------------------------------------------------------------------------|-----------------|--------------------------------------------------------------------------------------------------------------------------------------|
| **Create**: Создание нового курса         | POST /courses<br>Request Body: { "title": "string", "description": "string", "instructor_id": "number" } | 400 Bad Request | {"error": "InvalidRequest", "message": "The request body is invalid. Please provide a valid title, description, and instructor ID."} |
| **Read**: Получение информации о курсе    | GET /courses/{id}                                                                                        | 404 Not Found   | {"error": "CourseNotFound", "message": "No course found with the provided ID."}                                                      |
| **Update**: Обновление информации о курсе | PUT /courses/{id}<br>Request Body: { "title": "string", "description": "string" }                        | 400 Bad Request | {"error": "InvalidRequest", "message": "The request body is invalid. Please provide a valid title and description."}                 |
|                                           |                                                                                                          | 404 Not Found   | {"error": "CourseNotFound", "message": "No course found with the provided ID."}                                                      |
| **Delete**: Удаление курса                | DELETE /courses/{id}                                                                                     | 404 Not Found   | {"error": "CourseNotFound", "message": "No course found with the provided ID."}                                                      |

### Lessons (Уроки)

| Операция                                   | Запрос                                                                                           | Ошибка          | Сообщение                                                                                                                    |
|--------------------------------------------|--------------------------------------------------------------------------------------------------|-----------------|------------------------------------------------------------------------------------------------------------------------------|
| **Create**: Создание нового урока          | POST /lessons<br>Request Body: { "title": "string", "content": "string", "course_id": "number" } | 400 Bad Request | {"error": "InvalidRequest", "message": "The request body is invalid. Please provide a valid title, content, and course ID."} |
| **Read**: Получение информации об уроке    | GET /lessons/{id}                                                                                | 404 Not Found   | {"error": "LessonNotFound", "message": "No lesson found with the provided ID."}                                              |
| **Update**: Обновление информации об уроке | PUT /lessons/{id}<br>Request Body: { "title": "string", "content": "string" }                    | 400 Bad Request | {"error": "InvalidRequest", "message": "The request body is invalid. Please provide a valid title and content."}             |
|                                            |                                                                                                  | 404 Not Found   | {"error": "LessonNotFound", "message": "No lesson found with the provided ID."}                                              |
| **Delete**: Удаление урока                 | DELETE /lessons/{id}                                                                             | 404 Not Found   | {"error": "LessonNotFound", "message": "No lesson found with the provided ID."}                                              |

### Enrollments (Записи)

| Операция                                | Запрос                                                                            | Ошибка          | Сообщение                                                                                                            |
|-----------------------------------------|-----------------------------------------------------------------------------------|-----------------|----------------------------------------------------------------------------------------------------------------------|
| **Create**: Запись пользователя на курс | POST /enrollments<br>Request Body: { "user_id": "number", "course_id": "number" } | 400 Bad Request | {"error": "InvalidRequest", "message": "The request body is invalid. Please provide a valid user ID and course ID."} |
| **Read**: Получение информации о записи | GET /enrollments/{id}                                                             | 404 Not Found   | {"error": "EnrollmentNotFound", "message": "No enrollment found with the provided ID."}                              |
| **Delete**: Удаление записи             | DELETE /enrollments/{id}                                                          | 404 Not Found   | {"error": "EnrollmentNotFound", "message": "No enrollment found with the provided ID."}                              |

### Progress (Прогресс)

| Операция                                    | Запрос                                                                         | Ошибка          | Сообщение                                                                                                            |
|---------------------------------------------|--------------------------------------------------------------------------------|-----------------|----------------------------------------------------------------------------------------------------------------------|
| **Create**: Отметка о завершении урока      | POST /progress<br>Request Body: { "user_id": "number", "lesson_id": "number" } | 400 Bad Request | {"error": "InvalidRequest", "message": "The request body is invalid. Please provide a valid user ID and lesson ID."} |
| **Read**: Получение информации о прогрессе  | GET /progress/{id}                                                             | 404 Not Found   | {"error": "ProgressNotFound", "message": "No progress found with the provided ID."}                                  |
| **Delete**: Удаление информации о прогрессе | DELETE /progress/{id}                                                          | 404 Not Found   | {"error": "ProgressNotFound", "message": "No progress found with the provided ID."}                                  |

## Тестирование

В проекте также были написаны mock-тесты для контроллеров и сервисов для проверки функциональности регистрации,
авторизации

Запустите тесты:

   ```bash
   mvn test
   ```

## Как запустить проект

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/Asemokamichi/EduPath
