DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
  `userName` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(45) COLLATE utf8_bin NOT NULL,
  `userType` int(11) NOT NULL DEFAULT '0' COMMENT '0:代表买家；1:代表卖家',
  PRIMARY KEY (`userName`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `user` VALUES ('buyer','reyub',0),('seller','relles',1);