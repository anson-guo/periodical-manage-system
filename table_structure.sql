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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-17 10:07:25
