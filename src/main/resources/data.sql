-- Users
INSERT INTO users(fullname,email,password,phone) VALUES
 ('Alice Johnson','alice@example.com','123456','0123456789'),
 ('Bob Smith','bob@example.com','123456','0987654321'),
 ('Charlie Brown','charlie@example.com','123456','0111111111');

-- Categories
INSERT INTO categories(name, images) VALUES
 ('Electronics','/img/cat/electronics.png'),
 ('Books','/img/cat/books.png'),
 ('Fashion','/img/cat/fashion.png');

-- N-N user_categories
INSERT INTO user_categories(user_id, category_id) VALUES (1,1),(1,2),(2,2),(3,3);

-- Products
INSERT INTO products(title,quantity,description,price,user_id,category_id) VALUES
 ('Phone A',10,'Entry smartphone',199.99,1,1),
 ('Laptop B',5,'Lightweight laptop',899.00,1,1),
 ('Novel C',50,'Bestseller novel',15.50,2,2),
 ('T-Shirt D',100,'Cotton T-Shirt',9.99,3,3),
 ('Headphones E',20,'Wireless headphones',59.99,1,1);