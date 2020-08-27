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
-- Table structure for table `m_apply_department`
--

DROP TABLE IF EXISTS `m_apply_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `m_apply_department` (
  `id` char(19) NOT NULL,
  `applicationid` char(19) NOT NULL COMMENT '申请人ID',
  `departmentid` int(11) NOT NULL COMMENT '申请部门ID',
  `dealuserid` char(19) DEFAULT NULL COMMENT '处理人ID',
  `detail` varchar(1024) DEFAULT NULL COMMENT '申请详细说明',
  `statis` varchar(45) NOT NULL DEFAULT 'wait' COMMENT '审批状态wait,defeat,pass',
  `gmt_modified` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `departmentid_idx` (`departmentid`),
  KEY `applicationid_idx` (`applicationid`,`dealuserid`),
  KEY `deal_id_idx` (`dealuserid`),
  CONSTRAINT `deal_id` FOREIGN KEY (`dealuserid`) REFERENCES `user` (`id`),
  CONSTRAINT `dealid_applicationid` FOREIGN KEY (`applicationid`) REFERENCES `user` (`id`),
  CONSTRAINT `departmentid_id` FOREIGN KEY (`departmentid`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门分组申请信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_apply_department`
--

LOCK TABLES `m_apply_department` WRITE;
/*!40000 ALTER TABLE `m_apply_department` DISABLE KEYS */;
INSERT INTO `m_apply_department` VALUES ('1292741991645327361','1290613836306558977',5,'1290613836306558977','UserWorkInfo(userid=1290613836306558977, name=李青, age=20, sex=男, education=重庆邮电大学-软件工程学院, departmentid=5, workinfo=暂无, awardsinfo=暂无, punishmentinfo=暂无, phone=13618334187, address=遵义虾子镇, statis=wait, gmtModified=Mon Aug 10 16:38:02 CST 2020, gmtCreate=Mon Aug 10 16:38:02 CST 2020, salary=99999)','pass','2020-08-10 08:38:03','2020-08-10 08:38:03'),('1292752130796433410','1290630416830562306',1,'1290613836306558977','UserWorkInfo(userid=1290630416830562306, name=李青（微信）, age=20, sex=男, education=大学, departmentid=1, workinfo=暂无, awardsinfo=暂无, punishmentinfo=暂无, phone=13618334187, address=规则遵义, statis=wait, gmtModified=Mon Aug 10 17:18:20 CST 2020, gmtCreate=Mon Aug 10 17:18:20 CST 2020, salary=999099)','pass','2020-08-10 09:18:21','2020-08-10 09:18:21'),('1293801187329503233','1293798556385173506',7,'1290613836306558977','UserWorkInfo(userid=1293798556385173506, name=两只兵, age=20, sex=男, education=本科, departmentid=7, workinfo=, awardsinfo=, punishmentinfo=, phone=15723417592, address=重庆, statis=wait, gmtModified=Thu Aug 13 14:46:53 CST 2020, gmtCreate=Thu Aug 13 14:46:53 CST 2020, salary=100000)','pass','2020-08-13 06:46:55','2020-08-13 06:46:55'),('1293810863240704001','1293809907786604545',3,'1290613836306558977','UserWorkInfo(userid=1293809907786604545, name=陆拾叁, age=63, sex=男, education=良好, departmentid=3, workinfo=正常, awardsinfo=全部, punishmentinfo=无, phone=17347898141, address=浦东, statis=wait, gmtModified=Thu Aug 13 15:25:22 CST 2020, gmtCreate=Thu Aug 13 15:25:22 CST 2020, salary=8000)','pass','2020-08-13 07:25:22','2020-08-13 07:25:22'),('1293812712496754690','1293811546639589378',1,'1290613836306558977','UserWorkInfo(userid=1293811546639589378, name=张茗凯, age=21, sex=男, education=幼儿园, departmentid=1, workinfo=幼儿园大班, awardsinfo=幼儿园大红花三枚, punishmentinfo=上课迟到罚站五分钟, phone=18290316418, address=小红花幼儿园, statis=wait, gmtModified=Thu Aug 13 15:32:43 CST 2020, gmtCreate=Thu Aug 13 15:32:43 CST 2020, salary=8000)','pass','2020-08-13 07:32:43','2020-08-13 07:32:43'),('1293813346428055553','1293809907786604545',1,'1290613836306558977','UserWorkInfo(userid=1293809907786604545, name=陆拾叁, age=63, sex=男, education=良好, departmentid=1, workinfo=正常, awardsinfo=全部, punishmentinfo=无, phone=17347898141, address=浦东, statis=wait, gmtModified=Thu Aug 13 15:35:14 CST 2020, gmtCreate=Thu Aug 13 15:25:22 CST 2020, salary=8000)','pass','2020-08-13 07:35:14','2020-08-13 07:35:14'),('1293813426241166370','1294192621438226432',4,NULL,'UserWorkInfo(userid=1293812630942670850, name=赵航, age=21, sex=男, education=本科, departmentid=2, workinfo=无, awardsinfo=三好学生, punishmentinfo=无, phone=13364006654, address=重庆, statis=wait, gmtModified=Thu Aug 13 15:35:33 CST 2020, gmtCreate=Thu Aug 13 15:35:33 CST 2020, salary=99999)','wait','2020-08-13 07:35:33','2020-08-13 07:35:33'),('1293813426241462370','1294192621438226434',3,NULL,'UserWorkInfo(userid=1293812630942670850, name=赵航, age=21, sex=男, education=本科, departmentid=2, workinfo=无, awardsinfo=三好学生, punishmentinfo=无, phone=13364006654, address=重庆, statis=wait, gmtModified=Thu Aug 13 15:35:33 CST 2020, gmtCreate=Thu Aug 13 15:35:33 CST 2020, salary=99999)','wait','2020-08-13 07:35:33','2020-08-13 07:35:33'),('1293813426241466310','1294192621438226431',4,NULL,'UserWorkInfo(userid=1293812630942670850, name=赵航, age=21, sex=男, education=本科, departmentid=2, workinfo=无, awardsinfo=三好学生, punishmentinfo=无, phone=13364006654, address=重庆, statis=wait, gmtModified=Thu Aug 13 15:35:33 CST 2020, gmtCreate=Thu Aug 13 15:35:33 CST 2020, salary=99999)','wait','2020-08-13 07:35:33','2020-08-13 07:35:33'),('1293813426241466370','1293812630942670850',2,'1290613836306558977','UserWorkInfo(userid=1293812630942670850, name=赵航, age=21, sex=男, education=本科, departmentid=2, workinfo=无, awardsinfo=三好学生, punishmentinfo=无, phone=13364006654, address=重庆, statis=wait, gmtModified=Thu Aug 13 15:35:33 CST 2020, gmtCreate=Thu Aug 13 15:35:33 CST 2020, salary=99999)','pass','2020-08-13 07:35:33','2020-08-13 07:35:33'),('1294193555090214913','1294192621438226433',3,'1293809907786604545','UserWorkInfo(userid=1294192621438226433, name=ZFY, age=21, sex=男, education=本科, departmentid=3, workinfo=在职, awardsinfo=全勤奖, punishmentinfo=无, phone=4008208820, address=什刹海, statis=wait, gmtModified=Fri Aug 14 16:46:03 CST 2020, gmtCreate=Fri Aug 14 16:46:03 CST 2020, salary=5620)','defeat','2020-08-14 08:46:03','2020-08-14 08:46:03'),('1294195848384647170','1294192621438226433',4,'1293809907786604545','UserWorkInfo(userid=1294192621438226433, name=ZFY, age=21, sex=男, education=本科, departmentid=4, workinfo=在职, awardsinfo=全勤奖, punishmentinfo=无, phone=4008208820, address=什刹海, statis=wait, gmtModified=Fri Aug 14 16:55:09 CST 2020, gmtCreate=Fri Aug 14 16:46:03 CST 2020, salary=5620)','pass','2020-08-14 08:55:10','2020-08-14 08:55:10'),('1295172949640265729','1295171313744310273',2,'1290613836306558977','UserWorkInfo(userid=1295171313744310273, name=hcj, age=21, sex=男, education=1, departmentid=2, workinfo=1, awardsinfo=1, punishmentinfo=1, phone=1, address=1, statis=wait, gmtModified=Mon Aug 17 09:37:46 CST 2020, gmtCreate=Mon Aug 17 09:37:46 CST 2020, salary=12222)','pass','2020-08-17 01:37:49','2020-08-17 01:37:49');
/*!40000 ALTER TABLE `m_apply_department` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 12:12:31
