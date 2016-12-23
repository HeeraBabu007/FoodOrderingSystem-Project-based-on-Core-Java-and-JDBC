-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: foodorderingsystem
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `userid` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `contact` bigint(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT 'user',
  UNIQUE KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('chetna07','11111','Chetna Negi',8802595,'admin'),('heera007','12345','HeeraBabu',4567893,'user'),('sid10','1000','Siddharth',348920,'user');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `custid` int(11) NOT NULL AUTO_INCREMENT,
  `custname` varchar(30) DEFAULT NULL,
  `contactno` bigint(20) NOT NULL,
  `emailid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`custid`),
  UNIQUE KEY `contactno` (`contactno`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (2,'Siddy',345678,'siddy@gmail.com'),(3,'Vikas',6789503,'vikas@yahoo.in'),(4,'Vikraal',456789,'viks@gmail.com');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodmenu`
--

DROP TABLE IF EXISTS `foodmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foodmenu` (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `itemname` varchar(100) DEFAULT NULL,
  `itemprice` double NOT NULL,
  `type` varchar(50) DEFAULT 'Veg',
  PRIMARY KEY (`itemid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodmenu`
--

LOCK TABLES `foodmenu` WRITE;
/*!40000 ALTER TABLE `foodmenu` DISABLE KEYS */;
INSERT INTO `foodmenu` VALUES (1,'Vada',50,'Veg'),(2,'Dosa-Sambhar',80,'Veg'),(3,'Veg-Chowmein',60,'Veg'),(4,'Chicken-Burger',70,'Non-Veg'),(5,'Dhokla',60,'Veg');
/*!40000 ALTER TABLE `foodmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `custid` int(11) DEFAULT NULL,
  `itemid` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'Pending',
  `billamount` double DEFAULT NULL,
  `postdatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,3,3,'Completed',180,'2016-12-18 17:31:45'),(2,3,2,4,'Pending',320,'2016-12-18 17:32:05'),(3,2,5,2,'Pending',120,'2016-12-18 17:32:40');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-18 17:40:54
