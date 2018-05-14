-- MySQL dump 10.13  Distrib 8.0.11, for macos10.13 (x86_64)
--
-- Host: localhost    Database: periodicaldb
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `borrowtb` (
  `readerID` varchar(8) NOT NULL,
  `periodicalID` varchar(20) NOT NULL,
  `beginDate` date NOT NULL,
  `endDate` date NOT NULL,
  `remarks` varchar(50) DEFAULT '未损坏',
  PRIMARY KEY (`readerID`,`periodicalID`),
  KEY `fk_periodicalstb_idx` (`periodicalID`),
  CONSTRAINT `fk_periodicalstb` FOREIGN KEY (`periodicalID`) REFERENCES `periodicalstb` (`periodicalID`),
  CONSTRAINT `fk_readerid` FOREIGN KEY (`readerID`) REFERENCES `readertb` (`readerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowtb`
--

LOCK TABLES `borrowtb` WRITE;
/*!40000 ALTER TABLE `borrowtb` DISABLE KEYS */;
/*!40000 ALTER TABLE `borrowtb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeetb`
--

DROP TABLE IF EXISTS `employeetb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employeetb` (
  `employeeID` varchar(8) NOT NULL,
  `employeename` varchar(8) NOT NULL,
  `sex` varchar(2) NOT NULL,
  `post` varchar(6) NOT NULL,
  `employeePD` varchar(50) NOT NULL,
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeetb`
--

LOCK TABLES `employeetb` WRITE;
/*!40000 ALTER TABLE `employeetb` DISABLE KEYS */;
/*!40000 ALTER TABLE `employeetb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodicalstb`
--

DROP TABLE IF EXISTS `periodicalstb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `periodicalstb` (
  `periodicalID` varchar(20) NOT NULL,
  `periodicalName` varchar(20) NOT NULL,
  `periodicalType` varchar(20) NOT NULL,
  `press` varchar(20) NOT NULL,
  `buyDate` date DEFAULT NULL,
  PRIMARY KEY (`periodicalID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodicalstb`
--

LOCK TABLES `periodicalstb` WRITE;
/*!40000 ALTER TABLE `periodicalstb` DISABLE KEYS */;
INSERT INTO `periodicalstb` VALUES ('107Z001320171101','中文信息','周刊（Z）','107','2017-02-01'),('107Z001320171301','中文信息','周刊（Z）','107','2017-02-01'),('107Z001320174801','中文信息','周刊（Z）','107','2017-11-01'),('107Z001320174901','中文信息','周刊（Z）','107','2017-11-01'),('107Z001320182201','中文信息','周刊（Z）','107','2017-02-01'),('109Z001020184401','农业网络信息','周刊（Z）','109','2018-09-01'),('109Z001020185401','农业网络信息','周刊（Z）','109','2018-09-01'),('109Z001020185501','农业网络信息','周刊（Z）','109','2018-09-01'),('109Z001020185502','农业网络信息','周刊（Z）','109','2018-09-01'),('109Z001020185503','农业网络信息','周刊（Z）','109','2018-05-14'),('111Y000820181101','电脑编程技巧与维护','月刊（Y）','111','2018-10-01'),('111Y000820181102','电脑编程技巧与维护','月刊（Y）','111','2018-10-01'),('111Y000820181103','电脑编程技巧与维护','月刊（Y）','111','2018-10-01'),('111Y000820181104','电脑编程技巧与维护','月刊（Y）','111','2018-10-01'),('111Y000820181105','电脑编程技巧与维护','月刊（Y）','111','2018-10-01'),('111Y000920170401','计算机与网络','月刊（Y）','111','2017-03-01'),('111Y000920170402','计算机与网络','月刊（Y）','111','2017-03-01'),('111Y000920170403','计算机与网络','月刊（Y）','111','2017-03-01'),('111Y000920170404','计算机与网络','月刊（Y）','111','2017-03-01'),('111Y000920170405','计算机与网络','月刊（Y）','111','2017-03-01'),('111Y001020181201','软件导刊','月刊（Y）','111','2018-11-01'),('111Y001020181202','软件导刊','月刊（Y）','111','2018-11-01'),('111Y001020181203','软件导刊','月刊（Y）','111','2018-11-01'),('111Y001020181204','软件导刊','月刊（Y）','111','2018-11-01'),('111Y001020181205','软件导刊','月刊（Y）','111','2018-11-01'),('111Z000720181101','中国教育信息化','周刊（Z）','111','2018-02-01'),('111Z000720181102','中国教育信息化','周刊（Z）','111','2018-02-01'),('111Z000720181103','中国教育信息化','周刊（Z）','111','2018-02-01'),('111Z000720181104','中国教育信息化','周刊（Z）','111','2018-02-01'),('111Z000720181105','中国教育信息化','周刊（Z）','111','2018-02-01'),('111Z001020182201','软件导刊','周刊（Z）','111','2018-04-01'),('111Z001020182202','软件导刊','周刊（Z）','111','2018-04-01'),('111Z001020182203','软件导刊','周刊（Z）','111','2018-04-01'),('111Z001020182204','软件导刊','周刊（Z）','111','2018-04-01'),('111Z001020182205','软件导刊','周刊（Z）','111','2018-04-01'),('114N000420180101','软件','年刊（N）','114','2018-01-01'),('114N000420180102','软件','年刊（N）','114','2018-01-01'),('114N000420180103','软件','年刊（N）','114','2018-01-01'),('114N000420180104','软件','年刊（N）','114','2018-01-01'),('114N000420180105','软件','年刊（N）','114','2018-01-01'),('114N002020170101','软件产业与工程','年刊（N）','114','2017-01-01'),('114N002020170102','软件产业与工程','年刊（N）','114','2017-01-01'),('114N002020170103','软件产业与工程','年刊（N）','114','2017-01-01'),('114N002020170104','软件产业与工程','年刊（N）','114','2017-01-01'),('114N002020170105','软件产业与工程','年刊（N）','114','2017-01-01'),('114Z000620170201','电脑知识与技术','周刊（Z）','114','2017-01-01'),('114Z000620184401','电脑知识与技术','周刊（Z）','114','2018-08-01'),('115N000120170101','通信世界','年刊（N）','115','2017-01-01'),('115N000120170102','通信世界','年刊（N）','115','2017-01-01'),('115N000220170101','互联网天地','年刊（N）','115','2017-01-01'),('115N000220180101','互联网天地','年刊（N）','115','2018-01-01'),('115Y000220180101','互联网天地','月刊（Y）','115','2018-01-01'),('115Y000220180201','互联网天地','月刊（Y）','115','2018-01-01'),('115Y000220180202','互联网天地','月刊（Y）','115','2018-01-01'),('115Y000220180301','互联网天地','月刊（Y）','115','2018-01-01'),('115Y000220180302','互联网天地','月刊（Y）','115','2018-01-01'),('115Y000220180303','互联网天地','月刊（Y）','115','2018-01-01'),('115Y000220180401','互联网天地','月刊（Y）','115','2018-01-01'),('115Y000220180402','互联网天地','月刊（Y）','115','2018-01-01'),('115Y000220180403','互联网天地','月刊（Y）','115','2018-01-01'),('115Y000220180501','互联网天地','月刊（Y）','115','2018-01-01'),('115Y000220180601','互联网天地','月刊（Y）','115','2018-01-01'),('115Y000220180701','互联网天地','月刊（Y）','115','2018-01-01'),('119Z001720181401','软件工程师','周刊（Z）','119','2018-03-01'),('119Z001720181402','软件工程师','周刊（Z）','119','2018-03-01'),('119Z001720181403','软件工程师','周刊（Z）','119','2018-03-01'),('119Z001720181404','软件工程师','周刊（Z）','119','2018-03-01'),('119Z001720181405','软件工程师','周刊（Z）','119','2018-03-01'),('200N001420170101','计算机时代','年刊（N）','200','2017-01-01'),('200N001420170102','计算机时代','年刊（N）','200','2017-01-01'),('200N001420170103','计算机时代','年刊（N）','200','2017-01-01'),('200N001420170104','计算机时代','年刊（N）','200','2017-01-01'),('200N001420180101','计算机时代','年刊（N）','200','2017-01-01'),('208N001520180101','微型电脑应用','年刊（N）','208','2018-01-01'),('208N001520180102','微型电脑应用','年刊（N）','208','2018-01-01'),('208N001520180103','微型电脑应用','年刊（N）','208','2018-01-01'),('208N001520180104','微型电脑应用','年刊（N）','208','2018-01-01'),('208N001520180105','微型电脑应用','年刊（N）','208','2018-01-01'),('209N001920180101','智能建筑电气技术','年刊（N）','209','2018-01-01'),('209N001920180102','智能建筑电气技术','年刊（N）','209','2018-01-01'),('209N001920180103','智能建筑电气技术','年刊（N）','209','2018-01-01'),('209N001920180104','智能建筑电气技术','年刊（N）','209','2018-01-01'),('209N001920180105','智能建筑电气技术','年刊（N）','209','2018-01-01'),('209Y001220180401','计算机系统应用','月刊（Y）','209','2018-03-01'),('209Y001220180402','计算机系统应用','月刊（Y）','209','2018-03-01'),('209Y001220180501','计算机系统应用','月刊（Y）','209','2018-04-01'),('209Y001220180601','计算机系统应用','月刊（Y）','209','2018-03-01'),('209Y001220180701','计算机系统应用','月刊（Y）','209','2018-05-01'),('216Z001820183301','办公自动化','周刊（Z）','216','2018-04-01'),('216Z001820183302','办公自动化','周刊（Z）','216','2018-04-01'),('216Z001820183303','办公自动化','周刊（Z）','216','2018-04-01'),('216Z001820183304','办公自动化','周刊（Z）','216','2018-04-01'),('216Z001820183305','办公自动化','周刊（Z）','216','2018-04-01'),('302Y000320170501','计算机仿真','月刊（Y）','302','2018-04-01'),('302Y000320180301','计算机仿真','月刊（Y）','302','2018-02-01'),('302Y000320180302','计算机仿真','月刊（Y）','302','2018-02-01'),('302Y000320180303','计算机仿真','月刊（Y）','302','2018-02-01'),('302Y000320180304','计算机仿真','月刊（Y）','302','2018-02-01'),('302Y000320180305','计算机仿真','月刊（Y）','302','2018-02-01'),('303Y001620180501','计算机光盘与应用','月刊（Y）','303','2018-04-01'),('303Y001620180502','计算机光盘与应用','月刊（Y）','303','2018-04-01'),('303Y001620180503','计算机光盘与应用','月刊（Y）','303','2018-04-01'),('303Y001620180504','计算机光盘与应用','月刊（Y）','303','2018-04-01'),('303Y001620180505','计算机光盘与应用','月刊（Y）','303','2018-04-01'),('309Y001820180201','网络新媒体技术','月刊（Y）','309','2018-01-01'),('309Y001820180202','网络新媒体技术','月刊（Y）','309','2018-01-01'),('309Y001820180203','网络新媒体技术','月刊（Y）','309','2018-01-01'),('309Y001820180204','网络新媒体技术','月刊（Y）','309','2018-01-01'),('309Y001820180205','网络新媒体技术','月刊（Y）','309','2018-01-01');
/*!40000 ALTER TABLE `periodicalstb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisherisbncodetable`
--

DROP TABLE IF EXISTS `publisherisbncodetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `publisherisbncodetable` (
  `press` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='出版社ISBN代码表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisherisbncodetable`
--

LOCK TABLES `publisherisbncodetable` WRITE;
/*!40000 ALTER TABLE `publisherisbncodetable` DISABLE KEYS */;
INSERT INTO `publisherisbncodetable` VALUES ('人民出版社','01'),('人民文学出版社','02'),('科学出版社','03'),('高等教育出版社','04'),('商务印书馆','100'),('中华书局','101'),('人民美术出版社','102'),('人民音乐出版社','103'),('人民戏剧出版社','104'),('民族出版社','105'),('中国电影出版社','106'),('人民教育出版社','107'),('生活读书新知三联书店','108'),('中国农业出版社','109'),('科学普及出版社','110'),('机械工业出版社','111'),('中国建筑工业出版社','112'),('中国铁道出版社','113'),('人民交通出版社','114'),('人民邮电出版社','115'),('地质出版社','116'),('人民卫生出版社','117'),('国防工业出版社','118'),('外文出版社','119'),('电子工业出版社','120'),('北京出版社','200'),('天津人民出版社','201'),('河北人民出版社','202'),('山西人民出版社','203'),('内蒙古人民出版社','204'),('辽宁人民出版社','205'),('吉林人民出版社','206'),('黑龙江人民出版社','207'),('上海人民出版社','208'),('山东人民出版社','209'),('江西人民出版社','210'),('福建人民出版社','211'),('安徽人民出版社','212'),('浙江人民出版社','213'),('江苏人民出版社','214'),('河南人民出版社','215'),('湖北人民出版社','216'),('湖南人民出版社','217'),('广东人民出版社','218'),('广西人民出版社','219'),('四川人民出版社','220'),('贵州人民出版社','221'),('云南人民出版社','222'),('西藏人民出版社','223'),('陕西人民出版社','224'),('青海人民出版社','225'),('甘肃人民出版社','226'),('宁夏人民出版社','227'),('新疆人民出版社','228'),('中国人民大学出版社','300'),('北京大学出版社','301'),('清华大学出版社','302'),('北京师范大学出版社','303'),('中央广播电视大学出版','304'),('南京大学出版社','305'),('中山大学出版社','306'),('武汉大学出版社','307'),('浙江大学出版社','308'),('复旦大学出版社','309'),('南开大学出版社','310'),('兰州大学出版社','311'),('中国科学技术大学出版','312'),('上海交通大学出版社','313'),('湖南大学出版社','314'),('中国大百科全书出版社','5000'),('中国对外翻译出版公司','5001'),('中国盲文出版社','5002');
/*!40000 ALTER TABLE `publisherisbncodetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readertb`
--

DROP TABLE IF EXISTS `readertb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `readertb` (
  `readerID` varchar(8) NOT NULL,
  `readerName` varchar(10) NOT NULL,
  `department` varchar(10) NOT NULL,
  `readerPD` varchar(50) NOT NULL,
  `reputation` varchar(2) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`readerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readertb`
--

LOCK TABLES `readertb` WRITE;
/*!40000 ALTER TABLE `readertb` DISABLE KEYS */;
INSERT INTO `readertb` VALUES ('20150101','郭逢枭','清洁部','admin','10','男');
/*!40000 ALTER TABLE `readertb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workloadtb`
--

DROP TABLE IF EXISTS `workloadtb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `workloadtb` (
  `employeeID` char(8) NOT NULL,
  `receive ToRegister` varchar(11) NOT NULL,
  `loginTime` varchar(10) NOT NULL,
  `totalWorkload` varchar(10) NOT NULL,
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workloadtb`
--

LOCK TABLES `workloadtb` WRITE;
/*!40000 ALTER TABLE `workloadtb` DISABLE KEYS */;
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

-- Dump completed on 2018-05-14 22:27:45
