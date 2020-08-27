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
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `info` varchar(45) NOT NULL,
  `statis` varchar(45) NOT NULL DEFAULT 'wait',
  `gmt_modified` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  `creatuserid` char(19) NOT NULL COMMENT '项目创建人',
  `creatusername` varchar(45) NOT NULL,
  `membernum` int(11) NOT NULL DEFAULT '0' COMMENT '项目成员数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目记录信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'404测试项目','第2条测试数据','pass','2021-08-10 09:22:11','2021-08-10 09:22:11','1290613836306558977','李青',2),(2,'404测试项目','第3条测试数据','pass','2021-08-10 09:22:11','2021-08-10 09:22:11','1290613836306558977','李青',2),(3,'404测试项目','第4条测试数据','pass','2023-08-10 09:22:11','2023-08-10 09:22:11','1290613836306558977','李青',2),(4,'404测试项目','第5条测试数据','pass','2020-08-11 08:00:10','2020-08-10 09:22:11','1290613836306558977','李青',2),(10,'404测试项目','第一条测试数据','wait','2020-08-10 09:22:11','2020-08-10 09:22:11','1290613836306558977','李青',2),(11,'1','111','wait','2020-08-13 06:54:05','2020-08-13 06:54:05','1293798556385173506','两只兵',2),(12,'第二个项目','404测试','wait','2020-08-13 07:46:50','2020-08-13 07:46:50','1290613836306558977','李青',5),(13,'测试3 ','界面显示测试','wait','2020-08-14 08:36:42','2020-08-14 08:36:42','1293809907786604545','陆拾叁',1),(14,'xinji','adad','wait','2020-08-17 01:49:38','2020-08-17 01:49:38','1290613836306558977','李青',5);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 12:12:33
