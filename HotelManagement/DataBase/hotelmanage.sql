# MySQL-Front 5.0  (Build 1.107)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: hotelmanage
# ------------------------------------------------------
# Server version 5.0.9-beta

DROP DATABASE IF EXISTS `hotelmanage`;
CREATE DATABASE `hotelmanage` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hotelmanage`;

#
# Table structure for table hotel_cat
#

CREATE TABLE `hotel_cat` (
  `ID_` bigint(20) NOT NULL auto_increment,
  `NAME_` varchar(255) default NULL,
  `AGE_` int(11) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Dumping data for table hotel_cat
#
LOCK TABLES `hotel_cat` WRITE;
/*!40000 ALTER TABLE `hotel_cat` DISABLE KEYS */;

INSERT INTO `hotel_cat` VALUES (1,'mimi',5);
INSERT INTO `hotel_cat` VALUES (2,'MiMi',5);
INSERT INTO `hotel_cat` VALUES (3,'咪咪',5);
INSERT INTO `hotel_cat` VALUES (4,'大黄',5);
INSERT INTO `hotel_cat` VALUES (5,'大黄',5);
INSERT INTO `hotel_cat` VALUES (6,'大黄',5);
INSERT INTO `hotel_cat` VALUES (7,'大黄',5);
INSERT INTO `hotel_cat` VALUES (8,'大黄',5);
INSERT INTO `hotel_cat` VALUES (9,'大黄',5);
INSERT INTO `hotel_cat` VALUES (10,'大黄',5);
INSERT INTO `hotel_cat` VALUES (11,'大黄',5);
INSERT INTO `hotel_cat` VALUES (12,'大黄',5);
INSERT INTO `hotel_cat` VALUES (13,'大黄',5);
INSERT INTO `hotel_cat` VALUES (14,'大黄',5);
INSERT INTO `hotel_cat` VALUES (15,'大黄',5);
/*!40000 ALTER TABLE `hotel_cat` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table hotel_room
#



#
# Dumping data for table hotel_room
#
LOCK TABLES `hotel_room` WRITE;
/*!40000 ALTER TABLE `hotel_room` DISABLE KEYS */;

INSERT INTO `hotel_room` VALUES (1,'A','1001','大众房','一层',90,'备注信息');
INSERT INTO `hotel_room` VALUES (6,'A','1002','大众房','一层',90,'');
INSERT INTO `hotel_room` VALUES (7,'A','1003','大众房','一层',90,'');
INSERT INTO `hotel_room` VALUES (8,'A','1004','大众房','一层',90,'');
/*!40000 ALTER TABLE `hotel_room` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table hotel_user
#

CREATE TABLE `hotel_user` (
  `ID_` bigint(20) NOT NULL auto_increment,
  `CLASS_` char(1) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `LOGINNAME_` varchar(255) default NULL,
  `DESCRIPTION_` text,
  `PASSWORD_` varchar(255) default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Dumping data for table hotel_user
#
LOCK TABLES `hotel_user` WRITE;
/*!40000 ALTER TABLE `hotel_user` DISABLE KEYS */;

INSERT INTO `hotel_user` VALUES (1,'A','管理员','admin',NULL,'admin');
/*!40000 ALTER TABLE `hotel_user` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
