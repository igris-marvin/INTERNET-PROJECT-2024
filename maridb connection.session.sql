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
    date_added DATE NOT NULL,
    address_id INT NOT NULL,
    account_id INT NOT NULL
    FOREIGN KEY (address_id) REFERENCES USER(address_id),
    FOREIGN KEY (acount_id) REFERENCES USER(account_id)
);
