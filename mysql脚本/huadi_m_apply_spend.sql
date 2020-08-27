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
-- Table structure for table `m_apply_spend`
--

DROP TABLE IF EXISTS `m_apply_spend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `m_apply_spend` (
  `id` char(19) NOT NULL,
  `money` int(11) NOT NULL COMMENT '报销金额',
  `applicationid` char(19) NOT NULL COMMENT '申请人ID',
  `dealuserid` char(19) DEFAULT NULL COMMENT '处理人ID',
  `statis` varchar(45) NOT NULL DEFAULT 'wait' COMMENT '处理状态',
  `gmt_modified` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  `detail` varchar(255) DEFAULT NULL COMMENT '申请详细原因',
  `type` varchar(45) NOT NULL COMMENT '申报类型',
  `sourceurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `申请人id_idx` (`applicationid`),
  KEY `处理人id_idx` (`dealuserid`),
  CONSTRAINT `处理人id` FOREIGN KEY (`dealuserid`) REFERENCES `user` (`id`),
  CONSTRAINT `申请人id` FOREIGN KEY (`applicationid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='报销申请表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_apply_spend`
--

LOCK TABLES `m_apply_spend` WRITE;
/*!40000 ALTER TABLE `m_apply_spend` DISABLE KEYS */;
INSERT INTO `m_apply_spend` VALUES ('1292745702564179970',80,'1290613836306558977','1293809907786604545','pass','2020-08-10 08:52:48','2020-08-10 08:52:48','买菜','饮食类','https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/10/1ec4eb5c-32f7-4ef9-b6f3-5370595d8ba6.JPEG'),('1292746311635841026',110,'1290613836306558977','1290613836306558977','pass','2020-08-10 08:55:13','2020-08-10 08:55:13','打车费','交通类','https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/10/8bf9060b-ff7c-4359-b660-1bddb01f4806.JPEG'),('1292752810445647873',120,'1290630416830562306','1293809907786604545','defeat','2020-08-10 09:21:03','2020-08-10 09:21:03','写代码','办公类',''),('1294180977714696194',500,'1293809907786604545','1290613836306558977','pass','2020-08-14 07:56:04','2020-08-14 07:56:04','测试功能','其它',''),('1295173632938524673',12222,'1293798556385173506','1290613836306558977','defeat','2020-08-17 01:40:32','2020-08-17 01:40:32','1','饮食类','https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/17/bd745d8d-57d8-433b-8aec-027fcb25eb25.JPEG');
/*!40000 ALTER TABLE `m_apply_spend` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 12:12:29
