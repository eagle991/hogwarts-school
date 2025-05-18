-- Запрос 1: Получить информацию о студентах (имя и возраст) вместе с названиями факультетов
SELECT s.name AS student_name, s.age, f.name AS faculty_name
FROM student s
         LEFT JOIN faculty f ON s.faculty_id = f.id;

-- Запрос 2: Получить студентов, у которых есть аватарки
SELECT s.name AS student_name
FROM student s
         INNER JOIN avatar a ON s.id = a.student_id;