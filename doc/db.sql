/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.14-log : Database - 2019springtest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`2019springtest` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `2019springtest`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `account` */

insert  into `account`(`id`,`name`,`nickname`,`create_time`,`update_time`) values 
(1,'zhang','张三','2019-08-08 20:43:39','2019-08-08 20:43:42'),
(2,'li','李四','2019-08-08 20:43:56','2019-08-08 20:43:59'),
(3,'wang','王五','2019-08-08 20:54:12','2019-08-08 20:54:12'),
(4,'test','测试','2019-08-08 21:25:02','2019-08-08 21:25:02');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`avatar`,`created`,`updated`) values 
(1,'zhang','111','张三','2019-08-08','2019-08-08'),
(2,'li','222','李四','2019-08-08','2019-08-08'),
(3,'wang','333','王五','2019-08-08','2019-08-08');

CREATE TABLE `excel_import_file` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `import_date` varchar(8) DEFAULT '' COMMENT '导入日期YYYMMDD',
  `import_file_name` varchar(50) DEFAULT '' COMMENT 'excel文件名',
  `import_count` int(11) DEFAULT '0' COMMENT '导入件数',
  `import_user` varchar(20) DEFAULT '' COMMENT '导入用户',
  `status` int(2) DEFAULT '0' COMMENT '文件状态',
  `import_type` varchar(10) DEFAULT '' COMMENT '导入用的技术',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='导入excel文件记录表'

CREATE TABLE `excel_import_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` int(11) DEFAULT NULL COMMENT '导入文件的id',
  `file_name` varchar(50) DEFAULT NULL COMMENT '导入的文件名字',
  `host_id` varchar(50) DEFAULT NULL COMMENT 'host名',
  `message` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` int(2) DEFAULT '0' COMMENT '记录状态',
  `import_type` varchar(10) DEFAULT '' COMMENT '导入用的技术',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='导入excel数据表'

