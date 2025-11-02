CREATE TABLE s_user (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
	user_name VARCHAR(50),
	role VARCHAR(20),
	password VARCHAR(255)
);

CREATE TABLE s_product (
	product_id INT AUTO_INCREMENT PRIMARY KEY,
	product_name VARCHAR(100),
	stock INT,
	price DECIMAL(10,2)
);

CREATE TABLE s_order (
	order_id INT AUTO_INCREMENT PRIMARY KEY,
	product_id INT,
	user_id INT,
	quantity INT,
	status VARCHAR(20),
	order_date TIMESTAMP
);

