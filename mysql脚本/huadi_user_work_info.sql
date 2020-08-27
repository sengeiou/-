-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: huadi
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user_work_info`
--

DROP TABLE IF EXISTS `user_work_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_work_info` (
  `userid` char(19) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL COMMENT '学历',
  `departmentid` int(11) NOT NULL DEFAULT '0' COMMENT '所属分组，0表示是刚注册用户',
  `workinfo` varchar(255) DEFAULT NULL,
  `awardsinfo` varchar(255) DEFAULT NULL COMMENT '获奖信息',
  `punishmentinfo` varchar(255) DEFAULT NULL COMMENT '处分信息',
  `phone` varchar(45) DEFAULT NULL COMMENT '联系方式',
  `address` varchar(45) DEFAULT NULL COMMENT '联系地址',
  `statis` varchar(45) NOT NULL DEFAULT 'wait' COMMENT '信息审核状态\n等待状态为wait\n通过状态为pass\n未通过状态未defeat',
  `gmt_modified` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  `salary` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`),
  KEY `分组ID_idx` (`departmentid`),
  CONSTRAINT `分组ID` FOREIGN KEY (`departmentid`) REFERENCES `department` (`id`),
  CONSTRAINT `用户ID` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_work_info`
--

LOCK TABLES `user_work_info` WRITE;
/*!40000 ALTER TABLE `user_work_info` DISABLE KEYS */;
INSERT INTO `user_work_info` VALUES ('1290613836306558977','李青',20,'男','重庆邮电大学-软件工程学院',5,'暂无','暂无','暂无','13618334187','遵义虾子镇','pass','2020-08-10 08:38:02','2020-08-10 08:38:02',99999),('1290630416830562306','李青（微信）',21,'男','大学',7,'暂无','暂无','暂无','13618334187','规则遵义','pass','2020-08-12 02:09:14','2020-08-10 09:18:21',999099),('1293798556385173506','两只兵',20,'男','本科',7,'','','','15723417592','重庆','pass','2020-08-14 08:22:18','2020-08-13 06:46:54',100000),('1293809907786604545','陆拾叁',63,'男','良好',7,'正常','全部','无','17347898141','浦东','pass','2020-08-14 07:44:48','2020-08-13 07:25:22',8000),('1293811546639589378','张茗凯',21,'男','幼儿园',11,'幼儿园大班','幼儿园大红花三枚','上课迟到罚站五分钟','18290316418','小红花幼儿园','pass','2020-08-17 01:24:06','2020-08-13 07:32:43',8000),('1293812630942670850','赵航',21,'男','本科',2,'无','三好学生','无','13364006654','重庆','pass','2020-08-13 07:45:53','2020-08-13 07:35:33',99999),('1294192621438226433','ZFY',21,'男','本科',7,'在职','全勤奖','无','4008208820','什刹海','pass','2020-08-17 01:47:33','2020-08-14 08:46:03',5620),('1295171313744310273','hcj',21,'男','1',2,'1','1','1','1','1','pass','2020-08-17 01:43:36','2020-08-17 01:37:47',12222);
/*!40000 ALTER TABLE `user_work_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 12:12:36
