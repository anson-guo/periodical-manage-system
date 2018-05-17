-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: jichen
-- ------------------------------------------------------
-- Server version	10.1.26-MariaDB-0+deb9u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `borrowtb`
--

DROP TABLE IF EXISTS `borrowtb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrowtb` (
  `readerID` varchar(10) CHARACTER SET utf8mb4 NOT NULL,
  `periodicalID` int(11) NOT NULL,
  `beginDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `remarks` varchar(50) CHARACTER SET utf8mb4 DEFAULT '未损坏',
  PRIMARY KEY (`readerID`,`periodicalID`),
  KEY `fk_borrowtb_2_idx` (`periodicalID`),
  CONSTRAINT `fk_borrowtb_1` FOREIGN KEY (`periodicalID`) REFERENCES `periodicalstb` (`periodicalID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_borrowtb_2` FOREIGN KEY (`readerID`) REFERENCES `readertb` (`readerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowtb`
--

LOCK TABLES `borrowtb` WRITE;
/*!40000 ALTER TABLE `borrowtb` DISABLE KEYS */;
INSERT INTO `borrowtb` VALUES ('2015110305',159,'2018-05-15',NULL,'未损坏'),('2015110305',160,'2018-05-01',NULL,'');
/*!40000 ALTER TABLE `borrowtb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeetb`
--

DROP TABLE IF EXISTS `employeetb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeetb` (
  `employeeID` varchar(8) CHARACTER SET utf8mb4 NOT NULL,
  `employeeName` varchar(8) CHARACTER SET utf8mb4 NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8mb4 NOT NULL,
  `post` varchar(6) CHARACTER SET utf8mb4 NOT NULL,
  `employeePD` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeetb`
--

LOCK TABLES `employeetb` WRITE;
/*!40000 ALTER TABLE `employeetb` DISABLE KEYS */;
INSERT INTO `employeetb` VALUES ('20150101','纪臣','男','一层管理员','admin'),('20150102','郭敬安','男','二层管理员','F'),('20150103','郭鸿','男','三层管理员','F');
/*!40000 ALTER TABLE `employeetb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodicalstb`
--

DROP TABLE IF EXISTS `periodicalstb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodicalstb` (
  `periodicalID` int(11) NOT NULL AUTO_INCREMENT,
  `issue` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `index` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `periodicalName` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `periodicalType` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `press` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `buyDate` date NOT NULL,
  PRIMARY KEY (`periodicalID`)
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodicalstb`
--

LOCK TABLES `periodicalstb` WRITE;
/*!40000 ALTER TABLE `periodicalstb` DISABLE KEYS */;
INSERT INTO `periodicalstb` VALUES (110,'201711','01','中文信息','周刊（Z）','107','2017-02-01'),(111,'201713','01','中文信息','周刊（Z）','107','2017-02-01'),(112,'201748','01','中文信息','周刊（Z）','107','2017-11-01'),(113,'201749','01','中文信息','周刊（Z）','107','2017-11-01'),(114,'201822','01','中文信息','周刊（Z）','107','2017-02-01'),(115,'201844','01','农业网络信息','周刊（Z）','109','2018-09-01'),(116,'201854','01','农业网络信息','周刊（Z）','109','2018-09-01'),(117,'201855','01','农业网络信息','周刊（Z）','109','2018-09-01'),(118,'201855','02','农业网络信息','周刊（Z）','109','2018-09-01'),(119,'201855','03','农业网络信息','周刊（Z）','109','2018-05-14'),(120,'201811','01','电脑编程技巧与维护','月刊（Y）','111','2018-10-01'),(121,'201811','02','电脑编程技巧与维护','月刊（Y）','111','2018-10-01'),(122,'201811','03','电脑编程技巧与维护','月刊（Y）','111','2018-10-01'),(123,'201811','04','电脑编程技巧与维护','月刊（Y）','111','2018-10-01'),(124,'201811','05','电脑编程技巧与维护','月刊（Y）','111','2018-10-01'),(125,'201704','01','计算机与网络','月刊（Y）','111','2017-03-01'),(126,'201704','02','计算机与网络','月刊（Y）','111','2017-03-01'),(127,'201704','03','计算机与网络','月刊（Y）','111','2017-03-01'),(128,'201704','04','计算机与网络','月刊（Y）','111','2017-03-01'),(129,'201704','05','计算机与网络','月刊（Y）','111','2017-03-01'),(130,'201812','01','软件导刊','月刊（Y）','111','2018-11-01'),(131,'201812','02','软件导刊','月刊（Y）','111','2018-11-01'),(132,'201812','03','软件导刊','月刊（Y）','111','2018-11-01'),(133,'201812','04','软件导刊','月刊（Y）','111','2018-11-01'),(134,'201812','05','软件导刊','月刊（Y）','111','2018-11-01'),(135,'201811','01','中国教育信息化','周刊（Z）','111','2018-02-01'),(136,'201811','02','中国教育信息化','周刊（Z）','111','2018-02-01'),(137,'201811','03','中国教育信息化','周刊（Z）','111','2018-02-01'),(138,'201811','04','中国教育信息化','周刊（Z）','111','2018-02-01'),(139,'201811','05','中国教育信息化','周刊（Z）','111','2018-02-01'),(140,'201822','01','软件导刊','周刊（Z）','111','2018-04-01'),(141,'201822','02','软件导刊','周刊（Z）','111','2018-04-01'),(142,'201822','03','软件导刊','周刊（Z）','111','2018-04-01'),(143,'201822','04','软件导刊','周刊（Z）','111','2018-04-01'),(144,'201822','05','软件导刊','周刊（Z）','111','2018-04-01'),(145,'201801','01','软件','年刊（N）','114','2018-01-01'),(146,'201801','02','软件','年刊（N）','114','2018-01-01'),(147,'201801','03','软件','年刊（N）','114','2018-01-01'),(148,'201801','04','软件','年刊（N）','114','2018-01-01'),(149,'201801','05','软件','年刊（N）','114','2018-01-01'),(150,'201701','01','软件产业与工程','年刊（N）','114','2017-01-01'),(151,'201701','02','软件产业与工程','年刊（N）','114','2017-01-01'),(152,'201701','03','软件产业与工程','年刊（N）','114','2017-01-01'),(153,'201701','04','软件产业与工程','年刊（N）','114','2017-01-01'),(154,'201701','05','软件产业与工程','年刊（N）','114','2017-01-01'),(155,'201702','06','电脑知识与技术','周刊（Z）','114','2017-01-01'),(156,'201844','01','电脑知识与技术','周刊（Z）','114','2018-08-01'),(157,'201701','01','通信世界','年刊（N）','115','2017-01-01'),(158,'201701','02','通信世界','年刊（N）','115','2017-01-01'),(159,'201701','01','互联网天地','年刊（N）','115','2017-01-01'),(160,'201801','01','互联网天地','年刊（N）','115','2018-01-01'),(161,'201801','02','互联网天地','月刊（Y）','115','2018-01-01'),(162,'201802','01','互联网天地','月刊（Y）','115','2018-01-01'),(163,'201802','02','互联网天地','月刊（Y）','115','2018-01-01'),(164,'201803','01','互联网天地','月刊（Y）','115','2018-01-01'),(165,'201803','02','互联网天地','月刊（Y）','115','2018-01-01'),(166,'201803','03','互联网天地','月刊（Y）','115','2018-01-01'),(167,'201804','01','互联网天地','月刊（Y）','115','2018-01-01'),(168,'201804','02','互联网天地','月刊（Y）','115','2018-01-01'),(169,'201804','03','互联网天地','月刊（Y）','115','2018-01-01'),(170,'201805','01','互联网天地','月刊（Y）','115','2018-01-01'),(171,'201806','01','互联网天地','月刊（Y）','115','2018-01-01'),(172,'201807','01','互联网天地','月刊（Y）','115','2018-01-01'),(173,'201814','01','软件工程师','周刊（Z）','119','2018-03-01'),(174,'201814','02','软件工程师','周刊（Z）','119','2018-03-01'),(175,'201814','03','软件工程师','周刊（Z）','119','2018-03-01'),(176,'201814','04','软件工程师','周刊（Z）','119','2018-03-01'),(177,'201814','05','软件工程师','周刊（Z）','119','2018-03-01'),(178,'201701','01','计算机时代','年刊（N）','200','2017-01-01'),(179,'201701','02','计算机时代','年刊（N）','200','2017-01-01'),(180,'201701','03','计算机时代','年刊（N）','200','2017-01-01'),(181,'201701','04','计算机时代','年刊（N）','200','2017-01-01'),(182,'201801','01','计算机时代','年刊（N）','200','2017-01-01'),(183,'201801','01','微型电脑应用','年刊（N）','208','2018-01-01'),(184,'201801','02','微型电脑应用','年刊（N）','208','2018-01-01'),(185,'201801','03','微型电脑应用','年刊（N）','208','2018-01-01'),(186,'201801','04','微型电脑应用','年刊（N）','208','2018-01-01'),(187,'201801','05','微型电脑应用','年刊（N）','208','2018-01-01'),(188,'201801','01','智能建筑电气技术','年刊（N）','209','2018-01-01'),(189,'201801','02','智能建筑电气技术','年刊（N）','209','2018-01-01'),(190,'201801','03','智能建筑电气技术','年刊（N）','209','2018-01-01'),(191,'201801','04','智能建筑电气技术','年刊（N）','209','2018-01-01'),(192,'201801','05','智能建筑电气技术','年刊（N）','209','2018-01-01'),(193,'201804','01','计算机系统应用','月刊（Y）','209','2018-03-01'),(194,'201804','02','计算机系统应用','月刊（Y）','209','2018-03-01'),(195,'201805','01','计算机系统应用','月刊（Y）','209','2018-04-01'),(196,'201806','01','计算机系统应用','月刊（Y）','209','2018-03-01'),(197,'201807','01','计算机系统应用','月刊（Y）','209','2018-05-01'),(198,'201833','01','办公自动化','周刊（Z）','216','2018-04-01'),(199,'201833','02','办公自动化','周刊（Z）','216','2018-04-01'),(200,'201833','03','办公自动化','周刊（Z）','216','2018-04-01'),(201,'201833','04','办公自动化','周刊（Z）','216','2018-04-01'),(202,'201833','05','办公自动化','周刊（Z）','216','2018-04-01'),(203,'201705','01','计算机仿真','月刊（Y）','302','2018-04-01'),(204,'201803','01','计算机仿真','月刊（Y）','302','2018-02-01'),(205,'201803','02','计算机仿真','月刊（Y）','302','2018-02-01'),(206,'201803','03','计算机仿真','月刊（Y）','302','2018-02-01'),(207,'201803','04','计算机仿真','月刊（Y）','302','2018-02-01'),(208,'201803','05','计算机仿真','月刊（Y）','302','2018-02-01'),(209,'201805','01','计算机光盘与应用','月刊（Y）','303','2018-04-01'),(210,'201805','02','计算机光盘与应用','月刊（Y）','303','2018-04-01'),(211,'201805','03','计算机光盘与应用','月刊（Y）','303','2018-04-01'),(212,'201805','04','计算机光盘与应用','月刊（Y）','303','2018-04-01'),(213,'201805','05','计算机光盘与应用','月刊（Y）','303','2018-04-01'),(214,'201802','01','网络新媒体技术','月刊（Y）','309','2018-01-01'),(215,'201802','02','网络新媒体技术','月刊（Y）','309','2018-01-01'),(216,'201802','03','网络新媒体技术','月刊（Y）','309','2018-01-01'),(217,'201802','04','网络新媒体技术','月刊（Y）','309','2018-01-01'),(218,'201802','05','网络新媒体技术','月刊（Y）','309','2018-01-01');
/*!40000 ALTER TABLE `periodicalstb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pressPeriodicaltb`
--

DROP TABLE IF EXISTS `pressPeriodicaltb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pressPeriodicaltb` (
  `pressID` int(11) NOT NULL,
  `periodicalName` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`pressID`,`periodicalName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pressPeriodicaltb`
--

LOCK TABLES `pressPeriodicaltb` WRITE;
/*!40000 ALTER TABLE `pressPeriodicaltb` DISABLE KEYS */;
INSERT INTO `pressPeriodicaltb` VALUES (107,'中文信息'),(109,'农业网络信息'),(111,'中国教育信息化'),(111,'电脑编程技巧与维护'),(111,'计算机与网络'),(111,'软件导刊（Y）'),(111,'软件导刊（Z）'),(114,'电脑知识与技术'),(114,'软件'),(115,'互联网天地'),(115,'通信世界'),(117,'软件产业与工程'),(119,'软件工程师'),(200,'计算机时代'),(208,'微型电脑应用'),(209,'智能建筑电气技术'),(209,'计算机系统应用'),(216,'办公自动化'),(302,'计算机仿真'),(303,'计算机光盘与应用'),(309,'网络新媒体技术');
/*!40000 ALTER TABLE `pressPeriodicaltb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presstb`
--

DROP TABLE IF EXISTS `presstb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presstb` (
  `pressName` varchar(10) CHARACTER SET utf8 NOT NULL,
  `pressID` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`pressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='出版社ISBN代码表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presstb`
--

LOCK TABLES `presstb` WRITE;
/*!40000 ALTER TABLE `presstb` DISABLE KEYS */;
INSERT INTO `presstb` VALUES ('人民出版社','001'),('人民文学出版社','002'),('科学出版社','003'),('高等教育出版社','004'),('商务印书馆','100'),('中华书局','101'),('人民美术出版社','102'),('人民音乐出版社','103'),('人民戏剧出版社','104'),('民族出版社','105'),('中国电影出版社','106'),('人民教育出版社','107'),('生活读书新知三联书店','108'),('中国农业出版社','109'),('科学普及出版社','110'),('机械工业出版社','111'),('中国建筑工业出版社','112'),('中国铁道出版社','113'),('人民交通出版社','114'),('人民邮电出版社','115'),('地质出版社','116'),('人民卫生出版社','117'),('国防工业出版社','118'),('外文出版社','119'),('电子工业出版社','120'),('北京出版社','200'),('天津人民出版社','201'),('河北人民出版社','202'),('山西人民出版社','203'),('内蒙古人民出版社','204'),('辽宁人民出版社','205'),('吉林人民出版社','206'),('黑龙江人民出版社','207'),('上海人民出版社','208'),('山东人民出版社','209'),('江西人民出版社','210'),('福建人民出版社','211'),('安徽人民出版社','212'),('浙江人民出版社','213'),('江苏人民出版社','214'),('河南人民出版社','215'),('湖北人民出版社','216'),('湖南人民出版社','217'),('广东人民出版社','218'),('广西人民出版社','219'),('四川人民出版社','220'),('贵州人民出版社','221'),('云南人民出版社','222'),('西藏人民出版社','223'),('陕西人民出版社','224'),('青海人民出版社','225'),('甘肃人民出版社','226'),('宁夏人民出版社','227'),('新疆人民出版社','228'),('中国人民大学出版社','300'),('北京大学出版社','301'),('清华大学出版社','302'),('北京师范大学出版社','303'),('中央广播电视大学出版','304'),('南京大学出版社','305'),('中山大学出版社','306'),('武汉大学出版社','307'),('浙江大学出版社','308'),('复旦大学出版社','309'),('南开大学出版社','310'),('兰州大学出版社','311'),('中国科学技术大学出版','312'),('上海交通大学出版社','313'),('湖南大学出版社','314'),('中国大百科全书出版社','500'),('中国对外翻译出版公司','501'),('中国盲文出版社','502');
/*!40000 ALTER TABLE `presstb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readertb`
--

DROP TABLE IF EXISTS `readertb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `readertb` (
  `readerID` varchar(10) CHARACTER SET utf8mb4 NOT NULL,
  `readerName` varchar(10) CHARACTER SET utf8mb4 NOT NULL,
  `department` varchar(10) CHARACTER SET utf8mb4 NOT NULL,
  `readerPD` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `reputation` varchar(2) CHARACTER SET utf8mb4 NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`readerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readertb`
--

LOCK TABLES `readertb` WRITE;
/*!40000 ALTER TABLE `readertb` DISABLE KEYS */;
INSERT INTO `readertb` VALUES ('2015110301','邓强','志工部','','10','男'),('2015110302','邓伟','学习部','事实是','68','男'),('2015110303','邓小卫','体育部','事实是','99','男'),('2015110304','李献旸','纪检部','事实是','78','男'),('2015110305','郭逢枭','生活部','admin','96','男'),('2015110306','李献旸','纪检部','事实是','95','男'),('2015110307','戴明阳','生活部','事实是','88','男'),('2015110308','刘鑫','纪检部','事实是','50','男'),('2015110309','白皓宇','志工部','事实是','78','男'),('2015110310','曹定洪','纪检部','事实是','80','男'),('2015110311','秦楚蒙','纪检部','事实是','99','男'),('2015110312','谭后金','生活部','事实是','45','男'),('2015110313','李云鹏','志工部','事实是','89','男'),('2015110314','李晓旭','纪检部','事实是','65','男'),('2015110315','杨瑞军','志工部','事实是','87','男'),('2015110316','黄宇','研发部','admin','99','女');
/*!40000 ALTER TABLE `readertb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workloadtb`
--

DROP TABLE IF EXISTS `workloadtb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workloadtb` (
  `employeeID` char(8) NOT NULL,
  `toRegister` varchar(11) NOT NULL,
  `loginTime` varchar(10) NOT NULL,
  `totalWorkload` varchar(10) NOT NULL,
  `workdate` date NOT NULL,
  PRIMARY KEY (`employeeID`,`workdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workloadtb`
--

LOCK TABLES `workloadtb` WRITE;
/*!40000 ALTER TABLE `workloadtb` DISABLE KEYS */;
INSERT INTO `workloadtb` VALUES ('20150101','100','80','188','2018-01-00'),('20150101','100','80','188','2018-02-00'),('20150101','100','10','188','2018-03-00'),('20150101','100','20','120','2018-04-00'),('20150101','80','100','180','2018-05-00'),('20150101','90','110','200','2018-06-00'),('20150101','120','50','170','2018-07-00'),('20150102','200','100','300','0000-00-00'),('20150103','150','90','240','0000-00-00');
/*!40000 ALTER TABLE `workloadtb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-17 10:07:37
