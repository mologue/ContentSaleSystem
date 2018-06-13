
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: contentSale
-- ------------------------------------------------------
-- Server version	5.7.19


DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`(
`contentId` int(11) NOT NULL AUTO_INCREMENT,
`title` VARCHAR (80) COLLATE utf8_bin DEFAULT NULL,
`picture` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL,
`summary` varchar(140) COLLATE utf8_bin DEFAULT NULL,
`detail` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
`price` double NOT NULL,
`sellerName` VARCHAR (45) COLLATE utf8_bin DEFAULT NULL,
PRIMARY KEY (`contentId`)
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

