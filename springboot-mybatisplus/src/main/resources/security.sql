drop database security;
create database security;
use security;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
 
 
 -- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
 
 -- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (2, 'editor', '$2a$10$JJ82/k3LicFc6kbCYP7ekO4MyepyQCL7w4vX1cgPzgy91nC2sALhi');
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$JJ82/k3LicFc6kbCYP7ekO4MyepyQCL7w4vX1cgPzgy91nC2sALhi');
COMMIT;


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` bigint(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) NOT NULL,
                        `code` varchar(255) NOT NULL,
                        `des` varchar(255),
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (2, 'ROLE_EDITOR','test','editor');
INSERT INTO `role` VALUES (1, 'ROLE_ADMIN','test','admin');
COMMIT;


-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `user_id` bigint(11) NOT NULL,
  `role_id` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 
-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `role_user` VALUES (1, 1);
INSERT INTO `role_user` VALUES (1, 2);
INSERT INTO `role_user` VALUES (2, 2);
COMMIT;

 
-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pid` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
 
-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (1, 'user/**', '用户列表', NULL, 0);
INSERT INTO `permission` VALUES (2, '/admin/**', '获取用户信息', NULL, 0);
COMMIT;
 
-- ----------------------------
-- Table structure for AccountState
-- ----------------------------
DROP TABLE IF EXISTS `account_state`;
CREATE TABLE `account_state` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(64) NOT NULL,
  `account_non_expired` int NOT NULL,
  `account_non_locked` int NOT NULL,
  `credentials_non_expired` int NOT NULL,
  `enabled` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
-- ----------------------------
-- Records of AccountState
-- ----------------------------
BEGIN;
INSERT INTO `account_state` VALUES (1,'1', 'admin', 1,1,1,1);
COMMIT;
 
 
-- ----------------------------
-- Table structure for RefreshToken
-- ----------------------------
DROP TABLE IF EXISTS `refresh_token`;
CREATE TABLE `refresh_token` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `usename` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 
