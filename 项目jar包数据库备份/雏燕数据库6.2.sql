/*
 Navicat Premium Data Transfer

 Source Server         : VPC_cydb
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 175.24.50.253:3306
 Source Schema         : dbDemo

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 02/06/2020 21:04:17
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
  CONSTRAINT `FKcdveik90g4pvk7m249scu73pg` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`cust_id`),
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
  `food_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `food_num` int(11) DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  `picurl` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `price` float DEFAULT NULL,
  `restaurant_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `window_intro` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`food_id`),
  KEY `FKm9xrxt95wwp1r2s7andom1l1c` (`restaurant_id`),
  CONSTRAINT `FKm9xrxt95wwp1r2s7andom1l1c` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`rest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of food
-- ----------------------------
BEGIN;
INSERT INTO `food` VALUES (53, '新疆拌面', 10, '', 0, NULL, 12, 3, '正常', '面档');
INSERT INTO `food` VALUES (55, '糖醋里脊', 100, '超好吃！', 0, '', 12, 1, '打折', '家常菜');
INSERT INTO `food` VALUES (56, '宫保鸡丁', 50, '', 0, '', 10, 1, '正常', '盖饭');
INSERT INTO `food` VALUES (57, '宫保鸡丁', 50, '', 0, '', 10, 1, '正常', '盖饭');
INSERT INTO `food` VALUES (66, '奶茶三兄弟', 3, '奶茶～', 0, 'http://localhost:8080/uploadFile/鸣人.jpg', 8.5, 1, '正常', '奶茶店');
INSERT INTO `food` VALUES (69, '担担面', 10, '好吃的担担面', 0, 'http://175.24.50.253:8080/uploadFile/担担面.jpg', 8, 2, '正常', '川味美食');
INSERT INTO `food` VALUES (70, '精品牛排', 5, '选自战斧牛排，精品中的精品', 0, 'http://175.24.50.253:8080/uploadFile/0877.jpg_wh300_72.jpg', 158, 1, '正常', '1');
INSERT INTO `food` VALUES (71, '浪漫草莓', 4, '一口回到初恋', 0, 'http://175.24.50.253:8080/uploadFile/300_8_110.jpg', 60, 1, '正常', '3');
INSERT INTO `food` VALUES (72, '辽参', 3, '滋补', 0, 'http://175.24.50.253:8080/uploadFile/1747.jpg_wh300_12.jpg', 136, 1, '正常', '5');
INSERT INTO `food` VALUES (73, '爆辣火锅', 20, '辣的开心，辣的过瘾', 0, 'http://175.24.50.253:8080/uploadFile/5338.jpg_wh300_77.jpg', 200, 3, '正常', '1');
INSERT INTO `food` VALUES (74, '口味龙虾', 1, '正宗极品小龙虾', 0, 'http://175.24.50.253:8080/uploadFile/1981.jpg_wh300_47.jpg', 328, 3, '正常', '2');
INSERT INTO `food` VALUES (97, '牛肉水饺', 0, NULL, 0, NULL, 14, 2, NULL, NULL);
INSERT INTO `food` VALUES (104, '红烧肉盖饭', 100, '红烧肉', 0, '', 12, 1, '正常', '盖饭');
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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of food_specification
-- ----------------------------
BEGIN;
INSERT INTO `food_specification` VALUES (35, 53, '不加洋葱', 10, 0);
INSERT INTO `food_specification` VALUES (36, 53, '辣条', 100, 1);
INSERT INTO `food_specification` VALUES (37, 53, '加肉', 5, 2);
INSERT INTO `food_specification` VALUES (38, 66, '珍珠', 10, 2);
INSERT INTO `food_specification` VALUES (39, 66, '布丁', 5, 1);
INSERT INTO `food_specification` VALUES (40, 66, '仙草', 6, 2);
INSERT INTO `food_specification` VALUES (47, 69, '大葱', 100, 2);
INSERT INTO `food_specification` VALUES (48, 69, '肉末', 100, 2);
INSERT INTO `food_specification` VALUES (49, 70, '黑椒酱', 100, 1);
INSERT INTO `food_specification` VALUES (50, 71, '草莓', 10000, 1);
INSERT INTO `food_specification` VALUES (51, 72, '燕麦', 100, 2);
INSERT INTO `food_specification` VALUES (52, 73, '金针菇', 20, 5);
INSERT INTO `food_specification` VALUES (53, 74, '香菜', 100, 2);
COMMIT;

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `label_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of label
-- ----------------------------
BEGIN;
INSERT INTO `label` VALUES (68, '饺子');
INSERT INTO `label` VALUES (69, '面食');
INSERT INTO `label` VALUES (74, '蔬菜');
INSERT INTO `label` VALUES (75, '西北');
INSERT INTO `label` VALUES (76, '甜品');
INSERT INTO `label` VALUES (77, '西餐');
INSERT INTO `label` VALUES (78, '烧烤');
INSERT INTO `label` VALUES (79, '素食');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of restaurant
-- ----------------------------
BEGIN;
INSERT INTO `restaurant` VALUES (1, '学生食堂', 1, '盖饭，西餐，水煮鱼，手抓饼', '初客牛排');
INSERT INTO `restaurant` VALUES (2, '教工食堂', 4, '美味的火锅,一块钱四个，嘿嘿！', '旋转火锅');
INSERT INTO `restaurant` VALUES (3, '学生食堂', 2, '清真美食！', '清真餐厅');
INSERT INTO `restaurant` VALUES (4, '教工食堂', 5, '聚餐必去!', '教五中餐厅');
INSERT INTO `restaurant` VALUES (5, NULL, 0, NULL, '233');
COMMIT;

-- ----------------------------
-- Table structure for sys_food_label
-- ----------------------------
DROP TABLE IF EXISTS `sys_food_label`;
CREATE TABLE `sys_food_label` (
  `label_id` bigint(20) NOT NULL,
  `food_id` bigint(20) NOT NULL,
  PRIMARY KEY (`label_id`,`food_id`),
  KEY `FKt1hasrx4amqwad4mx3gni0wwr` (`food_id`),
  CONSTRAINT `FK1yg7cb4wgsjf7b37auk4bp826` FOREIGN KEY (`label_id`) REFERENCES `label` (`label_id`),
  CONSTRAINT `FK8p4wajk1telt2ihpst7wlogr0` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_food_label
-- ----------------------------
BEGIN;
INSERT INTO `sys_food_label` VALUES (76, 66);
INSERT INTO `sys_food_label` VALUES (77, 66);
INSERT INTO `sys_food_label` VALUES (69, 69);
INSERT INTO `sys_food_label` VALUES (75, 69);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
