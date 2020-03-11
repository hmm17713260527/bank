/*
Navicat MySQL Data Transfer

Source Server         : hmm
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : lianxi

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-11 18:05:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_data
-- ----------------------------
DROP TABLE IF EXISTS `base_data`;
CREATE TABLE `base_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '父级id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `is_del` int(11) DEFAULT NULL COMMENT '1:展示 2:伪删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_data
-- ----------------------------
INSERT INTO `base_data` VALUES ('1', '0', '借款金额', '1');
INSERT INTO `base_data` VALUES ('2', '1', '30000', '1');
INSERT INTO `base_data` VALUES ('3', '1', '50000', '1');
INSERT INTO `base_data` VALUES ('4', '1', '100000', '1');
INSERT INTO `base_data` VALUES ('5', '0', '分期限定', '1');
INSERT INTO `base_data` VALUES ('6', '5', '1', '1');
INSERT INTO `base_data` VALUES ('7', '5', '3', '1');
INSERT INTO `base_data` VALUES ('8', '5', '6', '1');
INSERT INTO `base_data` VALUES ('9', '5', '12', '1');
INSERT INTO `base_data` VALUES ('10', '0', '银行卡类型', '1');
INSERT INTO `base_data` VALUES ('11', '10', '工商银行', '1');
INSERT INTO `base_data` VALUES ('12', '10', '建设银行', '1');
INSERT INTO `base_data` VALUES ('13', '10', '农行银行', '1');
INSERT INTO `base_data` VALUES ('14', '10', '中国农业银行', '1');
INSERT INTO `base_data` VALUES ('15', '0', '审核', '1');
INSERT INTO `base_data` VALUES ('16', '15', '未审核', '1');
INSERT INTO `base_data` VALUES ('17', '15', '审核通过', '1');
INSERT INTO `base_data` VALUES ('18', '15', '审核未通过', '1');
INSERT INTO `base_data` VALUES ('19', '15', '冻结', '1');
