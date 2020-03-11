/*
Navicat MySQL Data Transfer

Source Server         : hmm
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : lianxi

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-11 18:04:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bank_resource
-- ----------------------------
DROP TABLE IF EXISTS `bank_resource`;
CREATE TABLE `bank_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `is_del` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1.2用户展示；1.3管理员展示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_resource
-- ----------------------------
INSERT INTO `bank_resource` VALUES ('1', '权限管理', '0', '-', '1', '1');
INSERT INTO `bank_resource` VALUES ('2', '申请银行卡', '1', '/bankCard/toAdd', '1', '2');
INSERT INTO `bank_resource` VALUES ('3', '我的账户', '1', '/', '1', '2');
INSERT INTO `bank_resource` VALUES ('4', '交易记录', '3', '/trading/toShow', '1', '2');
INSERT INTO `bank_resource` VALUES ('5', '银行卡信息', '3', '/bankCard/toList', '1', '2');
INSERT INTO `bank_resource` VALUES ('6', '转账', '3', '/bankCard/toTransfer', '1', '2');
INSERT INTO `bank_resource` VALUES ('7', '借款', '3', '/user/toBorrowMoney', '1', '2');
INSERT INTO `bank_resource` VALUES ('8', '还款', '3', '/bankLoans/toRepaymentShow', '1', '2');
INSERT INTO `bank_resource` VALUES ('9', '修改银行卡密码', '3', '/bankCard/toUpdatePassword', '1', '2');
INSERT INTO `bank_resource` VALUES ('10', '充值', '3', '/user/toUpdateBalance', '1', '2');
INSERT INTO `bank_resource` VALUES ('11', '我的信誉积分', '1', '/bankCard/toShowReputationValue', '1', '2');
INSERT INTO `bank_resource` VALUES ('12', '普通积分', '1', '/bankCard/toShowIntegral', '1', '2');
INSERT INTO `bank_resource` VALUES ('13', '审核', '1', '-', '1', '3');
INSERT INTO `bank_resource` VALUES ('14', '银行卡申请审核', '13', '/bankCard/toUpdateStatusShow', '1', '3');
INSERT INTO `bank_resource` VALUES ('15', '借款审核', '13', '/bankLoans/toUpdateStatusShow', '1', '3');
INSERT INTO `bank_resource` VALUES ('16', '查看用户所有信息', '1', '/user/toShow', '1', '3');
INSERT INTO `bank_resource` VALUES ('17', '商品', '1', '/product/toShow', '1', '3');
