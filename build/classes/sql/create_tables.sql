--ユーザーアカウントテーブル
CREATE TABLE s_user (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
	user_name VARCHAR(50),
	role VARCHAR(20),
	password VARCHAR(255)
);
--商品情報テーブル
CREATE TABLE s_product (
	product_id INT AUTO_INCREMENT PRIMARY KEY,
	product_name VARCHAR(100),
	stock INT,
	price DECIMAL(10,2)
);
--発注履歴テーブル
CREATE TABLE s_order (
	order_id INT AUTO_INCREMENT PRIMARY KEY,
	product_id INT,
	user_id INT,
	quantity INT,
	status VARCHAR(20),
	order_date TIMESTAMP
);
--顧客情報テーブル
CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(50),
    address VARCHAR(255),
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
--売上履歴テーブル
CREATE TABLE sales (
    sale_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    customer_id INT,
    quantity INT NOT NULL,
    sale_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    remarks VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES s_product(product_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);
