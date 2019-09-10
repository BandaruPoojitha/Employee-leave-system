-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: LeaveSystem
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ApplyLeave`
--

DROP TABLE IF EXISTS `ApplyLeave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ApplyLeave` (
  `sno` int(11) NOT NULL,
  `LeaveType` varchar(255) DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `noOfDays` int(11) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `applyTo_employeeId` varchar(255) DEFAULT NULL,
  `employeeId_employeeId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sno`),
  KEY `FKbgy7ocu4qjfxmbjxvowyj6dxv` (`applyTo_employeeId`),
  KEY `FKm3smkai4iv7r7s3u0wgi1fjyl` (`employeeId_employeeId`),
  CONSTRAINT `FKbgy7ocu4qjfxmbjxvowyj6dxv` FOREIGN KEY (`applyTo_employeeId`) REFERENCES `Employee` (`employeeId`),
  CONSTRAINT `FKm3smkai4iv7r7s3u0wgi1fjyl` FOREIGN KEY (`employeeId_employeeId`) REFERENCES `Employee` (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ApplyLeave`
--

LOCK TABLES `ApplyLeave` WRITE;
/*!40000 ALTER TABLE `ApplyLeave` DISABLE KEYS */;
INSERT INTO `ApplyLeave` VALUES (12,'Paid','2019-08-15',1,'sick','2019-08-14','accept','100','101'),(13,'LOP','2019-08-15',1,'s','2019-08-14','accept','100','101'),(23,'Paid','2019-08-12',1,'sick','2019-08-11','processing','100','101');
/*!40000 ALTER TABLE `ApplyLeave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BalanceLeaves`
--

DROP TABLE IF EXISTS `BalanceLeaves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BalanceLeaves` (
  `serialno` int(11) NOT NULL,
  `LOP` int(11) NOT NULL,
  `Paid` int(11) NOT NULL,
  `employeeId_employeeId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`serialno`),
  KEY `FK8b02yvjujyr9w7lbntilhldxp` (`employeeId_employeeId`),
  CONSTRAINT `FK8b02yvjujyr9w7lbntilhldxp` FOREIGN KEY (`employeeId_employeeId`) REFERENCES `Employee` (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BalanceLeaves`
--

LOCK TABLES `BalanceLeaves` WRITE;
/*!40000 ALTER TABLE `BalanceLeaves` DISABLE KEYS */;
INSERT INTO `BalanceLeaves` VALUES (1,1,10,'100'),(2,1,9,'101'),(11,1,10,'104');
/*!40000 ALTER TABLE `BalanceLeaves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Department` (
  `departmentId` varchar(255) NOT NULL,
  `managerId_employeeId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`departmentId`),
  KEY `FKgypx2biag928mmure8mh5f5mk` (`managerId_employeeId`),
  CONSTRAINT `FKgypx2biag928mmure8mh5f5mk` FOREIGN KEY (`managerId_employeeId`) REFERENCES `Employee` (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department`
--

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
INSERT INTO `Department` VALUES ('1','1'),('2','100');
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `employeeId` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employeeName` varchar(255) DEFAULT NULL,
  `managerId` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `department_departmentId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employeeId`),
  KEY `FKqcp9m32ws9lf46ctv9q1ua1al` (`department_departmentId`),
  CONSTRAINT `FKqcp9m32ws9lf46ctv9q1ua1al` FOREIGN KEY (`department_departmentId`) REFERENCES `Department` (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES ('1','netherlands','steven@gmail.com','steven','1','9878677564','1'),('100','JNTU','poojitha@gmail.com','poojitha','1','9492156282','1'),('101','suraram','sreedevi@gmail.com','sreedevi','100','9494199738','2'),('104','andaman','simadrig75@gmail.com','simadri','100','9474294060','2');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Login`
--

DROP TABLE IF EXISTS `Login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Login` (
  `username` varchar(255) NOT NULL,
  `employeeType` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `employeeId_employeeId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FKcqelsvdij7clrvpq8attgyvj9` (`employeeId_employeeId`),
  CONSTRAINT `FKcqelsvdij7clrvpq8attgyvj9` FOREIGN KEY (`employeeId_employeeId`) REFERENCES `Employee` (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
INSERT INTO `Login` VALUES ('admin','admin','admin',NULL),('poojitha100','manager','100poojitha','100'),('simadri104','employee','104simadri','104'),('sreedevi101','employee','101sreedevi','101'),('steven1','CEO','1steven','1');
/*!40000 ALTER TABLE `Login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (24),(24);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-30 19:26:41
