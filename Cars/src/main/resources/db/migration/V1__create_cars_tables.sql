CREATE TABLE car (
    carId INT PRIMARY KEY,
    make VARCHAR(255),
    year INT,
    model VARCHAR(255)
);

CREATE TABLE category (
    categoryId INT PRIMARY KEY,
    category VARCHAR(255)
);
CREATE TABLE car_category (
    car_id INT,
    category_id INT,
    PRIMARY KEY (car_id, category_id),
    FOREIGN KEY (car_id) REFERENCES car(carId),
    FOREIGN KEY (category_id) REFERENCES category(categoryId)
);