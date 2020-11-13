/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : dbDemo

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 11/07/2020 21:15:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `bill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_num` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `pay_status` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `pay_time` datetime(6) DEFAULT NULL,
  `take_time` datetime(6) DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `FKj65fjs1gspbgl4i7yu1y4fsow` (`customer_id`),
  CONSTRAINT `FKj65fjs1gspbgl4i7yu1y4fsow` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for bill_order
-- ----------------------------
DROP TABLE IF EXISTS `bill_order`;
CREATE TABLE `bill_order` (
  `bill_order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_order_note` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `bill_order_price` float DEFAULT NULL,
  `spec_seq` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `bill_id` bigint(20) DEFAULT NULL,
  `food_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bill_order_id`),
  KEY `FKsccq6w3sh7k24nl7stn25qq4m` (`bill_id`),
  KEY `FKmuqso04502e5qbi5g3u6rsf57` (`food_id`),
  CONSTRAINT `FKmuqso04502e5qbi5g3u6rsf57` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`),
  CONSTRAINT `FKsccq6w3sh7k24nl7stn25qq4m` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `board_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `picurl` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `restaurant_id` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  KEY `FKqfc5hj369whkyijxb4bp35xy` (`restaurant_id`),
  CONSTRAINT `FKqfc5hj369whkyijxb4bp35xy` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`rest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `likes` int(11) DEFAULT NULL,
  `text` varchar(0) CHARACTER SET utf8 DEFAULT NULL,
  `time` datetime(6) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `food_id` bigint(20) DEFAULT NULL,
  `to_whom` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FKlwqielki359fs4py1a4iw2fdt` (`customer_id`),
  KEY `FKd98d2se7wf5px59h6g1rjf0dq` (`food_id`),
  KEY `FKt5ptcovpf2e3ckhm9d5tl66ni` (`to_whom`),
  KEY `FKde3rfu96lep00br5ov0mdieyt` (`parent_id`),
  CONSTRAINT `FKd98d2se7wf5px59h6g1rjf0dq` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`),
  CONSTRAINT `FKde3rfu96lep00br5ov0mdieyt` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`comment_id`),
  CONSTRAINT `FKlwqielki359fs4py1a4iw2fdt` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`cust_id`),
  CONSTRAINT `FKt5ptcovpf2e3ckhm9d5tl66ni` FOREIGN KEY (`to_whom`) REFERENCES `customer` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `cust_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_intro` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `hometown` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `identity` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `school` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `university` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `picurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `food_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `food_name` varchar(255) DEFAULT NULL,
  `food_num` int(11) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  `picurl` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `restaurant_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `window_intro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`food_id`),
  KEY `FKm9xrxt95wwp1r2s7andom1l1c` (`restaurant_id`),
  CONSTRAINT `FKm9xrxt95wwp1r2s7andom1l1c` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`rest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
