CREATE TABLE cars (
car_make TEXT NOT NULL,
car_model TEXT NOT NULL,
price INTEGER NOT NULL,
id SERIAL PRIMARY KEY
);

CREATE TABLE drivers (
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
age INTEGER CHECK (age > 18) NOT NULL,
car_license BOOLEAN
);

CREATE TABLE drivers_cars (
	drivers_id INTEGER NOT NULL REFERENCES drivers (id),
	car_id INTEGER NOT NULL REFERENCES cars (id)
);