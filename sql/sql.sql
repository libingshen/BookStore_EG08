CREATE DATABASE bookstore_0423;

CREATE TABLE bs_user(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(50) UNIQUE NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL,
	email VARCHAR(50)
)

CREATE TABLE bs_book(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(50),
	author VARCHAR(50),
	price DOUBLE(11,2),
	sales INT(11),
	stock INT(11),
	img_path VARCHAR(100)
)

CREATE TABLE bs_order(
	id VARCHAR(50) PRIMARY KEY,
	`date` DATETIME,
	state INT(5),
	user_id INT(11),
	CONSTRAINT `FK_ID` FOREIGN KEY (`user_id`) REFERENCES `bs_user` (`id`)
)

CREATE TABLE bs_order_item(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	`count` INT(11),
	amount DOUBLE(11,2),
	book_id INT(11),
	order_id VARCHAR(50),
	CONSTRAINT `FK_BOOK_ID` FOREIGN KEY (`book_id`) REFERENCES `bs_book` (`id`),
	CONSTRAINT `FK_ORDER_ID` FOREIGN KEY (`order_id`) REFERENCES `bs_order` (`id`)	
)