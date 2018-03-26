/*
Navicat MySQL Data Transfer

Source Server         : shiro
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-03-26 18:28:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `permission_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`role_id`),
  KEY `FK_Reference_4` (`permission_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`permission_id`) REFERENCES `im_permission` (`ID`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`role_id`) REFERENCES `im_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '2', '1');
INSERT INTO `role_permission` VALUES ('3', '2', '2');
INSERT INTO `role_permission` VALUES ('4', '3', '1');
INSERT INTO `role_permission` VALUES ('5', '3', '2');
INSERT INTO `role_permission` VALUES ('6', '3', '3');
