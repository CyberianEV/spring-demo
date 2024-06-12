CREATE TABLE IF NOT EXISTS products (id BIGSERIAL PRIMARY KEY, title VARCHAR(100), price NUMERIC(10, 2), hidden_field VARCHAR(100));

INSERT INTO products (title, price, hidden_field)
    VALUES ('Bread', 1.95, 'h!dcIe$N'), ('Milk', 0.95, 'h!dcIe$N'), ('Cheese', 3.20, 'h!dcIe$N'),
            ('Eggs', 1.05, 'h!dcIe$N'), ('Tomatoes', 2.65, 'h!dcIe$N'), ('Cucumbers', 0.65, 'h!dcIe$N'),
            ('Chicken', 4.95, 'h!dcIe$N'), ('Pasta', 0.95, 'h!dcIe$N'), ('Cake', 4.60, 'h!dcIe$N');