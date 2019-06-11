/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50173
 Source Host           : localhost:3306
 Source Schema         : chipreservation

 Target Server Type    : MySQL
 Target Server Version : 50173
 File Encoding         : 65001

 Date: 11/06/2019 13:05:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chips
-- ----------------------------
DROP TABLE IF EXISTS `chips`;
CREATE TABLE `chips`  (
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `amount` int(4) NOT NULL,
  PRIMARY KEY (`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of chips
-- ----------------------------
INSERT INTO `chips` VALUES ('a0001', 4);
INSERT INTO `chips` VALUES ('a0002', 99);
INSERT INTO `chips` VALUES ('b0001', 500);
INSERT INTO `chips` VALUES ('b0002', 98);
INSERT INTO `chips` VALUES ('b09-1281C', 7);
INSERT INTO `chips` VALUES ('b1201', 50);
INSERT INTO `chips` VALUES ('C09-11', 480);

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passwd` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `usertype` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES ('0120162416', '0120162416', '管理员', 1);
INSERT INTO `login` VALUES ('20160001', '11', '张三1', 0);
INSERT INTO `login` VALUES ('20160003', '20160003', '张流水', 0);
INSERT INTO `login` VALUES ('20160004', '20160004', '十多年', 0);
INSERT INTO `login` VALUES ('20160006', '20160006', 'sda11', 0);
INSERT INTO `login` VALUES ('20160008', '20160008', 'dsds', 0);
INSERT INTO `login` VALUES ('20163562', '20163562', '王', 0);

-- ----------------------------
-- Table structure for records
-- ----------------------------
DROP TABLE IF EXISTS `records`;
CREATE TABLE `records`  (
  `identifier` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `thedate` datetime NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(4) NULL DEFAULT NULL,
  PRIMARY KEY (`identifier`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of records
-- ----------------------------
INSERT INTO `records` VALUES ('t_20160001_20190605222947', '2019-06-05 22:29:47', '20160001', 'b0001', '预约', 7);
INSERT INTO `records` VALUES ('t_20160001_20190605222954', '2019-06-05 22:29:54', '20160001', 'b0002', '预约', 2);
INSERT INTO `records` VALUES ('t_20160001_20190605230537', '2019-06-05 23:05:37', '20160001', 'C09-11', '预约', 23);
INSERT INTO `records` VALUES ('t_20160001_20190605230859', '2019-06-05 23:08:59', '20160001', 'C09-11', '归还', 1);
INSERT INTO `records` VALUES ('t_20160001_20190605230947', '2019-06-05 23:09:47', '20160001', 'C09-11', '归还', 2);
INSERT INTO `records` VALUES ('t_20160001_20190606151303', '2019-06-06 15:13:03', '20160001', 'a0001', '预约', 3);
INSERT INTO `records` VALUES ('t_20160001_20190606151341', '2019-06-06 15:13:41', '20160001', 'a0001', '预约', 3);
INSERT INTO `records` VALUES ('t_20160001_20190606151400', '2019-06-06 15:14:00', '20160001', 'a0001', '归还', 6);
INSERT INTO `records` VALUES ('t_20160001_20190607180555', '2019-06-07 18:05:55', '20160001', 'a0002', '预约', 90);
INSERT INTO `records` VALUES ('t_20160001_20190607180815', '2019-06-07 18:08:15', '20160001', 'a0002', '归还', 90);
INSERT INTO `records` VALUES ('t_20160003_20190605211757', '2019-06-05 21:17:57', '20160003', 'a0001', '预约', 1);
INSERT INTO `records` VALUES ('t_20160003_20190605211926', '2019-06-05 21:19:26', '20160003', 'a0001', '预约', 12);
INSERT INTO `records` VALUES ('t_20160003_20190605212115', '2019-06-05 21:21:15', '20160003', 'b0001', '预约', 30);
INSERT INTO `records` VALUES ('t_20160003_20190605212146', '2019-06-05 21:21:46', '20160003', 'b0002', '预约', 1);
INSERT INTO `records` VALUES ('t_20160003_20190605212202', '2019-06-05 21:22:02', '20160003', 'b0001', '预约', 1);
INSERT INTO `records` VALUES ('t_20160003_20190605212455', '2019-06-05 21:24:55', '20160003', 'b0001', '归还', 1);
INSERT INTO `records` VALUES ('t_20160003_20190605212550', '2019-06-05 21:25:50', '20160003', 'b0001', '归还', 30);
INSERT INTO `records` VALUES ('t_20160003_20190605214812', '2019-06-05 21:48:12', '20160003', 'b0002', '归还', 1);
INSERT INTO `records` VALUES ('t_20160003_20190605214826', '2019-06-05 21:48:26', '20160003', 'C09-11', '预约', 1);
INSERT INTO `records` VALUES ('t_20160003_20190605214843', '2019-06-05 21:48:43', '20160003', 'C09-11', '归还', 1);
INSERT INTO `records` VALUES ('t_20160004_20190605234701', '2019-06-05 23:47:01', '20160004', 'b09-1281C', '预约', 3);
INSERT INTO `records` VALUES ('t_20160004_20190606085229', '2019-06-06 08:52:29', '20160004', 'b0001', '预约', 3);
INSERT INTO `records` VALUES ('t_20160004_20190606085238', '2019-06-06 08:52:38', '20160004', 'b0001', '归还', 1);
INSERT INTO `records` VALUES ('t_20160006_20190605233824', '2019-06-05 23:38:24', '20160006', 'a0002', '预约', 1);
INSERT INTO `records` VALUES ('t_20160006_20190605234033', '2019-06-05 23:40:33', '20160006', 'a0002', '归还', 1);
INSERT INTO `records` VALUES ('t_20160006_20190606090727', '2019-06-06 09:07:27', '20160006', 'a0001', '预约', 7);
INSERT INTO `records` VALUES ('t_20160006_20190606090741', '2019-06-06 09:07:41', '20160006', 'a0001', '归还', 2);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reservedamount` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `needreturn` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('20160003', 'a0001', '13', '13');
INSERT INTO `users` VALUES ('20160003', 'b0001', '0', '0');
INSERT INTO `users` VALUES ('20160003', 'b0002', '0', '0');
INSERT INTO `users` VALUES ('20160003', 'C09-11', '0', '0');
INSERT INTO `users` VALUES ('20160001', 'b0001', '7', '7');
INSERT INTO `users` VALUES ('20160001', 'b0002', '2', '2');
INSERT INTO `users` VALUES ('20160001', 'C09-11', '20', '20');
INSERT INTO `users` VALUES ('20160006', 'a0002', '0', '0');
INSERT INTO `users` VALUES ('20160004', 'b09-1281C', '3', '3');
INSERT INTO `users` VALUES ('20160004', 'b0001', '2', '2');
INSERT INTO `users` VALUES ('20160006', 'a0001', '5', '5');
INSERT INTO `users` VALUES ('20160001', 'a0001', '0', '0');
INSERT INTO `users` VALUES ('20160001', 'a0002', '0', '0');

SET FOREIGN_KEY_CHECKS = 1;