BEGIN;
INSERT INTO `food` VALUES (31, '脆皮鸡饭', 0, NULL, 0, NULL, 5, 1, NULL, NULL);
INSERT INTO `food` VALUES (32, '脆皮鸡饭', 0, NULL, 0, NULL, 5, 2, NULL, NULL);
INSERT INTO `food` VALUES (42, '羊肉水饺', 0, NULL, 0, NULL, 14, 1, NULL, NULL);
INSERT INTO `food` VALUES (44, '意大利面', 0, NULL, 0, NULL, 5, 5, NULL, NULL);
INSERT INTO `food` VALUES (45, '羊肉水饺', 0, NULL, 0, NULL, 13, 5, NULL, NULL);
INSERT INTO `food` VALUES (46, '脆皮鸡饭', 0, NULL, 0, NULL, 5, NULL, NULL, NULL);
INSERT INTO `food` VALUES (47, '脆皮鸡饭', 0, NULL, 0, NULL, 5, 2, NULL, NULL);
INSERT INTO `food` VALUES (48, '脆皮鸡饭', 0, NULL, 0, NULL, 5, NULL, NULL, NULL);
INSERT INTO `food` VALUES (49, '牛肉水饺', 0, NULL, 0, NULL, 13, 7, NULL, NULL);
INSERT INTO `food` VALUES (50, '牛肉水饺', 0, NULL, 0, NULL, 14, NULL, NULL, NULL);
INSERT INTO `food` VALUES (51, '比萨', 1, 'this is pizza', 6, 'qwerty', 20, 8, '西餐', '1');
INSERT INTO `food` VALUES (52, '牛肉水饺', NULL, NULL, NULL, NULL, 14, 8, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for food_specification
-- ----------------------------
DROP TABLE IF EXISTS `food_specification`;
CREATE TABLE `food_specification` (
  `food_spec_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `food_id` bigint(20) DEFAULT NULL,
  `food_spec_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `food_spec_num` int(11) DEFAULT NULL,
  `food_spec_price` float DEFAULT NULL,
  PRIMARY KEY (`food_spec_id`),
  KEY `FK4p1dasrw8nymes6spb0ikrxm5` (`food_id`),
  CONSTRAINT `FK4p1dasrw8nymes6spb0ikrxm5` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of food_specification
-- ----------------------------
BEGIN;
INSERT INTO `food_specification` VALUES (28, 31, '辣椒', 0, 0);
INSERT INTO `food_specification` VALUES (29, 32, '辣椒', 0, 0);
INSERT INTO `food_specification` VALUES (30, 46, '辣椒', 0, 0);
INSERT INTO `food_specification` VALUES (31, 47, '辣椒', 0, 0);
INSERT INTO `food_specification` VALUES (32, 48, '辣椒', 0, 0);
INSERT INTO `food_specification` VALUES (33, 48, '生菜', 1, 3);
INSERT INTO `food_specification` VALUES (34, 48, '鸡蛋', 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `label_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of label
-- ----------------------------
BEGIN;
INSERT INTO `label` VALUES (9, '面食');
INSERT INTO `label` VALUES (10, '饺子');
INSERT INTO `label` VALUES (13, '西餐');
INSERT INTO `label` VALUES (14, '面食');
INSERT INTO `label` VALUES (15, '饺子');
INSERT INTO `label` VALUES (16, '饺子');
INSERT INTO `label` VALUES (17, '面食');
INSERT INTO `label` VALUES (18, '面食');
INSERT INTO `label` VALUES (19, '饺子');
COMMIT;

-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `mail_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `picurl` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `text` text CHARACTER SET utf8,
  `customer_id` bigint(20) DEFAULT NULL,
  `restaurant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`mail_id`),
  KEY `FK88338jjtu93ykermrobk04v1i` (`customer_id`),
  KEY `FK79guoq19rn1op2cym50na3jw0` (`restaurant_id`),
  CONSTRAINT `FK79guoq19rn1op2cym50na3jw0` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`rest_id`),
  CONSTRAINT `FK88338jjtu93ykermrobk04v1i` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `likes` int(11) DEFAULT NULL,
  `picurl` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `text` text CHARACTER SET utf8,
  `time` datetime(6) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FKioa7otrmcu3s30njygbgvortc` (`customer_id`),
  CONSTRAINT `FKioa7otrmcu3s30njygbgvortc` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for restaurant
-- ----------------------------
DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant` (
  `rest_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `building` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `floor` int(11) DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`rest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of restaurant
-- ----------------------------
BEGIN;
INSERT INTO `restaurant` VALUES (1, '1', 1, 'qwerty', '教工餐厅');
INSERT INTO `restaurant` VALUES (2, '1', 1, 'qwerty', '教工餐厅');
INSERT INTO `restaurant` VALUES (5, '7', 9, 'wiue', '学生餐厅');
INSERT INTO `restaurant` VALUES (6, '7', 9, 'wiue', '清真餐厅');
INSERT INTO `restaurant` VALUES (7, '2', 3, 'uibi', '学生餐厅');
INSERT INTO `restaurant` VALUES (8, '1', 3, 'cj ', '旋转火锅');
COMMIT;

-- ----------------------------
-- Table structure for sys_food_label
-- ----------------------------
DROP TABLE IF EXISTS `sys_food_label`;
CREATE TABLE `sys_food_label` (
  `label_id` bigint(20) NOT NULL,
  `food_id` bigint(20) NOT NULL,
  PRIMARY KEY (`label_id`,`food_id`),
  KEY `FKt1hasrx4amqwad4mx3gni0wwr` (`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_food_label
-- ----------------------------
BEGIN;
INSERT INTO `sys_food_label` VALUES (42, 9);
INSERT INTO `sys_food_label` VALUES (42, 10);
INSERT INTO `sys_food_label` VALUES (44, 13);
INSERT INTO `sys_food_label` VALUES (45, 14);
INSERT INTO `sys_food_label` VALUES (45, 15);
INSERT INTO `sys_food_label` VALUES (49, 16);
INSERT INTO `sys_food_label` VALUES (49, 17);
INSERT INTO `sys_food_label` VALUES (50, 18);
INSERT INTO `sys_food_label` VALUES (50, 19);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
