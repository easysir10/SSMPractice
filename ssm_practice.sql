/*
 Navicat Premium Data Transfer

 Source Server         : 16206110
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : ssm_practice

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 09/07/2020 12:03:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_vote
-- ----------------------------
DROP TABLE IF EXISTS `user_vote`;
CREATE TABLE `user_vote`  (
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `vote_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_vote_results` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `vote_id`(`vote_id`) USING BTREE,
  CONSTRAINT `user_vote_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `user_vote_ibfk_2` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote`  (
  `vote_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '投票id',
  `vote_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标题',
  `vote_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '描述',
  `vote_type` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '投票类型：单项投票，多项投票',
  PRIMARY KEY (`vote_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vote
-- ----------------------------
INSERT INTO `vote` VALUES ('1', '谁是最帅的人', '选出你认为最帅的人', '单项投票');
INSERT INTO `vote` VALUES ('2', '谁是最丑的人', '选出你认为最丑的人', '单项投票');
INSERT INTO `vote` VALUES ('3', '谁是友好的人', '选人你认为友好的人', '多项投票');
INSERT INTO `vote` VALUES ('4b33f245c5db47b0b3f5103aea491d01', '胡歌帅吗', '你认为胡歌帅吗', '单项投票');

-- ----------------------------
-- Table structure for vote_item
-- ----------------------------
DROP TABLE IF EXISTS `vote_item`;
CREATE TABLE `vote_item`  (
  `item_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '投票项id',
  `item_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '描述',
  `item_remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '备注',
  `vote_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '投票id',
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `vote_item_ibfk_1`(`vote_id`) USING BTREE,
  CONSTRAINT `vote_item_ibfk_1` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
