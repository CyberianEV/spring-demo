CREATE TABLE IF NOT EXISTS products (id BIGSERIAL PRIMARY KEY, title VARCHAR(255), price NUMERIC(10, 2));

INSERT INTO products (title, price)
    VALUES ('Bread', 1.95), ('Milk', 0.95), ('Cheese', 3.20), ('Eggs', 1.05), ('Tomatoes', 2.65), ('Cucumbers', 0.65),
            ('Chicken', 4.95), ('Pasta', 0.95), ('Cake', 4.60);