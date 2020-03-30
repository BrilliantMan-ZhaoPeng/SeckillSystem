
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `a_order`;
CREATE TABLE `a_order` (
  `order_id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `a_product`;
CREATE TABLE `a_product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_address` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `a_product` VALUES ('1', '中国深圳', 'ihone', '10000000');
INSERT INTO `a_product` VALUES ('2', '中国绵阳', '火箭', '1');
INSERT INTO `a_product` VALUES ('3', '中国成都', '熊猫玩具', '12');


DROP TABLE IF EXISTS `a_user`;
CREATE TABLE `a_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_address` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `a_user` VALUES ('1', '绵阳', 'zs', '123123123', '123');
