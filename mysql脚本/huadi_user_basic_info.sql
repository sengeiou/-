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
-- Table structure for table `user_basic_info`
--

DROP TABLE IF EXISTS `user_basic_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_basic_info` (
  `userid` char(19) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `weight` varchar(45) DEFAULT NULL,
  `eyes` varchar(45) DEFAULT NULL COMMENT '视力',
  `nation` varchar(45) DEFAULT NULL COMMENT '民族',
  `identitycard` varchar(125) DEFAULT NULL COMMENT '身份证号',
  `ismarry` varchar(45) DEFAULT NULL COMMENT '婚姻状况',
  `bornplace` varchar(45) DEFAULT NULL COMMENT '户口所在地',
  `politicaloutlook` varchar(45) DEFAULT NULL COMMENT '政治面貌',
  `nowplace` varchar(45) DEFAULT NULL COMMENT '现居住地',
  `phone` varchar(45) DEFAULT NULL COMMENT '联系电话',
  `healthy` varchar(45) DEFAULT NULL COMMENT '健康状况',
  `emergencypeople` varchar(45) DEFAULT NULL COMMENT '紧急联系人',
  `relationship` varchar(45) DEFAULT NULL COMMENT '与本人关系',
  `contactphone` varchar(45) DEFAULT NULL COMMENT '紧急联系人电话',
  PRIMARY KEY (`userid`),
  CONSTRAINT `注册用户ID` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_basic_info`
--

LOCK TABLES `user_basic_info` WRITE;
/*!40000 ALTER TABLE `user_basic_info` DISABLE KEYS */;
INSERT INTO `user_basic_info` VALUES ('1290613836306558977','李青','男',20,180,'60','5','汉','xxxxxxxxxxxxxxxxx','未婚','四川','团员','贵州-遵义','13618334187','健康','无','无','12345678902'),('1290630416830562306','李青（微信）','男',20,180,'60','5','汉','XXXXXXXXXXXXXXXX','未婚','四川','团员','遵义','13618334187','健康','无','无','无'),('1293798556385173506','两只兵','男',20,175,'60','4.5','汉','500107199906025319','未婚','Japan','党员','成都','15723417592','健康','','',''),('1293809907786604545','张丰羽','男',21,176,'86','5.0','汉族','XXXXXXXXXXXXXXXXXX','未婚','西南','群众','重庆','17347898141','良好','无','妹关系','10086');
/*!40000 ALTER TABLE `user_basic_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 12:12:37
