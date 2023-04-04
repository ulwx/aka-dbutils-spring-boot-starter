/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.31-log : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `name` varchar(20) DEFAULT NULL,
  `address_id` int(11) ,
  `custormer_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`name`,`address_id`,`custormer_id`) values 
('a',1,1605621808120),
('b',2,1605621808120),
('c',3,1605621850314),
('c',4,1605621850314),
('e',5,1605621873398);

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

insert  into `customer`(`id`,`first_name`,`dob`) values 
(1605621808120,'Albert','1904-05-13'),
(1605621850314,'Albert','1904-05-13'),
(1605621873398,'Albert','1904-05-13'),
(1605621895633,'Albert','1904-05-13'),
(1605622080160,'Hans Albert','1904-05-13'),
(1605622194888,'Hans Albert','1904-05-13'),
(1605770657473,'Hans Albert','1904-05-13'),
(1607232338675,'Hans Albert','1904-05-13'),
(1607407361211,'Hans Albert','1904-05-13'),
(1609216553890,'Hans Albert','1904-05-13'),
(1609216949479,'Hans Albert','1904-05-13');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
