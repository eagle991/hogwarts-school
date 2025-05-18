-- Создание таблицы "человек"
CREATE TABLE person (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        age INT NOT NULL,
                        has_license BOOLEAN NOT NULL,
                        car_id INT REFERENCES car(id)
);

-- Создание таблицы "машина"
CREATE TABLE car (
                     id SERIAL PRIMARY KEY,
                     brand VARCHAR(255) NOT NULL,
                     model VARCHAR(255) NOT NULL,
                     price DECIMAL(10, 2) NOT NULL
);

-- Добавление внешнего ключа в таблицу "человек" для связи с таблицей "машина"
ALTER TABLE person ADD CONSTRAINT fk_person_car FOREIGN KEY (car_id) REFERENCES car(id);