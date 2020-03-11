/*
Navicat MySQL Data Transfer

Source Server         : hmm
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : lianxi

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-11 17:59:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bank_card
-- ----------------------------
DROP TABLE IF EXISTS `bank_card`;
CREATE TABLE `bank_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `bank_card_number` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `password` int(255) DEFAULT NULL COMMENT '银行卡密码 6位数',
  `balance` double(55,0) NOT NULL COMMENT '银行卡余额',
  `integral` int(255) DEFAULT NULL COMMENT '卡上积分',
  `reputation_value` int(255) DEFAULT NULL COMMENT '卡信誉值',
  `status` int(11) DEFAULT NULL COMMENT '银行卡状态：17为正常，19为冻结，16待审核；18，审核未通过',
  `create_time` date DEFAULT NULL COMMENT '银行卡申请时间',
  `type` int(11) DEFAULT NULL COMMENT '11:工商银行 12:建设银行 13:农业银行 14中国银行',
  `borrow_balance` double(10,0) DEFAULT NULL COMMENT '可借金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_card
-- ----------------------------
