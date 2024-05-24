drop table if exists enrollments;

drop table if exists progress;

drop table if exists lessons;

drop table if exists courses;

drop table if exists users;

create table users
(
    id       bigserial primary key,
    username varchar(50)  not null unique,
    password varchar(100) not null,
    email    varchar(100) not null unique,
    role     varchar(50)  not null
);

create table courses
(
    id            bigserial primary key,
    title         varchar(100) not null,
    description   text         not null,
    instructor_id bigint references users (id)
);

create table lessons
(
    id        bigserial primary key,
    title     varchar(100) not null,
    content   text         not null,
    course_id bigint references courses (id)
);

create table enrollments
(
    id          bigserial primary key,
    user_id     bigint references users (id),
    course_id   bigint references courses (id),
    enrolled_at timestamp default now(),
    constraint unique_enrollment unique (user_id, course_id)
);

create table progress
(
    id           bigserial primary key,
    user_id      bigint references users (id),
    lesson_id    bigint references lessons (id),
    completed_at timestamp,
    constraint unique_progress unique (user_id, lesson_id)
);

INSERT INTO users (username, password, email, role)
VALUES ('alice', '$2a$10$O1t3zRr0Fjk6M48o4/SzKuA0KOQExE7c4xCxT0yOlXt6qNfxyDjiG', 'alice@example.com', 'STUDENT'),
       ('bob', '$2a$10$9Bl2Cm.8Yb4d/tM8zUwF8uT9kBQdksKFD3Ys4aBzwKEL9.12erD2K', 'bob@example.com', 'INSTRUCTOR'),
       ('charlie', '$2a$10$Z5PZK5UZUwO3GkpTYmZmduoHHtOiz.Cx/jdVjH2j1VFlNchS6HfVO', 'charlie@example.com', 'STUDENT');

INSERT INTO courses (title, description, instructor_id)
VALUES ('Spring Boot Basics', 'Learn the basics of Spring Boot', 2),
       ('Advanced Java', 'Deep dive into advanced Java topics', 2);

INSERT INTO lessons (title, content, course_id)
VALUES ('Introduction to Spring Boot', 'Content for Introduction to Spring Boot', 1),
       ('Spring Boot Annotations', 'Content for Spring Boot Annotations', 1),
       ('Java Streams', 'Content for Java Streams', 2),
       ('Java Concurrency', 'Content for Java Concurrency', 2);

INSERT INTO enrollments (user_id, course_id)
VALUES (1, 1),
       (3, 1),
       (1, 2);

INSERT INTO progress (user_id, lesson_id, completed_at)
VALUES (1, 1, '2024-05-23 10:00:00'),
       (3, 1, '2024-05-23 11:00:00'),
       (1, 3, '2024-05-23 12:00:00');


--     Пользователь Alice: Пароль - password1
--     Пользователь Bob: Пароль - password2
--     Пользователь Charlie: Пароль - password3

select * from users