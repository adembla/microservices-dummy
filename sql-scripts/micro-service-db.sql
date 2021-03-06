CREATE SCHEMA prod_ms;

use prod_ms;

DROP TABLE IF EXISTS categories;

CREATE TABLE IF NOT EXISTS categories (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    CATEGORY_NAME VARCHAR(200) NOT NULL
);

commit;

INSERT INTO categories(ID,CATEGORY_NAME)
 VALUES (1,'CLOTHES');
 
 INSERT INTO categories(ID,CATEGORY_NAME)
 VALUES (2,'ELECTRONICS');
 
INSERT INTO categories(ID,CATEGORY_NAME)
 VALUES (3,'PERSONAL CARE');
  
INSERT INTO categories(ID,CATEGORY_NAME)
 VALUES (4,'HOME & KITCHEN');

INSERT INTO categories(ID,CATEGORY_NAME)
 VALUES (5,'SPORTS & FITNESS');

commit;

DROP TABLE IF EXISTS products;
CREATE TABLE IF NOT EXISTS products (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_NAME VARCHAR(200) NOT NULL,
    PRICE DOUBLE(10,4),
    CATEGORY_ID BIGINT NOT NULL,
    FOREIGN KEY (CATEGORY_ID)
        REFERENCES categories (ID)
);

ALTER TABLE `prod_ms`.`products` 
ADD COLUMN `PRODUCT_DSCR` VARCHAR(45) NULL AFTER `CATEGORY_ID`,
ADD COLUMN `QUANTITY` INT NULL AFTER `PRODUCT_DSCR`;


commit;


INSERT INTO products(PRODUCT_NAME,PRICE,CATEGORY_ID,PRODUCT_DSCR,QUANTITY) VALUES('Shirts',1350.0,1,'Mens Denim Shirt',15); 
INSERT INTO products(PRODUCT_NAME,PRICE,CATEGORY_ID,PRODUCT_DSCR,QUANTITY) VALUES('Dumbells',3150.0,5,'Decathlon Brand',20);
INSERT INTO products(PRODUCT_NAME,PRICE,CATEGORY_ID,PRODUCT_DSCR,QUANTITY) VALUES('Laptop',56050.0,2,'Lenovo Thinkpad',10); 
INSERT INTO products(PRODUCT_NAME,PRICE,CATEGORY_ID,PRODUCT_DSCR,QUANTITY) VALUES('Gas Stove',4300.0,4,'Pigeon Cookware',6); 
INSERT INTO products(PRODUCT_NAME,PRICE,CATEGORY_ID,PRODUCT_DSCR,QUANTITY) VALUES('Copper Chimney',3500.0,4,'Faber automatic Chimney',32); 
INSERT INTO products(PRODUCT_NAME,PRICE,CATEGORY_ID,PRODUCT_DSCR,QUANTITY) VALUES('Dove Shampoo',35.0,3,'Hindustan Uniliver Product',90); 
INSERT INTO products(PRODUCT_NAME,PRICE,CATEGORY_ID,PRODUCT_DSCR,QUANTITY) VALUES('Jeans',1700.0,1,'Mufti Jeans',18); 

commit;


CREATE SCHEMA cust_ms;

use cust_ms;

commit;

DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT EXISTS customer(ID BIGINT AUTO_INCREMENT PRIMARY KEY, CUST_NAME VARCHAR(400) NOT NULL,
CUST_GENDER VARCHAR(100), CUST_PHONE_NUM BIGINT(10) NOT NULL, ALT_NUM BIGINT(10));
commit;

INSERT INTO customer(CUST_NAME, CUST_GENDER, CUST_PHONE_NUM) VALUES('Anand Dembla','MALE',90876543); 

INSERT INTO customer(CUST_NAME, CUST_GENDER, CUST_PHONE_NUM) VALUES('John Cena','MALE',12345678); 

INSERT INTO customer(CUST_NAME, CUST_GENDER, CUST_PHONE_NUM) VALUES('Priyanka Dion','FEMALE',876541213); 

