/*
Navicat MySQL Data Transfer

Source Server         : shiro
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-03-26 18:29:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `im_permission`
-- ----------------------------
DROP TABLE IF EXISTS `im_permission`;
CREATE TABLE `im_permission` (
  `ID` varchar(32) NOT NULL,
  `permission_name` varchar(100) DEFAULT NULL,
  `permisssion_code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of im_permission
-- ----------------------------
INSERT INTO `im_permission` VALUES ('1', '商品添加', 'product:add');
INSERT INTO `im_permission` VALUES ('2', '商品修改', 'product:update');
INSERT INTO `im_permission` VALUES ('3', '商品查询', 'product:list');
