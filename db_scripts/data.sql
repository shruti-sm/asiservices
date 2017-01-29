-- MySQL dump 10.11
--
-- Host: localhost    Database: asi
-- ------------------------------------------------------
-- Server version	5.0.96-community-nt

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
-- Table structure for table `declaration_form`
--

DROP TABLE IF EXISTS `declaration_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `declaration_form` (
  `id` bigint(20) NOT NULL auto_increment,
  `contact_number` bigint(20) NOT NULL,
  `expected_arrival_date_time` datetime NOT NULL,
  `leaving_date_time` datetime NOT NULL,
  `emp_id` bigint(20) default NULL,
  `office_id` bigint(20) default NULL,
  `project_id` bigint(20) default NULL,
  `status` varchar(2) default NULL,
  `remarks` varchar(1000) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCBE3AC697775355D` (`office_id`),
  KEY `FKCBE3AC6987B78657` (`project_id`),
  KEY `FK_declaration_form_3` (`emp_id`),
  CONSTRAINT `FKCBE3AC697775355D` FOREIGN KEY (`office_id`) REFERENCES `office` (`id`),
  CONSTRAINT `FKCBE3AC6987B78657` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_declaration_form_3` FOREIGN KEY (`emp_id`) REFERENCES `office` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `declaration_form`
--

LOCK TABLES `declaration_form` WRITE;
/*!40000 ALTER TABLE `declaration_form` DISABLE KEYS */;
INSERT INTO `declaration_form` VALUES (1,8787898689,'2017-01-18 20:30:00','2017-01-26 00:15:00',1,3,1,NULL,NULL),(2,8787898689,'2017-01-19 20:30:00','2017-01-26 20:15:00',2,3,1,NULL,NULL),(4,9878675643,'2017-01-15 20:51:23','2017-01-17 19:23:45',1,1,1,NULL,NULL),(5,9878675643,'2017-01-15 20:51:23','2017-01-17 19:23:45',2,3,2,NULL,NULL),(6,8787898689,'2017-01-27 02:59:25','2017-01-27 01:31:47',3,3,3,'1',NULL),(7,9867564534,'2017-01-27 03:06:53','2017-01-27 01:39:15',2,1,2,'1',NULL),(8,8787898689,'2017-01-27 03:20:59','2017-01-27 01:53:21',1,3,3,'1',NULL);
/*!40000 ALTER TABLE `declaration_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL auto_increment,
  `contact_number` bigint(20) NOT NULL,
  `emp_code` varchar(255) NOT NULL default '',
  `first_name` varchar(255) NOT NULL,
  `last_expected_arrival_time` bigint(20) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `office_id` bigint(20) default NULL,
  `project_id` bigint(20) default NULL,
  `email_id` varchar(100) NOT NULL default '',
  `password` varchar(1000) NOT NULL default '',
  PRIMARY KEY  (`id`),
  KEY `FK4722E6AE7775355D` (`office_id`),
  KEY `FK4722E6AE87B78657` (`project_id`),
  CONSTRAINT `FK4722E6AE7775355D` FOREIGN KEY (`office_id`) REFERENCES `office` (`id`),
  CONSTRAINT `FK4722E6AE87B78657` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,8787898689,'HM0000966','Shruti',5258000,'Mishra',3,3,'shruti.mishra@happiestminds.com','821c7cb5fdc85a8d5739a6c3ccb913d8dd5571edfc693103a4fdfeb686820ab5cac368e0ee931592'),(2,9867564534,'HM0000767','Mridula',5258000,'Ghosh',1,2,'mridula.ghosh@happiestminds.com','821c7cb5fdc85a8d5739a6c3ccb913d8dd5571edfc693103a4fdfeb686820ab5cac368e0ee931592'),(3,7856435678,'HM0000655','Sincy',5258000,'Sebastian',4,1,'sincy.sebastian@happiestminds.com','821c7cb5fdc85a8d5739a6c3ccb913d8dd5571edfc693103a4fdfeb686820ab5cac368e0ee931592');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `office`
--

DROP TABLE IF EXISTS `office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `office` (
  `id` bigint(20) NOT NULL auto_increment,
  `address` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `office`
--

LOCK TABLES `office` WRITE;
/*!40000 ALTER TABLE `office` DISABLE KEYS */;
INSERT INTO `office` VALUES (1,'SJR','SMILES-1'),(2,'ECITY-1','SMILES-2'),(3,'ECITY-1','SMILES-3'),(4,'Madiwala','SMILES-4');
/*!40000 ALTER TABLE `office` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL auto_increment,
  `code` varchar(255) NOT NULL,
  `manager` varchar(255) default NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'PRJ_01','Hemant Salvi','Kohl\'s'),(2,'PRJ_02','Mgr 1','CompuCom'),(3,'PRJ_03','Mgr 2','Lowe\'s'),(4,'PRJ_04','Mgr 3','Odeon');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-29 19:59:22
