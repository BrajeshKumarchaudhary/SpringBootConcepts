CREATE DATABASE  IF NOT EXISTS `erpstore` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `erpstore`;
-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: erpstore
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.16.04.1

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
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `unit_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` bigint(20) NOT NULL,
  `createdOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `unit_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`unit_id`),
  KEY `FKfpa61gje7ge4eb06ovx81hbo4` (`unit_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit_conversion_mapping`
--

DROP TABLE IF EXISTS `unit_conversion_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_conversion_mapping` (
  `unit_conversion_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `value` double NOT NULL,
  `converted_from` bigint(20) DEFAULT NULL,
  `converted_to` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`unit_conversion_id`),
  KEY `FK82506kn7n6stsn73kvqbs50q9` (`converted_from`),
  KEY `FKhrp32qkow6b95m81ijkc4rhhi` (`converted_to`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit_conversion_mapping`
--

LOCK TABLES `unit_conversion_mapping` WRITE;
/*!40000 ALTER TABLE `unit_conversion_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `unit_conversion_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit_type`
--

DROP TABLE IF EXISTS `unit_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_type` (
  `unit_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`unit_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit_type`
--

LOCK TABLES `unit_type` WRITE;
/*!40000 ALTER TABLE `unit_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `unit_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'erpstore'
--

--
-- Dumping routines for database 'erpstore'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-04 15:00:16
