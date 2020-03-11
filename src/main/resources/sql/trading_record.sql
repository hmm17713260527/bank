/*
Navicat MySQL Data Transfer

Source Server         : hmm
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : lianxi

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-11 18:05:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for trading_record
-- ----------------------------
DROP TABLE IF EXISTS `trading_record`;
CREATE TABLE `trading_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_card` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `deal_money` varchar(10) DEFAULT NULL COMMENT '交易金额',
  `deal_time` datetime DEFAULT NULL COMMENT '交易时间',
  `balance_money` double(10,0) DEFAULT NULL COMMENT '卡剩余余额',
  `pay_way` varchar(255) DEFAULT NULL COMMENT '支付方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trading_record
-- ----------------------------
