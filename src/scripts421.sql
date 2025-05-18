-- Ограничение на возраст студента (не менее 16 лет)
ALTER TABLE student ADD CONSTRAINT chk_student_age CHECK (age >= 16);

-- Ограничение на уникальность имени студента и неравенство нулю
ALTER TABLE student ADD CONSTRAINT unique_student_name UNIQUE (name);
ALTER TABLE student ADD CONSTRAINT chk_student_name_not_null CHECK (name IS NOT NULL AND name <> '');

-- Значение по умолчанию для возраста студента (20 лет)
ALTER TABLE student ALTER COLUMN age SET DEFAULT 20;

-- Ограничение на уникальность пары "название факультета" - "цвет факультета"
ALTER TABLE faculty ADD CONSTRAINT unique_faculty_name_color UNIQUE (name, color);