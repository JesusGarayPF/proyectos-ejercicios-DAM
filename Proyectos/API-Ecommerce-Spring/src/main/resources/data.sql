-- INSERTAR CATEGORÍAS
INSERT INTO categories (category_id, name, description)
VALUES (1, 'Electronics', 'Electronic gadgets and devices'),
       (2, 'Books', 'Books of various genres'),
       (3, 'Clothing', 'Apparel for men and women'),
       (4, 'Home Appliances', 'Appliances for everyday use');
ALTER TABLE categories ALTER COLUMN category_id RESTART WITH 5;

-- INSERTAR PRODUCTOS (10 en una categoría, 10 en dos categorías)
INSERT INTO products (product_id, price, stock, sku, name, description)
VALUES (1, 199.99, 50, 'ELEC001', 'Smartphone', 'Latest smartphone with advanced features'),
       (2, 599.99, 30, 'ELEC002', 'Laptop', 'High-performance laptop for work and play'),
       (3, 29.99, 100, 'ELEC003', 'Headphones', 'Noise-cancelling over-ear headphones'),
       (4, 15.99, 200, 'BOOK001', 'Fiction Book', 'A captivating fiction novel'),
       (5, 20.99, 150, 'BOOK002', 'Cookbook', 'Delicious recipes from around the world'),
       (6, 10.99, 300, 'CLOT001', 'T-shirt', 'Comfortable cotton T-shirt'),
       (7, 49.99, 80, 'CLOT002', 'Jeans', 'Classic blue jeans'),
       (8, 150.00, 20, 'HOME001', 'Microwave', 'Compact microwave oven'),
       (9, 200.00, 25, 'HOME002', 'Vacuum Cleaner', 'Powerful and lightweight vacuum cleaner'),
       (10, 100.00, 60, 'ELEC004', 'Smartwatch', 'Smartwatch with fitness tracking'),
-- Productos en dos categorías
       (11, 399.99, 40, 'ELEC005', 'Tablet', 'Portable and versatile tablet'),
       (12, 25.99, 120, 'BOOK003', 'Self-help Book', 'Inspirational and motivational book'),
       (13, 15.99, 250, 'CLOT003', 'Cap', 'Stylish baseball cap'),
       (14, 300.00, 10, 'HOME003', 'Air Purifier', 'Keeps the air clean and fresh'),
       (15, 99.99, 35, 'ELEC006', 'Bluetooth Speaker', 'Portable wireless speaker'),
       (16, 75.99, 55, 'HOME004', 'Blender', 'High-speed blender for smoothies'),
       (17, 150.99, 15, 'ELEC007', 'Monitor', 'Full HD computer monitor'),
       (18, 20.99, 120, 'BOOK004', 'Graphic Novel', 'Visually stunning graphic novel'),
       (19, 5.99, 400, 'CLOT004', 'Socks', 'Pack of comfortable socks'),
       (20, 12.99, 220, 'BOOK005', 'Children''s Book', 'Engaging stories for kids');
ALTER TABLE products ALTER COLUMN product_id RESTART WITH 21;

-- ASOCIAR PRODUCTOS A CATEGORÍAS
INSERT INTO product_categories (product_id, category_id) VALUES
(1, 1), (2, 1), (3, 1), (10, 1),
(4, 2), (5, 2), (12, 2), (18, 2), (20, 2),
(6, 3), (7, 3), (13, 3), (19, 3),
(8, 4), (9, 4), (14, 4), (16, 4),
-- Productos en dos categorías
(11, 1), (11, 4), (15, 1), (15, 4);

-- INSERTAR CLIENTES
INSERT INTO customers (customer_id, phone_number, email, password, first_name, last_name, address) VALUES
(1, '123456789', 'john.doe@example.com', 'password1', 'John', 'Doe', '123 Main Street'),
(2, '987654321', 'jane.smith@example.com', 'password2', 'Jane', 'Smith', '456 Oak Avenue'),
(3, '555123456', 'bob.brown@example.com', 'password3', 'Bob', 'Brown', '789 Pine Road'),
(4, '111222333', 'alice.white@example.com', 'password4', 'Alice', 'White', '321 Birch Lane');
ALTER TABLE customers ALTER COLUMN customer_id RESTART WITH 5;
-- INSERTAR PEDIDO CON TRES PRODUCTOS
INSERT INTO orders (order_id, customer_id, order_total) VALUES
(1, 1, 655.97); -- Total calculado con los productos
ALTER TABLE orders ALTER COLUMN order_id RESTART WITH 2;

INSERT INTO order_items (order_item_id, order_id, product_id, quantity, "unit_price") VALUES
(1, 1, 1, 1, 199.99), -- Smartphone
(2, 1, 2, 1, 599.99), -- Laptop
(3, 1, 3, 1, 29.99);  -- Headphones
ALTER TABLE order_items ALTER COLUMN order_item_id RESTART WITH 4;

-- INSERTAR LISTA DE DESEOS CON TRES PRODUCTOS
INSERT INTO wishlist (wishlist_id, customer_id, shared, name) VALUES
(1, 2, 0,'Christmas Wishlist'),
(2,3,1,'Trends Wishlist'),
(3,3,0,'Urgent Wishlist');
ALTER TABLE wishlist ALTER COLUMN wishlist_id RESTART WITH 4;

INSERT INTO wishlist_products (wishlist_id, product_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 4), (2, 5), (2, 6);

-- INSERTAR PRODUCTOS EN EL CARRITO DE LA COMPRA
INSERT INTO cart_items (cart_item_id, customer_id, product_id, quantity) VALUES
(1, 3, 1, 2), -- 2 Smartphones
(2, 3, 4, 1), -- 1 Fiction Book
(3, 3, 6, 3), -- 3 T-shirts
(4, 3, 8, 1); -- 1 Microwave
ALTER TABLE cart_items ALTER COLUMN cart_item_id RESTART WITH 5;
