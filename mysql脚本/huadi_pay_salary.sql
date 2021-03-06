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
-- Table structure for table `pay_salary`
--

DROP TABLE IF EXISTS `pay_salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pay_salary` (
  `id` char(19) NOT NULL,
  `userid` char(19) NOT NULL,
  `departmentid` int(11) NOT NULL,
  `salary` int(11) NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  `dealid` char(19) DEFAULT NULL,
  `dealname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `员工ID_idx` (`userid`),
  KEY `部门ID_idx` (`departmentid`),
  KEY `处理人ID_idx` (`dealid`),
  CONSTRAINT `员工ID` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `部门ID` FOREIGN KEY (`departmentid`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工资发放记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_salary`
--

LOCK TABLES `pay_salary` WRITE;
/*!40000 ALTER TABLE `pay_salary` DISABLE KEYS */;
INSERT INTO `pay_salary` VALUES ('1292746530398154753','1290613836306558977',5,110,'2020-08-10 08:56:06','2020-08-10 08:56:06','1290613836306558977','李青'),('1292746995353530370','1290613836306558977',5,9990,'2020-08-10 08:57:56','2020-08-10 08:57:56','1290613836306558977','员工'),('1293803501259911170','1293798556385173506',7,10000,'2020-08-13 06:56:07','2020-08-13 06:56:07','1293798556385173506','员工'),('1293805447949643778','1293798556385173506',7,1000,'2020-08-13 07:03:51','2020-08-13 07:03:51','1293798556385173506','员工'),('1293812486683815937','1290613836306558977',5,5162,'2020-08-13 07:31:49','2020-08-13 07:31:49','1293809907786604545','员工'),('1293812684516552706','1290613836306558977',5,80,'2020-08-13 07:32:37','2020-08-13 07:32:37','1293809907786604545','陆拾叁'),('1294182102664777730','1293809907786604545',7,6000,'2020-08-14 08:00:33','2020-08-14 08:00:33','1293809907786604545','员工'),('1294183129539448833','1290613836306558977',5,12340,'2020-08-14 08:04:38','2020-08-14 08:04:38','1290613836306558977','员工'),('1295175139385720834','1293809907786604545',7,500,'2020-08-17 01:46:31','2020-08-17 01:46:31','1290613836306558977','李青'),('1295175737728352258','1290630416830562306',7,99990,'2020-08-17 01:48:54','2020-08-17 01:48:54','1290613836306558977','员工');
/*!40000 ALTER TABLE `pay_salary` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 12:12:30