INSERT INTO customer(CUST_NAME, CUST_GENDER, CUST_PHONE_NUM) VALUES('Miley Cyrus','FEMALE',908734512); 

commit;

DROP TABLE IF EXISTS address;
CREATE TABLE IF NOT EXISTS address(ADDRESS_ID BIGINT AUTO_INCREMENT PRIMARY KEY, CUST_ID BIGINT NOT NULL,APARTMENT_NAME VARCHAR(500),
STREET_NAME VARCHAR(800),CITY VARCHAR(200),STATE VARCHAR(20),PIN INTEGER(10), FOREIGN KEY(CUST_ID) REFERENCES customer(ID));

commit;

INSERT INTO address(CUST_ID,APARTMENT_NAME,STREET_NAME,CITY,STATE,PIN)
 VALUES (1,'Eklavya Residency','Gandhi Baug','Bangalore','Karanataka',560037);
 
 INSERT INTO address(CUST_ID,APARTMENT_NAME,STREET_NAME,CITY,STATE,PIN)
 VALUES (2,'Aeropolis Residency','Karve Nagar','Pune','Maharashtra',412308);
 
 INSERT INTO address(CUST_ID,APARTMENT_NAME,STREET_NAME,CITY,STATE,PIN)
 VALUES (3,'Salunke Vihar','Kharadi','Pune','Maharashtra',411014);
 
 INSERT INTO address(CUST_ID,APARTMENT_NAME,STREET_NAME,CITY,STATE,PIN)
 VALUES (4,'Lodha Prime','Bombay Kurla Complex','Mumbai','Maharashtra',411213); 
 
 commit;
 
 
 CREATE SCHEMA invoice_ms;

USE invoice_ms;
 
 DROP TABLE IF EXISTS invoice;

CREATE TABLE IF NOT EXISTS invoice (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    CUST_ID BIGINT NOT NULL,
    DATE_OF_PURCHASE DATETIME,
    MODE_PAY_ID VARCHAR(200) NOT NULL,
    TOTAL_AMT INTEGER
);
commit;


insert into invoice(CUST_ID,DATE_OF_PURCHASE,MODE_PAY_ID,TOTAL_AMT) values(1,'2019-11-19 00:00:00','POS',55453);
insert into invoice(CUST_ID,DATE_OF_PURCHASE,MODE_PAY_ID,TOTAL_AMT) values(4,'2020-01-29 00:00:00','DEBIT CARD',1345);
insert into invoice(CUST_ID,DATE_OF_PURCHASE,MODE_PAY_ID,TOTAL_AMT) values(2,'2020-02-11 00:00:00','POS',2235);


DROP TABLE IF EXISTS orderitems;
Create table if not exists orderitems (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
	INVOICE_ID BIGINT NOT NULL,
	PROD_ID BIGINT NOT NULL,
    QUANTITY INTEGER(5),
	TOTAL_COST BIGINT,
    TRANSACTION_ID VARCHAR(200),
    CONSTRAINT tb_fk FOREIGN KEY (INVOICE_ID)
	REFERENCES invoice(ID)	
);
commit;


insert into orderitems(INVOICE_ID,PROD_ID, QUANTITY,TOTAL_COST,TRANSACTION_ID) values(1,2,1,3150.0,'asfr3412bgfv');
insert into orderitems(INVOICE_ID,PROD_ID, QUANTITY, TOTAL_COST,TRANSACTION_ID) values(2,4,1,4300.0,'asfr3412bgfv');
insert into orderitems(INVOICE_ID,PROD_ID, QUANTITY, TOTAL_COST,TRANSACTION_ID) values(3,7,10,350.0,'asfr3412bgfv');
insert into orderitems(INVOICE_ID,PROD_ID, QUANTITY, TOTAL_COST,TRANSACTION_ID) values(1,1,2,2700.0,'asfr3412bgfv');

commit;

CREATE SCHEMA login_ms;

use login_ms;