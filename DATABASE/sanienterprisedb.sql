-- Create USER table
CREATE TABLE USER (
    id_number INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    surname VARCHAR(25) NOT NULL,
    email VARCHAR(100) NOT NULL,
    contact_number VARCHAR(10) NOT NULL,
    age INT NOT NULL,
    gender CHAR(1) NOT NULL,
    birth_date DATE NOT NULL,
    address_id INT NOT NULL,
    account_id INT NOT NULL
    FOREIGN KEY (address_id) REFERENCES USER(address_id),
    FOREIGN KEY (acount_id) REFERENCES USER(account_id)
);

-- Create CUSTOMER table
CREATE TABLE CUSTOMER (
    customer_id INT AUTO_INCwREMENT PRIMARY KEY,
    FOREIGN KEY (customer_id) REFERENCES USER(id_number)
);

-- Create ADMIN table
CREATE TABLE ADMIN (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (admin_id) REFERENCES USER(id_number)
);

-- Create ADDRESS table
CREATE TABLE ADDRESS (
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    province VARCHAR(100) NOT NULL,
    suburb VARCHAR(100) NOT NULL,
    zip_code VARCHAR(20) NOT NULL
);

-- Create ACCOUNT table
CREATE TABLE ACCOUNT (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    wishlist_id INT,
    cart_id INT,
    history_id INT,
    FOREIGN KEY (wishlist_id) REFERENCES WISHLIST(wishlist_id),
    FOREIGN KEY (cart_id) REFERENCES CART(cart_id),
    FOREIGN KEY (history_id) REFERENCES HISTORY(history_id)
);

-- Create PRODUCTS table
CREATE TABLE PRODUCTS (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_description TEXT NOT NULL,
    product_price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    date_added TIMESTAMP NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id)
);

-- Create IMAGE table
CREATE TABLE IMAGE (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    image_binary BLOB,
    image_angle INT
);

-- Create CATEGORY table
CREATE TABLE CATEGORY (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

-- Create WISHLIST table
CREATE TABLE WISHLIST (
    wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES PRODUCTS(product_id)
);

-- Create CART table
CREATE TABLE CART (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES PRODUCTS(product_id)
);

-- Create HISTORY table
CREATE TABLE HISTORY (
    history_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES PRODUCTS(product_id)
);

-- Create SPECIAL table
CREATE TABLE SPECIAL (
    special_id INT AUTO_INCREMENT PRIMARY KEY,
    discount DECIMAL(5, 2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);
