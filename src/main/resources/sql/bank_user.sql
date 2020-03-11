/*
Navicat MySQL Data Transfer

Source Server         : hmm
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : lianxi

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-11 18:04:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bank_user
-- ----------------------------
DROP TABLE IF EXISTS `bank_user`;
CREATE TABLE `bank_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL COMMENT '1男，2女',
  `age` int(11) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL COMMENT '1在，2无',
  `message` varchar(255) DEFAULT NULL COMMENT '验证码',
  `type` int(11) DEFAULT NULL COMMENT '1,用户，2，管理员',
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_user
-- ----------------------------
