DROP DATABASE IF EXISTS VirtualFit_application;
CREATE DATABASE IF NOT EXISTS VirtualFit_application;

USE VirtualFit_application;

DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Administrator;
DROP TABLE IF EXISTS PremiumCustomer;
DROP TABLE IF EXISTS Order;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Offers;
DROP TABLE IF EXISTS OrdersHistory;

CREATE TABLE Customer(
	customerId int(5) NOT NULL PRIMARY KEY auto_increment,
	email varchar(255) NOT NULL,
      username varchar(18) NOT NULL ,
	password varchar(18) NOT NULL,
	Member ENUM('Free','Premium')
);

CREATE TABLE PremiumCustomer(
	points INT
);

CREATE TABLE Administrator(
	username varchar(18) NOT NULL,
	password varchar(18) NOT NULL,
	email varchar(255) NOT NULL PRIMARY KEY,
	member ENUM('Admin')
);

CREATE TABLE Order(
	customerEmail varchar(50) NOT NULL PRIMARY KEY,
    	customerName varchar(20) NOT NULL, 
	itemListName varchar(100) NOT NULL,
    	quantityPerItem int,
   	orderDate date, 
	cost double 
);

CREATE TABLE Product(
	productId int PRIMARY KEY, 
	productName varchar(255) NOT NULL,
	price double,
	quantity int
);

CREATE TABLE Offers(
	offerProductId int PRIMARY KEY,
	itemListName varchar(50) NOT NULL,
	discount double
);

CREATE TABLE OrdersHistory(
	customerEmail varchar(100) NOT NULL,
      buyListDate date,
    	orderId int PRIMARY KEY
);

CREATE TABLE favouriteProducts(
	fCustomerId int(5) NOT NULL PRIMARY KEY,
   	fProductName VARCHAR(100) NOT NULL,
    	fProducrId INT(5) NOT NULL
);
	


