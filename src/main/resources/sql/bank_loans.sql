/*
Navicat MySQL Data Transfer

Source Server         : hmm
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : lianxi

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-11 18:01:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bank_loans
-- ----------------------------
DROP TABLE IF EXISTS `bank_loans`;
CREATE TABLE `bank_loans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_card_id` int(11) NOT NULL,
  `pay_money_all` double(11,0) DEFAULT NULL COMMENT '总待还金额',
  `pay_money_month` double(11,0) DEFAULT NULL COMMENT '本月代还金额',
  `pay_month_number` int(11) NOT NULL DEFAULT '0' COMMENT '代还月数',
  `status` int(11) DEFAULT NULL COMMENT '17为正常，19为冻结，16待审核；18，审核未通过',
  `is_del` int(11) DEFAULT NULL COMMENT '1在，2无',
  `repayment_time` datetime DEFAULT NULL COMMENT '还款时间',
  `type` int(11) DEFAULT NULL COMMENT '用于判断减少信誉积分：1：已减少，0：没',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_loans
-- ----------------------------
