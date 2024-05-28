**1. Create (Регистрация нового пользователя):**
POST http://localhost:8080/users 
"Content-Type: application/json" {"username": "testuser", "password": "testpass", "email": "testuser@example.com", "role": "student"}

**2. Read (Получение информации о пользователе):**
GET http://localhost:8080/users/{id}

**3. Update (Обновление информации о пользователе):**
PUT http://localhost:8080/users/{id} 
"Content-Type: application/json" {"username": "updateduser", "email": "updateduser@example.com", "role": "instructor"}

**4. Delete (Удаление пользователя):**
DELETE http://localhost:8080/users/{id}

### Courses (Курсы)

**1. Create (Создание нового курса):**
POST http://localhost:8080/courses 
"Content-Type: application/json" {"title": "New Course", "description": "Course Description", "instructor_id": 1}

**2. Read (Получение информации о курсе):**
GET http://localhost:8080/courses/{id}

**3. Update (Обновление информации о курсе):**
PUT http://localhost:8080/courses/{id} 
"Content-Type: application/json" {"title": "Updated Course", "description": "Updated Description"}

**4. Delete (Удаление курса):**
DELETE http://localhost:8080/courses/{id}

### Lessons (Уроки)

**1. Create (Создание нового урока):**
POST http://localhost:8080/lessons 
"Content-Type: application/json" {"title": "New Lesson", "content": "Lesson Content", "course_id": 1}

**2. Read (Получение информации об уроке):**
GET http://localhost:8080/lessons/{id}

**3. Update (Обновление информации об уроке):**
PUT http://localhost:8080/lessons/{id} 
"Content-Type: application/json" {"title": "Updated Lesson", "content": "Updated Content"}

**4. Delete (Удаление урока):**
DELETE http://localhost:8080/lessons/{id}

### Enrollments (Записи)

**1. Create (Запись пользователя на курс):**
POST http://localhost:8080/enrollments 
"Content-Type: application/json" {"user_id": 1, "course_id": 1}

**2. Read (Получение информации о записи):**
GET http://localhost:8080/enrollments/{id}

**3. Delete (Удаление записи):**
DELETE http://localhost:8080/enrollments/{id}

### Progress (Прогресс)

**1. Create (Отметка о завершении урока):**
POST http://localhost:8080/progress 
"Content-Type: application/json" {"user_id": 1, "lesson_id": 1}

**2. Read (Получение информации о прогрессе):**
GET http://localhost:8080/progress/{id}

**3. Delete (Удаление информации о прогрессе):**
DELETE http://localhost:8080/progress/{id}
