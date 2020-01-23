
CREATE DATABASE IF NOT EXISTS `application` ;

USE `application`;

/*Table structure for table `addins` */

DROP TABLE IF EXISTS `addins`;

CREATE TABLE `addins` (
  `Object_name` VARCHAR(250) DEFAULT NULL,
  `Object_price` INT(200) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Data for the table `addins` */

INSERT  INTO `addins`(`Object_name`,`Object_price`) VALUES 
('Phone case',500),
('USB Cable',800),
('JBL Headphones',1700),
('Bose Bluetooth Speaker',10000);

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `product_name` VARCHAR(250) DEFAULT NULL,
  `price` INT(200) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Data for the table `products` */

INSERT  INTO `products`(`product_name`,`price`) VALUES 
('oneplus 6',450000),
('Iphone x',850000),
('Samsung s8',520000),
('Xiomi',150000);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) DEFAULT NULL,
  `email` VARCHAR(200) DEFAULT NULL,
  `gender` VARCHAR(200) DEFAULT NULL,
  `contact` VARCHAR(200) DEFAULT NULL,
  PRIMARY KEY (`user`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

INSERT  INTO `user`(`user`,`password`,`email`,`gender`,`contact`) VALUES 
('parth','par','parthapadu@gmail.com','male','9955113377');


