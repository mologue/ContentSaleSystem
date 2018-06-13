DROP TABLE IF EXISTS `order`;

CREATE TABLE `order`(
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `contentId` int(11) DEFAULT NULL,
  `title` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  `picture` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY ( `orderId`)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE `order` ADD index (`userName`);

insert into `order` values(1,'buyer',1,'手表','https://ws3.sinaimg.cn/large/006tKfTcly1fodx8jix36j31040j2gw9.jpg','2018-03-31 19:49:20.000000',1688,1);