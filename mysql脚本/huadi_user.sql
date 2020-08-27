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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` char(19) NOT NULL,
  `number` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT 'https://lq-avatar.oss-cn-beijing.aliyuncs.com/avatar/2020/05/27/2b8914a8-d8d9-470b-b40a-2464a290a244.png',
  `openid` varchar(128) DEFAULT NULL,
  `face_token` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `gmt_modified` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1290613836306558977','13618334187','1721071147@qq.com',NULL,'新用户-50972061-0964-4de2-84b6-92fabdc83c26','https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/10/a9a10444-b7a9-4c84-a430-7fac3ee9ad49.JPEG',NULL,'b8a29a1b8168e925e395310c9d0fe8f0','0c974e64_8094_4b78_8af6_adfc4124b281',0,'2020-08-10 08:26:47','2020-08-04 11:41:32'),('1290630416830562306',NULL,'',NULL,'Cu特猫肉','http://thirdwx.qlogo.cn/mmopen/vi_32/MTHhdSx6pTqVomJ5BKArVnW1aBtzIvPMez2EOy9EsubTQ5XIicuYibHdXAVLbnKZRDQd4zrKIGU3ZRO1SjUfh9FQ/132','o3_SC5-rcT5pevxeFk_5ozwggt2U',NULL,NULL,0,'2020-08-04 12:47:25','2020-08-04 12:47:25'),('1293798556385173506','15723417592','695312849@qq.com','e10adc3949ba59abbe56e057f20f883e','new撕裂的天堂','https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/13/f5f2538f-e116-49a6-917a-6abdcffe3306.jpg',NULL,NULL,NULL,0,'2020-08-13 07:06:05','2020-08-13 06:36:28'),('1293807662894440450',NULL,NULL,NULL,'新用户-a7097499-f35a-48c8-93d6-6952a3b83f90','https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/13/36110e56-b02a-4c8f-a868-b5717524397f.png',NULL,'4b00c0dfc703d7384d56571843d3aec4','8924acaf_728b_4769_be0b_3363f7257504',0,'2020-08-13 07:12:39','2020-08-13 07:12:39'),('1293809907786604545','17347898141',NULL,'a420384997c8a1a93d5a84046117c2aa','我是甲方','https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/14/b06e4506-b3e6-48c1-8edb-6352f7c3125e.jpg',NULL,NULL,NULL,0,'2020-08-14 08:28:53','2020-08-13 07:21:35'),('1293811546639589378','18290316418',NULL,'e10adc3949ba59abbe56e057f20f883e','四六开','https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/13/8a2c58dd-9b2d-471d-b561-a981ee3d0f70.jpg',NULL,NULL,NULL,0,'2020-08-13 07:35:22','2020-08-13 07:28:05'),('1293812630942670850','13364006654',NULL,'741a627471982d84d67177464d006a01','xhh','https://lq-avatar.oss-cn-beijing.aliyuncs.com/avatar/2020/05/27/2b8914a8-d8d9-470b-b40a-2464a290a244.png',NULL,NULL,NULL,0,'2020-08-13 07:32:24','2020-08-13 07:32:24'),('1294192621438226431','123453212',NULL,'741a627471982d84d67177464d006a01',NULL,'https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/14/5c451886-2a7f-4772-bfed-fbee05019ff6.png',NULL,NULL,NULL,0,'2020-08-14 08:42:21','2020-08-14 08:42:21'),('1294192621438226432','123453213',NULL,'741a627471982d84d67177464d006a01',NULL,'https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/14/5c451886-2a7f-4772-bfed-fbee05019ff6.png',NULL,NULL,NULL,0,'2020-08-14 08:42:21','2020-08-14 08:42:21'),('1294192621438226433',NULL,NULL,NULL,'新用户-cc9cc913-b4bf-428c-b41a-4c1841f4ad88','https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/14/5c451886-2a7f-4772-bfed-fbee05019ff6.png',NULL,'5bc7f498ae574214e17c522c25e39243','559ad5d3_0179_418f_89a6_474aad4a6aa3',0,'2020-08-14 08:42:21','2020-08-14 08:42:21'),('1294192621438226434','123242134',NULL,'741a627471982d84d67177464d006a01',NULL,'https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/14/5c451886-2a7f-4772-bfed-fbee05019ff6.png',NULL,NULL,NULL,0,'2020-08-14 08:42:21','2020-08-14 08:42:21'),('1295170361423392770','13340237428',NULL,'e10adc3949ba59abbe56e057f20f883e','张明凯','https://lq-avatar.oss-cn-beijing.aliyuncs.com/avatar/2020/05/27/2b8914a8-d8d9-470b-b40a-2464a290a244.png',NULL,NULL,NULL,0,'2020-08-17 01:27:32','2020-08-17 01:27:32'),('1295171313744310273','18030521970','18030521970@163.com',NULL,'Min','https://huadi-test.oss-cn-beijing.aliyuncs.com/huadi-test/2020/08/17/b51c0001-bf6b-4470-8c23-fa189fddfc61.JPEG','o3_SC57mSSg2MDDK27yem6qmjvE8',NULL,NULL,0,'2020-08-17 01:36:00','2020-08-17 01:31:19');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
