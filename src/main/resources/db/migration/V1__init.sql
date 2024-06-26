CREATE TABLE IF NOT EXISTS products (id BIGSERIAL PRIMARY KEY, title VARCHAR(100), price NUMERIC(10, 2), hidden_field VARCHAR(100));

INSERT INTO products (title, price, hidden_field)
    VALUES ('Bread', 1.95, 'h!dcIe$N'), ('Milk', 0.95, 'h!dcIe$N'), ('Cheese', 3.20, 'h!dcIe$N'),
            ('Eggs', 1.05, 'h!dcIe$N'), ('Tomatoes', 2.65, 'h!dcIe$N'), ('Cucumbers', 0.65, 'h!dcIe$N'),
            ('Chicken', 4.95, 'h!dcIe$N'), ('Pasta', 0.95, 'h!dcIe$N'), ('Cake', 4.60, 'h!dcIe$N');

CREATE TABLE IF NOT EXISTS users (
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(60) NOT NULL,
    password    VARCHAR(80) NOT NULL,
    email       VARCHAR(60) UNIQUE,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

INSERT INTO users (username, password, email)
    VALUES  ('Rick', '$2a$04$Y90HZQ6jS8TLTEyDCL3ISOFuXzlFPJ0Bj0VWMZeNxFz2Cr3EabzIK', 'rick@somemail.com'),
            ('John', '$2a$04$2YWb6fZxJbu0Ci0lLQD8jOFw7HJP0pbLsUTX7iL6SuoXFQgVmoggy', 'john@somemail.com'),
            ('Helen', '$2a$04$xQMcARcxHlW1kRBu/zNWz.xFozF.LnyvVmyLFwNRQxOLC.T7N96vy', 'helen@somemail.com');

CREATE TABLE IF NOT EXISTS roles (
    id          BIGSERIAL PRIMARY KEY,
    role        VARCHAR(60) NOT NULL,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

INSERT INTO roles (role)
    VALUES  ('ROLE_USER'),
            ('ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS users_roles (
    user_id BIGINT NOT NULL REFERENCES users (id),
    role_id BIGINT NOT NULL REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO users_roles (user_id, role_id)
    VALUES  (1, 2),
            (2, 1),
            (3, 2);