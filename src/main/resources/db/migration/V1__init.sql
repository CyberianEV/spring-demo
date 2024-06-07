CREATE TABLE IF NOT EXISTS products (id BIGSERIAL PRIMARY KEY, title VARCHAR(255), price NUMERIC(10, 2));

INSERT INTO products (title, price) VALUES ('Bread', 1.95), ('Milk', 0.95), ('Cheese', 3.20);