ALTER TABLE students
ADD CONSTRAINT age_constraint CHECK (age > 16);
ALTER COLUMN name SET NOT NULL;
ADD CONSTRAINT name_unique UNIQUE (name);

ALTER TABLE faculties
ADD CONSTRAINT name_color_unique UNIQUE (name, color);

