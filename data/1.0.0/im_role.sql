/*
Navicat MySQL Data Transfer

Source Server         : shiro
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-03-26 18:28:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `im_role`
-- ----------------------------
DROP TABLE IF EXISTS `im_role`;
CREATE TABLE `im_role` (
  `ID` varchar(32) NOT NULL,
  `ROLE` varchar(100) DEFAULT NULL,
  `permission_code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of im_role
-- ----------------------------
INSERT INTO `im_role` VALUES ('1', '1号管理员', 'admin1');
INSERT INTO `im_role` VALUES ('2', '2号管理员', 'admin2');
INSERT INTO `im_role` VALUES ('3', '3号管理员', 'admin3');
