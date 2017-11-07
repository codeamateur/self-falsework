/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.6.25-log : Database - sz_mid
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sz_mid` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sz_mid`;

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `remark` varchar(200) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_dept` */

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `level` int(1) DEFAULT NULL COMMENT '按钮级别【0：菜单一级；1：菜单二级；2：页面级】',
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `permission` varchar(50) DEFAULT NULL COMMENT '格式：project:create',
  `position` int(10) DEFAULT NULL COMMENT '排序',
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`parent_id`,`level`,`name`,`url`,`permission`,`position`,`status`) values (1,0,1,'系统管理',NULL,'system',1,1),(2,1,2,'菜单管理','menu/toMenuList','menu:toMenuList',1,1),(3,1,2,'用户管理','user/toList','user:toList',2,1),(4,1,2,'角色管理','role/toList','role:toList',3,1),(5,3,3,'添加按钮','user/toAdd','user:toAdd',0,1),(6,3,3,'编辑按钮','user/tomodify','user:tomodify',0,1),(7,3,3,'重置密码按钮','user/resetPassword','user:resetPassword',0,1),(8,4,3,'添加角色按钮','role/tosysRoleAdd','role:tosysRoleAdd',0,1),(9,4,3,'修改按钮','role/toUpdateRole','role:toUpdateRole',0,1),(10,4,3,'状态修改按钮','role/updateRoleStatus','role:updateRoleStatus',0,1);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`code`,`name`,`remark`,`status`) values (1,'01','系统管理员',NULL,1);

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`id`,`role_id`,`permission_id`,`status`) values (1,1,1,1),(2,1,2,1),(3,1,3,1),(4,1,4,1),(5,1,5,1),(6,1,6,1),(7,1,7,1),(8,1,8,1),(9,1,9,1),(10,1,10,1);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dep_id` bigint(20) DEFAULT NULL,
  `login_name` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `password` varchar(100) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  `csid` bigint(30) DEFAULT NULL COMMENT '客服编号',
  `extension_number` bigint(20) DEFAULT NULL COMMENT '分机号',
  `status` tinyint(1) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `last_login_ip` varchar(45) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`dep_id`,`login_name`,`name`,`password`,`sex`,`email`,`phone`,`mobile`,`address`,`locked`,`csid`,`extension_number`,`status`,`register_time`,`last_login_ip`,`last_login_time`) values (1,NULL,'admin','ADMIN','08428467285068b426356b9b0d0ae1e80378d9137d5e559e5f8377dbd6dde29f',NULL,'admin@qq.com','110','120','test',0,NULL,NULL,1,'2017-05-04 17:05:32',NULL,NULL),(2,NULL,'test','TEST','08428467285068b426356b9b0d0ae1e80378d9137d5e559e5f8377dbd6dde29f',NULL,'test@qq.com','011','021','test',0,NULL,NULL,1,'2017-05-04 17:10:12',NULL,NULL),(3,NULL,'a','a','08428467285068b426356b9b0d0ae1e80378d9137d5e559e5f8377dbd6dde29f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,NULL,'b','b','08428467285068b426356b9b0d0ae1e80378d9137d5e559e5f8377dbd6dde29f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,'c','c','08428467285068b426356b9b0d0ae1e80378d9137d5e559e5f8377dbd6dde29f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,'d','d','08428467285068b426356b9b0d0ae1e80378d9137d5e559e5f8377dbd6dde29f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,'e','e','08428467285068b426356b9b0d0ae1e80378d9137d5e559e5f8377dbd6dde29f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,'f','f','08428467285068b426356b9b0d0ae1e80378d9137d5e559e5f8377dbd6dde29f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,'g','g','08428467285068b426356b9b0d0ae1e80378d9137d5e559e5f8377dbd6dde29f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,NULL,'h','h','08428467285068b426356b9b0d0ae1e80378d9137d5e559e5f8377dbd6dde29f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,NULL,'i','i','08428467285068b426356b9b0d0ae1e80378d9137d5e559e5f8377dbd6dde29f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,NULL,'test2','test2','cdf4a007e2b02a0c49fc9b7ccfbb8a10c644f635e1765dcf2a7ab794ddc7edac',NULL,'aa@qq.com','12','11','11',0,NULL,NULL,1,'2017-05-04 18:06:47',NULL,NULL),(13,NULL,'test3','test3','cdf4a007e2b02a0c49fc9b7ccfbb8a10c644f635e1765dcf2a7ab794ddc7edac',NULL,'33@qq.com','22','11','222',0,NULL,NULL,1,'2017-05-04 18:09:28',NULL,NULL);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`,`status`) values (1,1,1,1),(2,2,1,1),(3,3,1,1),(4,4,1,1),(5,5,1,1),(6,6,1,1),(7,7,1,1),(8,8,1,1),(9,9,1,1),(10,10,1,1),(11,11,1,1),(12,12,1,1),(13,13,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
