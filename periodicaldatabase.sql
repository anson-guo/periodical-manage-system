-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: periodicaldatabase
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
  `readerID` varchar(8) NOT NULL,
  `periodicalsID` varchar(20) NOT NULL,
  `beginDate` date NOT NULL,
  `endDate` date NOT NULL,
  `remarks` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`readerID`,`periodicalsID`),
  KEY `fk_periodicalstb_idx` (`periodicalsID`),
  CONSTRAINT `fk_periodicalstb` FOREIGN KEY (`periodicalsID`) REFERENCES `periodicalstb` (`periodicalsID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_readerid` FOREIGN KEY (`readerID`) REFERENCES `readertb` (`readerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employeetb`
--

DROP TABLE IF EXISTS `employeetb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeetb` (
  `employeeID` varchar(8) NOT NULL,
  `employeename` varchar(8) NOT NULL,
  `sex` varchar(2) NOT NULL,
  `post` varchar(6) NOT NULL,
  `employeePD` varchar(50) NOT NULL,
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `periodicalstb`
--

DROP TABLE IF EXISTS `periodicalstb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodicalstb` (
  `periodicalsID` varchar(10) NOT NULL,
  `periodicalName` varchar(20) NOT NULL,
  `periodicalType` varchar(20) NOT NULL,
  `press` varchar(20) NOT NULL,
  `index` varchar(10) NOT NULL,
  PRIMARY KEY (`periodicalsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `readertb`
--

DROP TABLE IF EXISTS `readertb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `readertb` (
  `readerID` varchar(8) NOT NULL,
  `readerName` varchar(10) NOT NULL,
  `department` varchar(10) NOT NULL,
  `readerPD` varchar(50) NOT NULL,
  `reputation` varchar(2) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`readerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `workloadtb`
--

DROP TABLE IF EXISTS `workloadtb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workloadtb` (
  `employeeID` char(8) NOT NULL,
  `receive ToRegister` varchar(11) NOT NULL,
  `loginTime` varchar(10) NOT NULL,
  `totalWorkload` varchar(10) NOT NULL,
  PRIMARY KEY (`employeeID`)
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

-- Dump completed on 2018-05-09 17:19:37
