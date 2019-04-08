/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.56 : Database - courseware
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`courseware` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `courseware`;

/*Table structure for table `classroom` */

DROP TABLE IF EXISTS `classroom`;

CREATE TABLE `classroom` (
  `roomid` int(16) NOT NULL AUTO_INCREMENT COMMENT '默认编号',
  `roomnum` int(16) NOT NULL COMMENT '门牌号',
  `roomname` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '教室名称',
  `capacity` int(16) DEFAULT NULL COMMENT '容纳人数',
  `status` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态',
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  PRIMARY KEY (`roomid`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;

/*Data for the table `classroom` */

insert  into `classroom`(`roomid`,`roomnum`,`roomname`,`capacity`,`status`,`description`,`longitude`,`latitude`) values (0,103,'一教103',100,'0','公共教室',115.83344,28.76303),(1,101,'一教101',50,'0','公共教室',115.83344,28.76303),(2,102,'一教102',50,'0','公共教室',115.83344,28.76303),(3,104,'一教104',100,'0','公共教室',115.83344,28.76303),(4,105,'一教105',50,'0','公共教室',115.83344,28.76303),(5,201,'一教201',50,'0','公共教室',115.83344,28.76303),(6,202,'一教202',99,'0','公共教室',115.83344,28.76303),(7,203,'一教203',98,'1','公共教室',115.83344,28.76303),(8,204,'一教204',96,'0','公共教室',115.83344,28.76303),(9,205,'一教205',100,'0','公共教室',115.83344,28.76303),(10,301,'一教301',50,NULL,'公共教室',115.83344,28.76303),(11,302,'一教302',50,NULL,'公共教室',115.83344,28.76303),(12,303,'一教303',150,NULL,'公共教室',115.83344,28.76303),(13,304,'一教304',50,NULL,'公共教室',115.83344,28.76303),(14,305,'一教305',50,NULL,'公共教室',115.83344,28.76303),(15,101,'二教101',120,NULL,'公共教室',115.8382846287,28.7671364408),(16,102,'二教102',50,NULL,'公共教室',115.8382846287,28.7671364408),(17,103,'二教103',45,NULL,'公共教室',115.8382846287,28.7671364408),(18,104,'二教104',45,NULL,'公共教室',115.8382846287,28.7671364408),(19,105,'二教105',50,NULL,'公共教室',115.8382846287,28.7671364408),(20,106,'二教106',109,NULL,'公共教室',115.8382846287,28.7671364408),(21,107,'二教107',100,NULL,'公共教室',115.8382846287,28.7671364408),(22,108,'二教108',100,NULL,'公共教室',115.8382846287,28.7671364408),(23,109,'二教109',45,NULL,'公共教室',115.8382846287,28.7671364408),(24,110,'二教110',34,NULL,'公共教室',115.8382846287,28.7671364408),(25,111,'二教111',55,NULL,'公共教室',115.8382846287,28.7671364408),(26,112,'二教112',55,NULL,'公共教室',115.8382846287,28.7671364408),(27,113,'二教113',65,NULL,'公共教室',115.8382846287,28.7671364408),(28,114,'二教114',45,NULL,'公共教室',115.8382846287,28.7671364408),(29,201,'二教201',100,NULL,'公共教室',115.8382846287,28.7671364408),(30,202,'二教202',54,NULL,'公共教室',115.8382846287,28.7671364408),(31,203,'二教203',50,NULL,'公共教室',115.8382846287,28.7671364408),(32,301,'二教301',50,NULL,'公共教室',115.8382846287,28.7671364408),(33,302,'二教302',50,NULL,'公共教室',115.8382846287,28.7671364408),(34,303,'二教303',50,NULL,'公共教室',115.8382846287,28.7671364408),(35,401,'二教401',50,NULL,'公共教室',115.8382846287,28.7671364408),(36,402,'二教402',50,NULL,'公共教室',115.8382846287,28.7671364408),(37,403,'二教403',50,NULL,'公共教室',115.8382846287,28.7671364408),(38,501,'二教501',50,NULL,'公共教室',115.8382846287,28.7671364408),(39,502,'二教502',50,NULL,'公共教室',115.8382846287,28.7671364408),(40,503,'二教503',50,NULL,'公共教室',115.8382846287,28.7671364408),(41,101,'五教101',100,NULL,'公共教室',115.8389927319,28.7632849146),(42,102,'五教102',150,NULL,'公共教室',115.8389927319,28.7632849146),(43,103,'五教103',50,NULL,'公共教室',115.8389927319,28.7632849146),(44,104,'五教104',56,NULL,'公共教室',115.8389927319,28.7632849146),(45,105,'五教105',55,NULL,'公共教室',115.8389927319,28.7632849146),(46,201,'五教201',100,NULL,'公共教室',115.8389927319,28.7632849146),(47,202,'五教202',45,NULL,'公共教室',115.8389927319,28.7632849146),(48,203,'五教203',55,NULL,'公共教室',115.8389927319,28.7632849146),(49,301,'五教301',150,NULL,'公共教室',115.8389927319,28.7632849146),(50,302,'五教302',100,NULL,'公共教室',115.8389927319,28.7632849146),(51,303,'五教303',45,NULL,'公共教室',115.8389927319,28.7632849146),(52,401,'五教401',55,NULL,'公共教室',115.8389927319,28.7632849146),(53,402,'五教402',65,NULL,'公共教室',115.8389927319,28.7632849146),(54,310,'一教',100,'1','java实验课',NULL,NULL),(55,310,'一教',100,'0','java实验课',NULL,NULL),(56,310,'二教310',100,'0','',NULL,NULL),(57,201,'五教201',100,'0','',NULL,NULL),(58,201,'五教201',100,'0','',NULL,NULL),(59,201,'五教201',100,'0','',NULL,NULL);

/*Table structure for table `counter` */

DROP TABLE IF EXISTS `counter`;

CREATE TABLE `counter` (
  `visitcount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `counter` */

insert  into `counter`(`visitcount`) values (167);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `courseid` int(16) NOT NULL AUTO_INCREMENT COMMENT '课程号',
  `coursename` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '课程名称',
  `roomid` int(16) DEFAULT NULL COMMENT '教室编号',
  `reservationistid` int(16) DEFAULT NULL COMMENT '讲授教师',
  `numberofparticipants` int(16) DEFAULT NULL COMMENT '上课人数',
  `starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `reservationtime` datetime DEFAULT NULL COMMENT '预定时间',
  `canceledtime` datetime DEFAULT NULL COMMENT '撤销时间',
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `status` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态',
  `canceledreason` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '撤销原因',
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `course` */

insert  into `course`(`courseid`,`coursename`,`roomid`,`reservationistid`,`numberofparticipants`,`starttime`,`endtime`,`reservationtime`,`canceledtime`,`description`,`status`,`canceledreason`) values (0,'数据结构',44,5,54,'2017-11-27 08:00:00','2017-11-27 09:45:00','2015-01-10 23:02:39','2017-08-23 14:48:40',NULL,'0','aaaaaaaaaaaaaaa'),(1,'Java程序设计',45,8,54,'2017-11-27 10:15:00','2017-11-27 12:00:00','2015-01-17 23:04:18','2015-01-11 01:06:20',NULL,'0',NULL),(2,'离散数学',46,8,45,'2017-11-28 08:00:00','2017-11-28 09:45:00','2015-01-10 23:06:33','2015-01-11 01:01:42','','0',NULL),(3,'高等数学',47,6,45,'2017-11-28 10:15:00','2017-11-28 12:00:00','2015-01-10 23:26:26','2017-08-23 14:49:06','','0','ppppppppppppppppppppp'),(4,'线性代数',6,8,43,'2017-11-27 16:15:00','2017-11-27 18:00:00','2015-01-10 23:44:41',NULL,'','0',NULL),(5,'线性代数',10,5,43,'2017-11-30 10:15:00','2017-11-30 12:00:00','2015-01-10 23:56:20',NULL,'','0',NULL),(6,'线性代数',9,8,54,'2017-12-01 16:15:00','2017-12-01 18:00:00','2015-01-11 16:35:11',NULL,'','0',NULL),(7,'高等数学',5,7,45,'2017-12-01 14:00:00','2017-12-01 15:45:00','2015-01-11 16:40:50',NULL,'','0',NULL),(8,'大学语文',6,8,45,'2017-11-28 14:00:00','2017-11-28 15:45:00','2015-01-11 16:42:09','2015-01-12 11:44:57','','0',NULL),(9,'大学语文',8,9,45,'2017-12-01 08:00:00','2017-12-01 09:45:00','2015-01-11 16:42:51',NULL,'','0',NULL),(10,'C语言',7,6,45,'2017-11-27 14:00:00','2017-11-27 15:45:00','2015-01-11 16:44:35',NULL,'','0',NULL),(11,'C语言',18,5,34,'2017-11-29 08:00:00','2017-11-29 09:45:00','2015-01-11 16:57:56','2015-01-11 16:59:57','','0',NULL),(12,'大学英语',19,8,43,'2017-11-30 08:00:00','2017-11-30 09:45:00','2015-01-11 16:59:49',NULL,'','0',NULL),(13,'大学英语',20,6,46,'2017-11-30 10:15:00','2017-11-30 12:00:00','2015-01-12 11:49:17','2015-01-12 11:49:37','','1','11'),(14,'计算机导论',22,6,66,'2017-11-29 10:45:00','2017-11-29 12:00:00','2015-01-14 14:44:07',NULL,'','0',NULL),(15,'计算机导论',25,7,34,'2017-12-02 08:00:00','2017-12-02 09:45:00','2017-08-16 21:23:45',NULL,'','0',NULL),(16,'数据库系统概论',26,8,46,'2017-11-30 16:15:00','2017-11-30 18:00:00','2017-08-21 14:28:36',NULL,'','0',NULL),(17,'数字逻辑',27,5,65,'2017-11-29 16:15:00','2017-11-29 18:00:00','2017-11-18 21:04:08',NULL,'','0',NULL),(18,'数字逻辑',24,6,55,'2017-11-29 08:00:00','2017-11-29 09:45:00','2017-11-19 10:08:02',NULL,'','0',NULL),(19,'数字逻辑实验课',48,9,44,'2017-12-02 10:15:00','2017-12-02 12:00:00','2017-11-19 10:11:49',NULL,'','0',NULL),(21,'Java实验课',44,9,54,'2017-11-26 08:00:00','2017-11-26 09:45:00','2017-11-19 17:54:41',NULL,'','0',NULL),(22,'Java实验课',46,8,54,'2017-12-03 14:00:00','2017-12-03 15:45:00','2017-11-19 19:06:58',NULL,'','0',NULL),(23,'离散数学',5,6,56,'2017-11-30 14:00:00','2017-11-30 15:45:00','2017-11-22 17:20:25',NULL,'','0',NULL),(24,'数字逻辑实验课',8,5,54,'2017-12-01 10:15:00','2017-12-01 12:00:00','2017-11-19 10:12:53',NULL,'','0',NULL),(25,'数据库系统概论',52,2,55,'2017-12-01 08:00:00','2017-12-01 09:45:00','2017-11-30 11:25:33',NULL,'','0',NULL),(26,'西方经济学',0,2,34,'2017-12-03 14:00:00','2017-12-03 15:45:00','2017-12-03 13:50:31',NULL,'','0',NULL),(27,'西方经济学',0,2,33,'2017-12-04 08:56:24','2017-12-04 12:56:28','2017-12-04 08:56:44',NULL,'','0',NULL),(28,'数据库系统概论',0,2,33,'2017-12-05 08:01:58','2017-12-05 09:01:53','2017-12-04 09:02:27',NULL,'','0',NULL),(29,'数据结构',1,2,21,'2017-12-09 16:01:29','2017-12-09 18:01:36','2017-12-06 16:02:30',NULL,'数据结构课程','0',NULL),(30,'离散数学',0,2,23,'2017-12-10 16:02:49','2017-12-10 18:02:51','2017-12-06 16:03:22',NULL,'基础课程','0',NULL);

/*Table structure for table `courseparticipants` */

DROP TABLE IF EXISTS `courseparticipants`;

CREATE TABLE `courseparticipants` (
  `courseid` int(16) NOT NULL COMMENT '课程号',
  `personid` int(16) DEFAULT NULL COMMENT '人员编号',
  `status` int(16) DEFAULT '0' COMMENT '状态：请假或缺到'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `courseparticipants` */

insert  into `courseparticipants`(`courseid`,`personid`,`status`) values (25,3,0),(25,4,0),(25,5,2),(25,6,1),(26,3,0),(26,4,0),(26,5,0),(26,6,0),(27,3,0),(27,5,0),(28,3,2),(28,4,0),(28,5,0),(29,3,2),(29,4,0),(29,5,0),(29,6,0),(30,3,0),(30,4,0),(30,5,0),(30,6,0);

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `departmentid` int(16) NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `departmentname` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`departmentid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

/*Data for the table `department` */

insert  into `department`(`departmentid`,`departmentname`) values (1,'1609'),(2,'1610'),(3,'1611'),(4,'1710'),(5,'1612'),(14,'1604'),(15,'1602'),(16,'1603'),(17,'1605'),(18,'1606'),(19,'1606'),(20,'1609'),(21,'1607'),(22,'1605'),(23,'1609');

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `personid` int(16) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `personname` varchar(14) CHARACTER SET utf8 DEFAULT NULL COMMENT '姓名',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名(学号)',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `status` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态：审核通过与否',
  `departmentid` int(16) DEFAULT NULL COMMENT '院系',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `role` varchar(12) CHARACTER SET utf8 DEFAULT NULL COMMENT '身份',
  PRIMARY KEY (`personid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `person` */

insert  into `person`(`personid`,`personname`,`username`,`phone`,`email`,`status`,`departmentid`,`password`,`role`) values (0,'康咸鱼','0001','13671075406','583672089@qq.com','1',NULL,'C4CA4238A0B923820DCC509A6F75849B','1'),(1,'黄强大佬','0002','13671075406','583672089@qq.com','1',NULL,'C4CA4238A0B923820DCC509A6F75849B','2'),(2,'罗亚丽','0003','13245345396','583672089@qq.com','1',NULL,'C4CA4238A0B923820DCC509A6F75849B','3'),(3,'毛大佬','0004','13302312427','583672089@qq.com','1',1,'C4CA4238A0B923820DCC509A6F75849B','4'),(4,'王五','0005','13454765757','583672089@qq.com','1',2,'C4CA4238A0B923820DCC509A6F75849B','4'),(5,'赵六','0006','13567797878','kjycool@vip.qq.com','1',3,'C4CA4238A0B923820DCC509A6F75849B','4'),(6,'冯七','0007','13646787545','kjycool@vip.qq.com','1',4,'C4CA4238A0B923820DCC509A6F75849B','4');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
