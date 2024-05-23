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

- `id` (PK): Уникальный идентификатор пользователя
- `username`: Имя пользователя
- `password`: Пароль (хранится в зашифрованном виде)
- `email`: Электронная почта
- `role`: Роль пользователя (студент, преподаватель, администратор)

### Courses (Курсы)

- `id` (PK): Уникальный идентификатор курса
- `title`: Название курса
- `description`: Описание курса
- `instructor_id` (FK): Идентификатор преподавателя (ссылка на `Users`)

### Lessons (Уроки)

- `id` (PK): Уникальный идентификатор урока
- `title`: Название урока
- `content`: Содержание урока (может быть текст, видео, ссылка и т.д.)
- `course_id` (FK): Идентификатор курса (ссылка на `Courses`)

### Enrollments (Записи)

- `id` (PK): Уникальный идентификатор записи
- `user_id` (FK): Идентификатор пользователя (ссылка на `Users`)
- `course_id` (FK): Идентификатор курса (ссылка на `Courses`)
- `enrolled_at`: Дата и время записи

### Progress (Прогресс)

- `id` (PK): Уникальный идентификатор прогресса
- `user_id` (FK): Идентификатор пользователя (ссылка на `Users`)
- `lesson_id` (FK): Идентификатор урока (ссылка на `Lessons`)
- `completed_at`: Дата и время завершения урока

## Таблица CRUD операций

### Users (Пользователи)

- **Create**: Регистрация нового пользователя
    - **POST** `/users`
    - Request Body: `{ "username": "string", "password": "string", "email": "string", "role": "string" }`

- **Read**: Получение информации о пользователе
    - **GET** `/users/{id}`
    - Response: `{ "id": "number", "username": "string", "email": "string", "role": "string" }`

- **Update**: Обновление информации о пользователе
    - **PUT** `/users/{id}`
    - Request Body: `{ "username": "string", "email": "string", "role": "string" }`

- **Delete**: Удаление пользователя
    - **DELETE** `/users/{id}`

### Courses (Курсы)

- **Create**: Создание нового курса
    - **POST** `/courses`
    - Request Body: `{ "title": "string", "description": "string", "instructor_id": "number" }`

- **Read**: Получение информации о курсе
    - **GET** `/courses/{id}`
    - Response: `{ "id": "number", "title": "string", "description": "string", "instructor_id": "number" }`

- **Update**: Обновление информации о курсе
    - **PUT** `/courses/{id}`
    - Request Body: `{ "title": "string", "description": "string" }`

- **Delete**: Удаление курса
    - **DELETE** `/courses/{id}`

### Lessons (Уроки)

- **Create**: Создание нового урока
    - **POST** `/lessons`
    - Request Body: `{ "title": "string", "content": "string", "course_id": "number" }`

- **Read**: Получение информации об уроке
    - **GET** `/lessons/{id}`
    - Response: `{ "id": "number", "title": "string", "content": "string", "course_id": "number" }`

- **Update**: Обновление информации об уроке
    - **PUT** `/lessons/{id}`
    - Request Body: `{ "title": "string", "content": "string" }`

- **Delete**: Удаление урока
    - **DELETE** `/lessons/{id}`

### Enrollments (Записи)

- **Create**: Запись пользователя на курс
    - **POST** `/enrollments`
    - Request Body: `{ "user_id": "number", "course_id": "number" }`

- **Read**: Получение информации о записи
    - **GET** `/enrollments/{id}`
    - Response: `{ "id": "number", "user_id": "number", "course_id": "number", "enrolled_at": "string" }`

- **Delete**: Удаление записи
    - **DELETE** `/enrollments/{id}`

### Progress (Прогресс)

- **Create**: Отметка о завершении урока
    - **POST** `/progress`
    - Request Body: `{ "user_id": "number", "lesson_id": "number" }`

- **Read**: Получение информации о прогрессе
    - **GET** `/progress/{id}`
    - Response: `{ "id": "number", "user_id": "number", "lesson_id": "number", "completed_at": "string" }`

- **Delete**: Удаление информации о прогрессе
    - **DELETE** `/progress/{id}`

## Как запустить проект

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/Asemokamichi/EduPath
