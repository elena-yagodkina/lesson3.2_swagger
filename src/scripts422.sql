CREATE TABLE cars (
car_make TEXT NOT NULL,
car_model TEXT NOT NULL,
price INTEGER NOT NULL,
id SERIAL
);

CREATE TABLE drivers (
name TEXT NOT NULL,
age INTEGER CHECK (age > 18) NOT NULL,
car_license BOOLEAN,
id_car SERIAL REFERENCES cars(id) NOT NULL
);