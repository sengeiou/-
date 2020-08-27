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
-- Table structure for table `power_department`
--

DROP TABLE IF EXISTS `power_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `power_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `powerid` int(11) NOT NULL,
  `departmentid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `权限外键_idx` (`powerid`),
  KEY `部门外键_idx` (`departmentid`),
  CONSTRAINT `权限外键` FOREIGN KEY (`powerid`) REFERENCES `power` (`id`),
  CONSTRAINT `部门外键` FOREIGN KEY (`departmentid`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `power_department`
--

LOCK TABLES `power_department` WRITE;
/*!40000 ALTER TABLE `power_department` DISABLE KEYS */;
INSERT INTO `power_department` VALUES (101,1,1),(102,8,1),(103,2,1),(104,9,1),(105,10,1),(106,11,1),(107,7,1),(108,12,1),(109,5,2),(110,10,2),(111,12,2),(112,3,3),(113,7,3),(114,11,3),(115,10,5),(116,1,5),(117,2,5),(118,3,5),(119,5,5),(120,7,5),(121,8,5),(122,9,5),(123,11,5),(124,12,5),(125,1,7),(126,2,7),(127,3,7),(128,5,7),(129,7,7),(130,8,7),(131,9,7),(132,10,7),(133,11,7),(134,12,7),(135,7,11);
/*!40000 ALTER TABLE `power_department` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 12:12:34
